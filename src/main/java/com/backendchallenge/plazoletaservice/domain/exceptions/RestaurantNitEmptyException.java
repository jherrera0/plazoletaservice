package com.backendchallenge.plazoletaservice.domain.exceptions;

import com.backendchallenge.plazoletaservice.domain.until.ConstExceptions;

public class RestaurantNitEmptyException extends RuntimeException {
    public RestaurantNitEmptyException() {
        super(ConstExceptions.RESTAURANT_NIT_EMPTY);
    }
}
