package com.backendchallenge.plazoletaservice.domain.exceptions.employeeexcepcion;

import com.backendchallenge.plazoletaservice.domain.until.ConstExceptions;

public class RestaurantIdEmptyException extends RuntimeException {
    public RestaurantIdEmptyException() {
        super(ConstExceptions.RESTAURANT_ID_EMPTY);
    }
}
