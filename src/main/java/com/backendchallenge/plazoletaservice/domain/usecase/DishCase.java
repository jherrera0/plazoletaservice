package com.backendchallenge.plazoletaservice.domain.usecase;

import com.backendchallenge.plazoletaservice.domain.api.IDishServicePort;
import com.backendchallenge.plazoletaservice.domain.exceptions.dishexceptions.*;
import com.backendchallenge.plazoletaservice.domain.exceptions.restaurantexceptions.OwnerNotFoundException;
import com.backendchallenge.plazoletaservice.domain.exceptions.restaurantexceptions.RestaurantNotFoundException;
import com.backendchallenge.plazoletaservice.domain.model.Dish;
import com.backendchallenge.plazoletaservice.domain.model.PageCustom;
import com.backendchallenge.plazoletaservice.domain.spi.ICategoryPersistencePort;
import com.backendchallenge.plazoletaservice.domain.spi.IDishPersistencePort;
import com.backendchallenge.plazoletaservice.domain.spi.IJwtPersistencePort;
import com.backendchallenge.plazoletaservice.domain.spi.IRestaurantPersistencePort;
import com.backendchallenge.plazoletaservice.domain.spi.IUserPersistencePort;
import com.backendchallenge.plazoletaservice.domain.until.ConstJwt;
import com.backendchallenge.plazoletaservice.domain.until.ConstValidation;
import com.backendchallenge.plazoletaservice.domain.until.TokenHolder;

import java.util.Objects;

public class DishCase implements IDishServicePort {
    private final IDishPersistencePort dishPersistencePort;
    private final IRestaurantPersistencePort restaurantPersistencePort;
    private final IUserPersistencePort userPersistencePort;
    private final ICategoryPersistencePort categoryPersistencePort;
    private final IJwtPersistencePort jwtPersistencePort;

    public DishCase(IDishPersistencePort dishPersistencePort,
                    IRestaurantPersistencePort restaurantPersistencePort,
                    IUserPersistencePort userPersistencePort,
                    ICategoryPersistencePort categoryPersistencePort,
                    IJwtPersistencePort jwtPersistencePort
    ) {
        this.restaurantPersistencePort = restaurantPersistencePort;
        this.dishPersistencePort = dishPersistencePort;
        this.userPersistencePort = userPersistencePort;
        this.categoryPersistencePort = categoryPersistencePort;
        this.jwtPersistencePort = jwtPersistencePort;
    }

    @Override
    public void createDish(Dish dish) {
        String token = TokenHolder.getToken().replace(ConstJwt.BEARER, ConstJwt.SPLITERSTRING);
        Long idOwner = jwtPersistencePort.getUserId(token);
        if(!userPersistencePort.findOwnerById(idOwner)) {
            throw new OwnerNotFoundException();
        }
        if(!restaurantPersistencePort.existsRestaurantByIdAndOwner(dish.getIdRestaurant(), idOwner)) {
            throw new RestaurantNotFoundException();
        }
        validatedDishParams(dish);
        dish.setCategories(categoryPersistencePort.getCategoriesByNames(dish.getCategories()));
        dish.setAvailable(true);
        if (!Boolean.TRUE.equals(dishPersistencePort.createDish(dish))) {
            throw new RestaurantNotFoundException();
        }
    }

    @Override
    public void updateDish(Long idDish, String descriptionUpdate, Integer priceUpdate) {
        if (!dishPersistencePort.findDishById(idDish)){
            throw new DishNotFoundException();
        }
        String token = TokenHolder.getToken().replace(ConstJwt.BEARER, ConstJwt.SPLITERSTRING);
        Long idOwner = jwtPersistencePort.getUserId(token);

        if(dishPersistencePort.getRestaurantIdByDishId(idDish)== 0 ){
            throw new DishNotFoundException();
        }

        if (!restaurantPersistencePort.existsRestaurantByIdAndOwner(dishPersistencePort.getRestaurantIdByDishId(idDish), idOwner)) {
            throw new RestaurantNotFoundException();
        }

        updateValidatedParams(descriptionUpdate, priceUpdate);
        dishPersistencePort.updateDish(idDish, descriptionUpdate, priceUpdate);
    }

    @Override
    public void changeDishStatus(Long dishId, Boolean status) {
        if (!dishPersistencePort.findDishById(dishId)){
            throw new DishNotFoundException();
        }
        String token = TokenHolder.getToken().replace(ConstJwt.BEARER, ConstJwt.SPLITERSTRING);
        Long idOwner = jwtPersistencePort.getUserId(token);
        if (!restaurantPersistencePort.existsRestaurantByIdAndOwner(dishPersistencePort.getRestaurantIdByDishId(dishId), idOwner)) {
            throw new RestaurantNotFoundException();
        }
        Dish dish = dishPersistencePort.getDishById(dishId);
        dish.setAvailable(status);
        dishPersistencePort.changeDishStatus(dish, idOwner);
    }

    @Override
    public PageCustom<Dish> getDishesByRestaurant(Long restaurantId, Integer currentPage, Integer pageSize, String filterBy,String orderDirection) {
        if (!restaurantPersistencePort.existsRestaurantById(restaurantId)) {
            throw new RestaurantNotFoundException();
        }
        if (pageSize == null || pageSize <= ConstValidation.ZERO) {
            throw new DishesPageSizeInvalidException();
        }
        if (currentPage == null || currentPage < ConstValidation.ZERO) {
            throw new DishesCurrentPageInvalidException();
        }
        if (orderDirection == null || orderDirection.isBlank()) {
            throw new DishesOrderDirectionEmptyException();
        }
        PageCustom<Dish> dishes =
                dishPersistencePort.getDishesByRestaurant(restaurantId, currentPage, pageSize, filterBy, orderDirection);

        if (Objects.equals(dishes.getCurrentPage(), ConstValidation.MINUS_ONE)) {
            throw new DishesCurrentPageInvalidException();
        }

        return dishes;
    }

    private static void updateValidatedParams(String descriptionUpdate,Integer priceUpdate) {
        if(descriptionUpdate == null || descriptionUpdate.isBlank()) {
            throw new DishDescriptionUpdateEmptyException();
        }
        if(priceUpdate == null) {
            throw new DishPriceUpdateEmptyException();
        }
    }

    private static void validatedDishParams(Dish dish) {
        if (dish.getName() == null || dish.getName().isBlank()) {
            throw new DishNameEmptyException();
        }
        if (dish.getPrice() == null) {
            throw new DishPriceEmptyException();
        }
        if (dish.getDescription() == null || dish.getDescription().isBlank()) {
            throw new DishDescriptionEmptyException();
        }
        if (dish.getUrlImage() == null || dish.getUrlImage().isBlank()) {
            throw new DishUrlImageEmptyException();
        }
        if(dish.getCategories() == null) {
            throw new DishCategoryEmptyException();
        }
        dishPriceValidValue(dish.getPrice());
    }

    private static void dishPriceValidValue(Integer price) {
        if (price <= ConstValidation.ZERO) {
            throw new DishPriceInvalidValueException();
        }
    }
}
