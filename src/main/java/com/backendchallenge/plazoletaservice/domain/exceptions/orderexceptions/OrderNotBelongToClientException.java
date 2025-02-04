package com.backendchallenge.plazoletaservice.domain.exceptions.orderexceptions;

import com.backendchallenge.plazoletaservice.domain.until.ConstExceptions;

public class OrderNotBelongToClientException extends RuntimeException {
    public OrderNotBelongToClientException() {
        super(ConstExceptions.ORDER_NOT_BELONG_TO_CLIENT);
    }
}
