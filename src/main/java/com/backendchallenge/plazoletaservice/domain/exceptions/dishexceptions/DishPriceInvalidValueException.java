package com.backendchallenge.plazoletaservice.domain.exceptions.dishexceptions;

import com.backendchallenge.plazoletaservice.domain.until.ConstExceptions;

public class DishPriceInvalidValueException extends RuntimeException {
    public DishPriceInvalidValueException() {
        super(ConstExceptions.DISH_PRICE_INVALID_VALUE);
    }
}
