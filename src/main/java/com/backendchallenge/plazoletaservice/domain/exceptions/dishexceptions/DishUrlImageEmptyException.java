package com.backendchallenge.plazoletaservice.domain.exceptions.dishexceptions;

import com.backendchallenge.plazoletaservice.domain.until.ConstExceptions;

public class DishUrlImageEmptyException extends RuntimeException {
    public DishUrlImageEmptyException() {
        super(ConstExceptions.DISH_URL_IMAGE_EMPTY);
    }
}
