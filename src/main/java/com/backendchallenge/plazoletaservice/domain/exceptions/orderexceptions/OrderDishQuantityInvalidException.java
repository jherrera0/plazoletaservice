package com.backendchallenge.plazoletaservice.domain.exceptions.orderexceptions;

import com.backendchallenge.plazoletaservice.domain.until.ConstExceptions;

public class OrderDishQuantityInvalidException extends RuntimeException {
    public OrderDishQuantityInvalidException() {
        super(ConstExceptions.ORDER_DISH_QUANTITY_INVALID);
    }
}
