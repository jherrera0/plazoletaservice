package com.backendchallenge.plazoletaservice.domain.exceptions.orderexceptions;

import com.backendchallenge.plazoletaservice.domain.until.ConstExceptions;

public class OrderPinNotFoundException extends RuntimeException {
    public OrderPinNotFoundException() {
        super(ConstExceptions.ORDER_PIN_NOT_FOUND);
    }
}
