package com.backendchallenge.plazoletaservice.domain.exceptions.dishexceptions;

import com.backendchallenge.plazoletaservice.domain.until.ConstExceptions;

public class DishPriceEmptyException extends RuntimeException {
    public DishPriceEmptyException() {
        super(ConstExceptions.DISH_PRICE_EMPTY);
    }
}
