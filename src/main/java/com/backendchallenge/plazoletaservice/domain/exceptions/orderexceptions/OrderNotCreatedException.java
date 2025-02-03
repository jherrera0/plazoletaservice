package com.backendchallenge.plazoletaservice.domain.exceptions.orderexceptions;

import com.backendchallenge.plazoletaservice.domain.until.ConstExceptions;

public class OrderNotCreatedException extends RuntimeException {
    public OrderNotCreatedException() {
        super(ConstExceptions.ORDER_NOT_CREATED);
    }
}
