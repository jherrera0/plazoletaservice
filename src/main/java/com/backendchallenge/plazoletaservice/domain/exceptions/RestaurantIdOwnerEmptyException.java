package com.backendchallenge.plazoletaservice.domain.exceptions;

import com.backendchallenge.plazoletaservice.domain.until.ConstExceptions;

public class RestaurantIdOwnerEmptyException extends RuntimeException {
    public RestaurantIdOwnerEmptyException() {
        super(ConstExceptions.RESTAURANT_ID_OWNER_EMPTY);
    }
}
