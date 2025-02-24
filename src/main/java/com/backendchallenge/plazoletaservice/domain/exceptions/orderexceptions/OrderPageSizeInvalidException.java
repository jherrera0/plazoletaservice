package com.backendchallenge.plazoletaservice.domain.exceptions.orderexceptions;

import com.backendchallenge.plazoletaservice.domain.until.ConstExceptions;

public class OrderPageSizeInvalidException extends RuntimeException {
    public OrderPageSizeInvalidException() {
        super(ConstExceptions.ORDER_PAGE_SIZE_INVALID);
    }
}
