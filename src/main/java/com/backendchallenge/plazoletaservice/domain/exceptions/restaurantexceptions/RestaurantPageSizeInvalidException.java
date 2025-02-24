package com.backendchallenge.plazoletaservice.domain.exceptions.restaurantexceptions;

import com.backendchallenge.plazoletaservice.domain.until.ConstExceptions;

public class RestaurantPageSizeInvalidException extends RuntimeException {
    public RestaurantPageSizeInvalidException() {
        super(ConstExceptions.RESTAURANT_PAGE_SIZE_INVALID);
    }
}
