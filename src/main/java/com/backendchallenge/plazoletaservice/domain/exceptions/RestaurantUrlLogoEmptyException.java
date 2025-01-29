package com.backendchallenge.plazoletaservice.domain.exceptions;

import com.backendchallenge.plazoletaservice.domain.until.ConstExceptions;

public class RestaurantUrlLogoEmptyException extends RuntimeException {
    public RestaurantUrlLogoEmptyException() {
        super(ConstExceptions.RESTAURANT_URL_LOGO_EMPTY);
    }
}
