package com.backendchallenge.plazoletaservice.domain.exceptions.restaurantexceptions;

import com.backendchallenge.plazoletaservice.domain.until.ConstExceptions;

public class InvalidRestaurantDocumentFormatException extends RuntimeException {
    public InvalidRestaurantDocumentFormatException() {
        super(ConstExceptions.INVALID_RESTAURANT_DOCUMENT_FORMAT);
    }
}
