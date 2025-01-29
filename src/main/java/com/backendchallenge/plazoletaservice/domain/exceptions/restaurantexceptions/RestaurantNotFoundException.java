package com.backendchallenge.plazoletaservice.domain.exceptions.restaurantexceptions;

import com.backendchallenge.plazoletaservice.domain.until.ConstExceptions;

public class RestaurantNotFoundException extends RuntimeException {
    public RestaurantNotFoundException() {
        super(ConstExceptions.RESTAURANT_WITH_ID_AND_OWNER_NOT_FOUND);
    }
}
