package com.backendchallenge.plazoletaservice.domain.exceptions.orderexceptions;

import com.backendchallenge.plazoletaservice.domain.until.ConstExceptions;

public class OrderCurrentPageInvalidException extends RuntimeException {
    public OrderCurrentPageInvalidException() {
        super(ConstExceptions.ORDER_CURRENT_PAGE_INVALID);
    }
}
