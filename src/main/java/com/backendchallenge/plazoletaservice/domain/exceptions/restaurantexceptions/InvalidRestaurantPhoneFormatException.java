package com.backendchallenge.plazoletaservice.domain.exceptions.restaurantexceptions;

import com.backendchallenge.plazoletaservice.domain.until.ConstExceptions;

public class InvalidRestaurantPhoneFormatException extends RuntimeException {
    public InvalidRestaurantPhoneFormatException() {
        super(ConstExceptions.INVALID_RESTAURANT_PHONE_FORMAT);
    }
}
