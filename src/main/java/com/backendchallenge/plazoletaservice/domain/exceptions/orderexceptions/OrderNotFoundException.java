package com.backendchallenge.plazoletaservice.domain.exceptions.orderexceptions;

import com.backendchallenge.plazoletaservice.domain.until.ConstExceptions;

public class OrderNotFoundException extends RuntimeException {
    public OrderNotFoundException() {
        super(ConstExceptions.ORDER_NOT_FOUND);
    }
}
