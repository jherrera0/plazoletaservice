package com.backendchallenge.plazoletaservice.domain.exceptions.dishexceptions;

import com.backendchallenge.plazoletaservice.domain.until.ConstExceptions;

public class DishesPageSizeInvalidException extends RuntimeException {
    public DishesPageSizeInvalidException() {
        super(ConstExceptions.DISHES_PAGE_SIZE_INVALID);
    }
}
