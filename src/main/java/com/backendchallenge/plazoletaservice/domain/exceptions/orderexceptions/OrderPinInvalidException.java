package com.backendchallenge.plazoletaservice.domain.exceptions.orderexceptions;

import com.backendchallenge.plazoletaservice.domain.until.ConstExceptions;

public class OrderPinInvalidException extends RuntimeException {
    public OrderPinInvalidException() {
        super(ConstExceptions.ORDER_PIN_INVALID);
    }
}
