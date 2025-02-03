package com.backendchallenge.plazoletaservice.infrastructure.configuration.excepcionhandler;

import com.backendchallenge.plazoletaservice.domain.exceptions.dishexceptions.*;
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

    @Test
    void handleDishPriceUpdateEmptyException_ReturnsBadRequest() {
        DishPriceUpdateEmptyException exception = new DishPriceUpdateEmptyException();

        ResponseEntity<Object> response = handler.handleDishPriceUpdateEmptyException(exception);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(exception.getMessage(), response.getBody());
    }

    @Test
    void handleDishCategoryUpdateEmptyException_ReturnsBadRequest() {
        DishDescriptionUpdateEmptyException exception = new DishDescriptionUpdateEmptyException();

        ResponseEntity<Object> response = handler.handleDishDescriptionUpdateEmptyException(exception);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(exception.getMessage(), response.getBody());
    }
    @Test
    void handleDishesCurrentPageInvalidException_ReturnsBadRequest() {
        DishesCurrentPageInvalidException exception = new DishesCurrentPageInvalidException();

        ResponseEntity<Object> response = handler.handleDishesCurrentPageInvalidException(exception);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(ConstExceptions.DISHES_CURRENT_PAGE_INVALID, response.getBody());
    }

    @Test
    void handleDishesOrderDirectionEmptyException_ReturnsBadRequest() {
        DishesOrderDirectionEmptyException exception = new DishesOrderDirectionEmptyException();

        ResponseEntity<Object> response = handler.handleDishesOrderDirectionEmptyException(exception);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(ConstExceptions.DISHES_ORDER_DIRECTION_EMPTY, response.getBody());
    }

    @Test
    void handleDishesOrderDirectionInvalidException_ReturnsBadRequest() {
        DishesOrderDirectionInvalidException exception = new DishesOrderDirectionInvalidException();

        ResponseEntity<Object> response = handler.handleDishesOrderDirectionInvalidException(exception);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(ConstExceptions.DISHES_ORDER_DIRECTION_INVALID, response.getBody());
    }

    @Test
    void handleDishesPageSizeInvalidException_ReturnsBadRequest() {
        DishesPageSizeInvalidException exception = new DishesPageSizeInvalidException();

        ResponseEntity<Object> response = handler.handleDishesPageSizeInvalidException(exception);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(ConstExceptions.DISHES_PAGE_SIZE_INVALID, response.getBody());
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