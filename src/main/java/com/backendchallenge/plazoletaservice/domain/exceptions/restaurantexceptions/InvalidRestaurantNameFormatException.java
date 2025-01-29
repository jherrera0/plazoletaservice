package com.backendchallenge.plazoletaservice.domain.exceptions.restaurantexceptions;

import com.backendchallenge.plazoletaservice.domain.until.ConstExceptions;

public class InvalidRestaurantNameFormatException extends RuntimeException {
    public InvalidRestaurantNameFormatException() {
        super(ConstExceptions.INVALID_RESTAURANT_NAME_FORMAT);
    }
}
