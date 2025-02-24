package com.backendchallenge.plazoletaservice.domain.exceptions.dishexceptions;

import com.backendchallenge.plazoletaservice.domain.until.ConstExceptions;

public class DishNotFoundException extends RuntimeException {
    public DishNotFoundException() {
        super(ConstExceptions.DISH_NOT_FOUND);
    }
}
