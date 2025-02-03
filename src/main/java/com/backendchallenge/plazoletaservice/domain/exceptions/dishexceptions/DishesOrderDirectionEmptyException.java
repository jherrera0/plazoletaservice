package com.backendchallenge.plazoletaservice.domain.exceptions.dishexceptions;

import com.backendchallenge.plazoletaservice.domain.until.ConstExceptions;

public class DishesOrderDirectionEmptyException extends RuntimeException {
    public DishesOrderDirectionEmptyException() {
        super(ConstExceptions.DISHES_ORDER_DIRECTION_EMPTY);
    }
}
