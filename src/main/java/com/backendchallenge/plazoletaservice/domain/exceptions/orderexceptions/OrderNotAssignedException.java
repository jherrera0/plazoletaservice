package com.backendchallenge.plazoletaservice.domain.exceptions.orderexceptions;

import com.backendchallenge.plazoletaservice.domain.until.ConstExceptions;

public class OrderNotAssignedException extends RuntimeException {
    public OrderNotAssignedException() {
        super(ConstExceptions.ORDER_NOT_ASSIGNED);
    }
}
