package com.backendchallenge.plazoletaservice.domain.exceptions.orderexceptions;

import com.backendchallenge.plazoletaservice.domain.until.ConstExceptions;

public class OrderNotBelongToEmployeeException extends RuntimeException {
    public OrderNotBelongToEmployeeException() {
        super(ConstExceptions.ORDER_NOT_BELONG_TO_EMPLOYEE);
    }
}
