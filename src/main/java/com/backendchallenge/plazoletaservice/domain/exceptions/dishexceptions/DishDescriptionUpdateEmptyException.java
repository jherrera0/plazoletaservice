package com.backendchallenge.plazoletaservice.domain.exceptions.dishexceptions;

import com.backendchallenge.plazoletaservice.domain.until.ConstExceptions;

public class DishDescriptionUpdateEmptyException extends RuntimeException{
    public DishDescriptionUpdateEmptyException() {
        super(ConstExceptions.DISH_DESCRIPTION_UPDATE_EMPTY);
    }
}
