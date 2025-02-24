package com.backendchallenge.plazoletaservice.domain.exceptions.restaurantexceptions;

import com.backendchallenge.plazoletaservice.domain.until.ConstExceptions;

public class RestaurantNameEmptyException extends RuntimeException {
    public RestaurantNameEmptyException(){
        super(ConstExceptions.RESTAURANT_NAME_EMPTY);
    }
}
