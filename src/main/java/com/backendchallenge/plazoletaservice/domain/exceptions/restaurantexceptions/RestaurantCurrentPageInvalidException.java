package com.backendchallenge.plazoletaservice.domain.exceptions.restaurantexceptions;

import com.backendchallenge.plazoletaservice.domain.until.ConstExceptions;

public class RestaurantCurrentPageInvalidException extends RuntimeException {
    public RestaurantCurrentPageInvalidException() {
        super(ConstExceptions.RESTAURANT_CURRENT_PAGE_INVALID);
    }
}
