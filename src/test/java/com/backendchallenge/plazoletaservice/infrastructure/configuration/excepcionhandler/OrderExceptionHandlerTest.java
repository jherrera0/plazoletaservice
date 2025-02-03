package com.backendchallenge.plazoletaservice.infrastructure.configuration.excepcionhandler;

import com.backendchallenge.plazoletaservice.domain.exceptions.orderexceptions.*;
import com.backendchallenge.plazoletaservice.domain.until.ConstExceptions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OrderExceptionHandlerTest {

    private OrderExceptionHandler orderExceptionHandler;

    @BeforeEach
    void setUp() {
        orderExceptionHandler = new OrderExceptionHandler();
    }

    @Test
    void handleDishNotFoundInRestaurantException_returnsBadRequest() {
        DishNotFoundInRestaurantException exception = new DishNotFoundInRestaurantException();
        ResponseEntity<Object> response = orderExceptionHandler.handleDishNotFoundInRestaurantException(exception);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(ConstExceptions.DISH_NOT_FOUND_IN_RESTAURANT, response.getBody());
    }

    @Test
    void handleOrderAlreadyExistsException_returnsBadRequest() {
        OrderAlreadyExistsException exception = new OrderAlreadyExistsException();
        ResponseEntity<Object> response = orderExceptionHandler.handleOrderAlreadyExistsException(exception);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(ConstExceptions.ORDER_ALREADY_EXISTS, response.getBody());
    }

    @Test
    void handleOrderDishesNotEmptyException_returnsBadRequest() {
        OrderDishesNotEmptyException exception = new OrderDishesNotEmptyException();
        ResponseEntity<Object> response = orderExceptionHandler.handleOrderDishesNotEmptyException(exception);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(ConstExceptions.ORDER_DISHES_NOT_EMPTY, response.getBody());
    }

    @Test
    void handleOrderDishIdInvalidException_returnsBadRequest() {
        OrderDishIdInvalidException exception = new OrderDishIdInvalidException();
        ResponseEntity<Object> response = orderExceptionHandler.handleOrderDishIdInvalidException(exception);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(ConstExceptions.ORDER_DISH_ID_INVALID, response.getBody());
    }

    @Test
    void handleOrderDishQuantityInvalidException_returnsBadRequest() {
        OrderDishQuantityInvalidException exception = new OrderDishQuantityInvalidException();
        ResponseEntity<Object> response = orderExceptionHandler.handleOrderDishQuantityInvalidException(exception);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(ConstExceptions.ORDER_DISH_QUANTITY_INVALID, response.getBody());
    }

    @Test
    void handleOrderIdRestaurantInvalidException_returnsBadRequest() {
        OrderIdRestaurantInvalidException exception = new OrderIdRestaurantInvalidException();
        ResponseEntity<Object> response = orderExceptionHandler.handleOrderIdRestaurantInvalidException(exception);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(ConstExceptions.ORDER_ID_RESTAURANT_INVALID, response.getBody());
    }

    @Test
    void handleOrderNotCreatedException_returnsBadRequest() {
        OrderNotCreatedException exception = new OrderNotCreatedException();
        ResponseEntity<Object> response = orderExceptionHandler.handleOrderNotCreatedException(exception);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(ConstExceptions.ORDER_NOT_CREATED, response.getBody());
    }
}