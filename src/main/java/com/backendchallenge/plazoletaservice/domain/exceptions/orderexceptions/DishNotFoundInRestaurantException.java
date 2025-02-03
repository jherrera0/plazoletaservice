package com.backendchallenge.plazoletaservice.domain.exceptions.orderexceptions;

import com.backendchallenge.plazoletaservice.domain.until.ConstExceptions;

public class DishNotFoundInRestaurantException extends RuntimeException {
    public DishNotFoundInRestaurantException() {
        super(ConstExceptions.DISH_NOT_FOUND_IN_RESTAURANT);
    }
}
