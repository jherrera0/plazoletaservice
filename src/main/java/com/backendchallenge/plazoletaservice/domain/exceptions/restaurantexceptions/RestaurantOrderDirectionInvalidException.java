package com.backendchallenge.plazoletaservice.domain.exceptions.restaurantexceptions;

import com.backendchallenge.plazoletaservice.domain.until.ConstExceptions;

public class RestaurantOrderDirectionInvalidException extends RuntimeException {
    public RestaurantOrderDirectionInvalidException() {
        super(ConstExceptions.RESTAURANT_ORDER_DIRECTION_INVALID);
    }
}
