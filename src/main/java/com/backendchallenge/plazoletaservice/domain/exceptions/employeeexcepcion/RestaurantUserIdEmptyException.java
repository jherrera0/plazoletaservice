package com.backendchallenge.plazoletaservice.domain.exceptions.employeeexcepcion;

import com.backendchallenge.plazoletaservice.domain.until.ConstExceptions;

public class RestaurantUserIdEmptyException extends RuntimeException {
    public RestaurantUserIdEmptyException() {
        super(ConstExceptions.RESTAURANT_USER_ID_EMPTY);
    }
}
