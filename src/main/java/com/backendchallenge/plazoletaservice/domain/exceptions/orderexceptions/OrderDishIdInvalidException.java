package com.backendchallenge.plazoletaservice.domain.exceptions.orderexceptions;

import com.backendchallenge.plazoletaservice.domain.until.ConstExceptions;

public class OrderDishIdInvalidException extends RuntimeException {
    public OrderDishIdInvalidException() {
        super(ConstExceptions.ORDER_DISH_ID_INVALID);
    }
}
