package com.backendchallenge.plazoletaservice.domain.exceptions.restaurantexceptions;

import com.backendchallenge.plazoletaservice.domain.until.ConstExceptions;

public class RestaurantPhoneEmptyException extends RuntimeException {
    public RestaurantPhoneEmptyException() {
        super(ConstExceptions.RESTAURANT_PHONE_EMPTY);
    }
}
