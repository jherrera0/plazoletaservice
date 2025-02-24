package com.backendchallenge.plazoletaservice.domain.exceptions.dishexceptions;

import com.backendchallenge.plazoletaservice.domain.until.ConstExceptions;

public class DishPriceUpdateEmptyException extends RuntimeException {
    public DishPriceUpdateEmptyException() {
        super(ConstExceptions.DISH_PRICE_UPDATE_EMPTY);
    }
}
