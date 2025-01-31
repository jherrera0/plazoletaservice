package com.backendchallenge.plazoletaservice.domain.usecase;

import com.backendchallenge.plazoletaservice.domain.api.IDishServicePort;
import com.backendchallenge.plazoletaservice.domain.exceptions.dishexceptions.*;
import com.backendchallenge.plazoletaservice.domain.exceptions.restaurantexceptions.OwnerNotFoundException;
import com.backendchallenge.plazoletaservice.domain.exceptions.restaurantexceptions.RestaurantNotFoundException;
import com.backendchallenge.plazoletaservice.domain.model.Dish;
import com.backendchallenge.plazoletaservice.domain.spi.IDishPersistencePort;
import com.backendchallenge.plazoletaservice.domain.spi.IJwtPersistencePort;
import com.backendchallenge.plazoletaservice.domain.spi.IRestaurantPersistencePort;
import com.backendchallenge.plazoletaservice.domain.spi.IUserPersistencePort;
import com.backendchallenge.plazoletaservice.domain.until.ConstJwt;
import com.backendchallenge.plazoletaservice.domain.until.ConstValidation;
import com.backendchallenge.plazoletaservice.domain.until.TokenHolder;

public class DishCase implements IDishServicePort {
    private final IDishPersistencePort dishPersistencePort;
    private final IRestaurantPersistencePort restaurantPersistencePort;
    private final IUserPersistencePort userPersistencePort;
    private final IJwtPersistencePort jwtPersistencePort;

    public DishCase(IDishPersistencePort dishPersistencePort,
                    IRestaurantPersistencePort restaurantPersistencePort,
                    IUserPersistencePort userPersistencePort,
                    IJwtPersistencePort jwtPersistencePort
    ) {
        this.restaurantPersistencePort = restaurantPersistencePort;
        this.dishPersistencePort = dishPersistencePort;
        this.userPersistencePort = userPersistencePort;
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
        if(dish.getCategory() == null || dish.getCategory().isBlank()) {
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
