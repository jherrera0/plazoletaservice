package com.backendchallenge.plazoletaservice.domain.exceptions.dishexceptions;

import com.backendchallenge.plazoletaservice.domain.until.ConstExceptions;

public class DishDescriptionEmptyException extends RuntimeException {
    public DishDescriptionEmptyException() {
        super(ConstExceptions.DISH_DESCRIPTION_EMPTY);
    }
}
