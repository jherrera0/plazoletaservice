package com.backendchallenge.plazoletaservice.domain.usecase;

import com.backendchallenge.plazoletaservice.domain.api.IDishServicePort;
import com.backendchallenge.plazoletaservice.domain.exceptions.dishexceptions.*;
import com.backendchallenge.plazoletaservice.domain.exceptions.restaurantexceptions.OwnerNotFoundException;
import com.backendchallenge.plazoletaservice.domain.exceptions.restaurantexceptions.RestaurantNotFoundException;
import com.backendchallenge.plazoletaservice.domain.model.Dish;
import com.backendchallenge.plazoletaservice.domain.spi.IDishPersistencePort;
import com.backendchallenge.plazoletaservice.domain.spi.IRestaurantPersistencePort;
import com.backendchallenge.plazoletaservice.domain.spi.IUserPersistencePort;
import com.backendchallenge.plazoletaservice.domain.until.ConstValidation;

public class DishCase implements IDishServicePort {
    private final IDishPersistencePort dishPersistencePort;
    private final IRestaurantPersistencePort restaurantPersistencePort;
    private final IUserPersistencePort userPersistencePort;

    public DishCase(IDishPersistencePort dishPersistencePort,
                    IRestaurantPersistencePort restaurantPersistencePort,
                    IUserPersistencePort userPersistencePort
    ) {
        this.restaurantPersistencePort = restaurantPersistencePort;
        this.dishPersistencePort = dishPersistencePort;
        this.userPersistencePort = userPersistencePort;
    }

    @Override
    public void createDish(Dish dish, Long idOwner) {
        if(!userPersistencePort.findOwnerById(idOwner)) {
            throw new OwnerNotFoundException();
        }
        if(!restaurantPersistencePort.existsRestaurantByIdAndOwner(dish.getIdRestaurant(), idOwner)) {
            throw new RestaurantNotFoundException();
        }
        validatedDishParams(dish);
        dishPersistencePort.createDish(dish, idOwner);
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
