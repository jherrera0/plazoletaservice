package com.backendchallenge.plazoletaservice.domain.exceptions.restaurantexceptions;

import com.backendchallenge.plazoletaservice.domain.until.ConstExceptions;

public class RestaurantAddressEmptyException extends RuntimeException {
    public RestaurantAddressEmptyException() {
        super(ConstExceptions.RESTAURANT_ADDRESS_EMPTY);
    }
}
