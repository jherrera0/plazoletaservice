package com.backendchallenge.plazoletaservice.infrastructure.configuration.excepcionhandler;

import com.backendchallenge.plazoletaservice.domain.exceptions.restaurantexceptions.*;
import com.backendchallenge.plazoletaservice.domain.until.ConstExceptions;
import com.backendchallenge.plazoletaservice.domain.until.ConstTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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
    void handleInvalidRestaurantDocumentFormatException_ReturnsBadRequest() {
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
    @Test
    void handleRestaurantPageSizeInvalidException_ReturnsBadRequest() {
        RestaurantPageSizeInvalidException exception = new RestaurantPageSizeInvalidException();

        ResponseEntity<Object> response = handler.handleRestaurantPageSizeInvalidException(exception);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(ConstExceptions.RESTAURANT_PAGE_SIZE_INVALID, response.getBody());
    }

    @Test
    void handleRestaurantOrderDirectionInvalidException_ReturnsBadRequest() {
        RestaurantOrderDirectionInvalidException exception = new RestaurantOrderDirectionInvalidException();

        ResponseEntity<Object> response = handler.handleRestaurantOrderDirectionInvalidException(exception);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(ConstExceptions.RESTAURANT_ORDER_DIRECTION_INVALID, response.getBody());
    }

    @Test
    void handleRestaurantOrderDirectionEmptyException_ReturnsBadRequest() {
        RestaurantOrderDirectionEmptyException exception = new RestaurantOrderDirectionEmptyException();

        ResponseEntity<Object> response = handler.handleRestaurantOrderDirectionEmptyException(exception);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(ConstExceptions.RESTAURANT_ORDER_DIRECTION_EMPTY, response.getBody());
    }

    @Test
    void handleRestaurantCurrentPageInvalidException_ReturnsBadRequest() {
        RestaurantCurrentPageInvalidException exception = new RestaurantCurrentPageInvalidException();

        ResponseEntity<Object> response = handler.handleRestaurantCurrentPageInvalidException(exception);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(ConstExceptions.RESTAURANT_CURRENT_PAGE_INVALID, response.getBody());
    }

    @Test
    void handleMethodArgumentNotValid() {
        MethodArgumentNotValidException exception = mock(MethodArgumentNotValidException.class);
        BindingResult bindingResult = mock(BindingResult.class);
        HttpHeaders headers = new HttpHeaders();
        HttpStatusCode status = HttpStatus.BAD_REQUEST;
        WebRequest request = mock(WebRequest.class);

        Map<String, Object> errors = new HashMap<>();
        errors.put(ConstTest.FIELD1, ConstTest.ERROR1);
        errors.put(ConstTest.FIELD2, ConstTest.ERROR2);

        when(exception.getBindingResult()).thenReturn(bindingResult);
        when(bindingResult.getFieldErrors()).thenReturn(List.of(
                new FieldError("objectName", ConstTest.FIELD1, ConstTest.ERROR1),
                new FieldError("objectName", ConstTest.FIELD2, ConstTest.ERROR2)
        ));

        ResponseEntity<Object> response = handler.handleMethodArgumentNotValid(exception, headers, status, request);
        assert response != null;
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(errors, response.getBody());
    }

}