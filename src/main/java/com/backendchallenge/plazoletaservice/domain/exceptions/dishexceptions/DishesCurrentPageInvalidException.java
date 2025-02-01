package com.backendchallenge.plazoletaservice.domain.exceptions.dishexceptions;

import com.backendchallenge.plazoletaservice.domain.until.ConstExceptions;

public class DishesCurrentPageInvalidException extends RuntimeException {
    public DishesCurrentPageInvalidException() {
        super(ConstExceptions.DISHES_CURRENT_PAGE_INVALID);
    }
}
