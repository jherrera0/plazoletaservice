package com.backendchallenge.plazoletaservice.domain.exceptions.orderexceptions;

import com.backendchallenge.plazoletaservice.domain.until.ConstExceptions;

public class OrderIsNotCompletedException extends RuntimeException {
    public OrderIsNotCompletedException() {
        super(ConstExceptions.ORDER_IS_NOT_COMPLETED);
    }
}
