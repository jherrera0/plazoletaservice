package com.backendchallenge.plazoletaservice.domain.exceptions.orderexceptions;

import com.backendchallenge.plazoletaservice.domain.until.ConstExceptions;

public class OrderOrderDirectionInvalidException extends RuntimeException {
    public OrderOrderDirectionInvalidException() {
        super(ConstExceptions.ORDER_ORDER_DIRECTION_INVALID);
    }
}
