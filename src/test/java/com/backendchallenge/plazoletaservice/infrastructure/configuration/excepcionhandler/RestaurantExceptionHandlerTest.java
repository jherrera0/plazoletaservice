package com.backendchallenge.plazoletaservice.infrastructure.configuration.excepcionhandler;

import com.backendchallenge.plazoletaservice.domain.exceptions.restaurantexceptions.*;
import com.backendchallenge.plazoletaservice.domain.until.ConstExceptions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

class RestaurantExceptionHandlerTest {
    private RestaurantExceptionHandler handler;

    @BeforeEach
    void setUp() {
        handler = new RestaurantExceptionHandler();
    }

    @Test
    void handleInvalidRestaurantNameFormatException_ReturnsBadRequest() {
        InvalidRestaurantNameFormatException exception = new InvalidRestaurantNameFormatException();

        ResponseEntity<Object> response = handler.handleInvalidRestaurantNameFormatException(exception);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(ConstExceptions.INVALID_RESTAURANT_NAME_FORMAT, response.getBody());
    }

    @Test
    void handleInvalidRestaurantPhoneFormatException_ReturnsBadRequest() {
        InvalidRestaurantPhoneFormatException exception = new InvalidRestaurantPhoneFormatException();

        ResponseEntity<Object> response = handler.handleInvalidRestaurantPhoneFormatException(exception);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(ConstExceptions.INVALID_RESTAURANT_PHONE_FORMAT, response.getBody());
    }

    @Test
    void handlInvalidRestaurantDocumentFormatException_ReturnsBadRequest() {
        InvalidRestaurantDocumentFormatException exception = new InvalidRestaurantDocumentFormatException();

        ResponseEntity<Object> response = handler.handleInvalidRestaurantDocumentFormatException(exception);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(ConstExceptions.INVALID_RESTAURANT_DOCUMENT_FORMAT, response.getBody());
    }

    @Test
    void handleOwnerNotFoundException_ReturnsNotFound() {
        OwnerNotFoundException exception = new OwnerNotFoundException();

        ResponseEntity<Object> response = handler.handleOwnerNotFoundException(exception);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(ConstExceptions.OWNER_NOT_FOUND, response.getBody());
    }

    @Test
    void handleRestaurantAddressEmptyException_ReturnsBadRequest() {
        RestaurantAddressEmptyException exception = new RestaurantAddressEmptyException();

        ResponseEntity<Object> response = handler.handleRestaurantAddressEmptyException(exception);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(ConstExceptions.RESTAURANT_ADDRESS_EMPTY, response.getBody());
    }

    @Test
    void handleRestaurantIdOwnerEmptyException_ReturnsBadRequest() {
        RestaurantIdOwnerEmptyException exception = new RestaurantIdOwnerEmptyException();

        ResponseEntity<Object> response = handler.handleRestaurantIdOwnerEmptyException(exception);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(ConstExceptions.RESTAURANT_ID_OWNER_EMPTY, response.getBody());
    }

    @Test
    void handleRestaurantNameEmptyException_ReturnsBadRequest() {
        RestaurantNameEmptyException exception = new RestaurantNameEmptyException();

        ResponseEntity<Object> response = handler.handleRestaurantNameEmptyException(exception);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(ConstExceptions.RESTAURANT_NAME_EMPTY, response.getBody());
    }

    @Test
    void handleRestaurantNitEmptyException_ReturnsBadRequest() {
        RestaurantNitEmptyException exception = new RestaurantNitEmptyException();

        ResponseEntity<Object> response = handler.handleRestaurantNitEmptyException(exception);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(ConstExceptions.RESTAURANT_NIT_EMPTY, response.getBody());
    }

    @Test
    void handleRestaurantPhoneEmptyException_ReturnsBadRequest() {
        RestaurantPhoneEmptyException exception = new RestaurantPhoneEmptyException();

        ResponseEntity<Object> response = handler.handleRestaurantPhoneEmptyException(exception);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(ConstExceptions.RESTAURANT_PHONE_EMPTY, response.getBody());
    }

    @Test
    void handleRestaurantUrlLogoEmptyException_ReturnsBadRequest() {
        RestaurantUrlLogoEmptyException exception = new RestaurantUrlLogoEmptyException();

        ResponseEntity<Object> response = handler.handleRestaurantUrlLogoEmptyException(exception);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(ConstExceptions.RESTAURANT_URL_LOGO_EMPTY, response.getBody());
    }
    @Test
    void handleRestaurantNotFoundException_ReturnsNotFound() {
        RestaurantNotFoundException exception = new RestaurantNotFoundException();

        ResponseEntity<Object> response = handler.handleRestaurantNotFoundException(exception);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(exception.getMessage(), response.getBody());
    }
}