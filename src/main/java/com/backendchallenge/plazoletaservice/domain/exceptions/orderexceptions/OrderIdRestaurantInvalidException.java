package com.backendchallenge.plazoletaservice.domain.exceptions.orderexceptions;

import com.backendchallenge.plazoletaservice.domain.until.ConstExceptions;

public class OrderIdRestaurantInvalidException extends RuntimeException {
    public OrderIdRestaurantInvalidException() {
        super(ConstExceptions.ORDER_ID_RESTAURANT_INVALID);
    }
}
