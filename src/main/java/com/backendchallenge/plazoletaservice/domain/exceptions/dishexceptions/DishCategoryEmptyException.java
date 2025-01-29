package com.backendchallenge.plazoletaservice.domain.exceptions.dishexceptions;

import com.backendchallenge.plazoletaservice.domain.until.ConstExceptions;

public class DishCategoryEmptyException extends RuntimeException {
    public DishCategoryEmptyException() {
        super(ConstExceptions.DISH_CATEGORY_EMPTY);
    }
}
