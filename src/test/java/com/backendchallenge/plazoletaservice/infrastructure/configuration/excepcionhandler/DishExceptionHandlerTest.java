package com.backendchallenge.plazoletaservice.infrastructure.configuration.excepcionhandler;

import com.backendchallenge.plazoletaservice.domain.exceptions.dishexceptions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

class DishExceptionHandlerTest {
    private DishExceptionHandler handler;

    @BeforeEach
    void setUp() {
        handler = new DishExceptionHandler();
    }
    @Test
    void handleDishCategoryEmptyException_ReturnsBadRequest() {
        DishCategoryEmptyException exception = new DishCategoryEmptyException();

        ResponseEntity<Object> response = handler.handleDishCategoryEmptyException(exception);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(exception.getMessage(), response.getBody());
    }

    @Test
    void handleDishDescriptionEmptyException_ReturnsBadRequest() {
        DishDescriptionEmptyException exception = new DishDescriptionEmptyException();

        ResponseEntity<Object> response = handler.handleDishDescriptionEmptyException(exception);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(exception.getMessage(), response.getBody());
    }

    @Test
    void handleDishPriceEmptyException_ReturnsBadRequest() {
        DishPriceEmptyException exception = new DishPriceEmptyException();

        ResponseEntity<Object> response = handler.handleDishPriceEmptyException(exception);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(exception.getMessage(), response.getBody());
    }

    @Test
    void handleDishUrlImageEmptyException_ReturnsBadRequest() {
        DishUrlImageEmptyException exception = new DishUrlImageEmptyException();

        ResponseEntity<Object> response = handler.handleDishUrlImageEmptyException(exception);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(exception.getMessage(), response.getBody());
    }

    @Test
    void handleDishPriceInvalidValueException_ReturnsBadRequest() {
        DishPriceInvalidValueException exception = new DishPriceInvalidValueException();

        ResponseEntity<Object> response = handler.handleDishPriceInvalidValueException(exception);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(exception.getMessage(), response.getBody());
    }


}