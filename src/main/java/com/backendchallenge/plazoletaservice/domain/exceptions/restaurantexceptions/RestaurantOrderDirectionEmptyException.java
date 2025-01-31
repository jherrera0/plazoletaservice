package com.backendchallenge.plazoletaservice.domain.exceptions.restaurantexceptions;

import com.backendchallenge.plazoletaservice.domain.until.ConstExceptions;

public class RestaurantOrderDirectionEmptyException extends RuntimeException {
    public RestaurantOrderDirectionEmptyException() {
        super(ConstExceptions.RESTAURANT_ORDER_DIRECTION_EMPTY);
    }
}
