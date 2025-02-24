package com.backendchallenge.plazoletaservice.domain.exceptions.dishexceptions;

import com.backendchallenge.plazoletaservice.domain.until.ConstExceptions;

public class DishNameEmptyException extends RuntimeException {
    public DishNameEmptyException() {
        super(ConstExceptions.DISH_NAME_EMPTY);
    }
}
