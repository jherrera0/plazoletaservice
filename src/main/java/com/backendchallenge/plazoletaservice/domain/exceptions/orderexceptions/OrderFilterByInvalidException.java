package com.backendchallenge.plazoletaservice.domain.exceptions.orderexceptions;

import com.backendchallenge.plazoletaservice.domain.until.ConstExceptions;

public class OrderFilterByInvalidException extends RuntimeException {
    public OrderFilterByInvalidException() {
        super(ConstExceptions.ORDER_FILTER_BY_INVALID);
    }
}
