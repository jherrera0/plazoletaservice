package com.backendchallenge.plazoletaservice.domain.exceptions.orderexceptions;

import com.backendchallenge.plazoletaservice.domain.until.ConstExceptions;

public class OrderNotCancelableException extends RuntimeException {
    public OrderNotCancelableException() {
        super(ConstExceptions.ORDER_NOT_CANCELABLE);
    }
}
