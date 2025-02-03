package com.backendchallenge.plazoletaservice.domain.exceptions.orderexceptions;

import com.backendchallenge.plazoletaservice.domain.until.ConstExceptions;

public class OrderDishesNotEmptyException extends RuntimeException {
    public OrderDishesNotEmptyException() {
        super(ConstExceptions.ORDER_DISHES_NOT_EMPTY);
    }
}
