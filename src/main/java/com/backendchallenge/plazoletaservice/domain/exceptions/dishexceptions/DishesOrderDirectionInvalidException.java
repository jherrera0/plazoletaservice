package com.backendchallenge.plazoletaservice.domain.exceptions.dishexceptions;

import com.backendchallenge.plazoletaservice.domain.until.ConstExceptions;

public class DishesOrderDirectionInvalidException extends RuntimeException {
    public DishesOrderDirectionInvalidException() {
        super(ConstExceptions.DISHES_ORDER_DIRECTION_INVALID);
    }
}
