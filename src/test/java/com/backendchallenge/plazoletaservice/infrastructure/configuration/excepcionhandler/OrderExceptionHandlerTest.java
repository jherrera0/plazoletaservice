package com.backendchallenge.plazoletaservice.infrastructure.configuration.excepcionhandler;

import com.backendchallenge.plazoletaservice.domain.exceptions.employeeexcepcion.EmployeeNotBelongToRestaurantException;
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
    void handleOrderNotBelongToEmployeeException_returnsBadRequest() {
        OrderNotBelongToEmployeeException exception = new OrderNotBelongToEmployeeException();
        ResponseEntity<Object> response = orderExceptionHandler.handleOrderNotBelongToEmployeeException(exception);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(exception.getMessage(), response.getBody());
    }

    @Test
    void handleOrderNotFoundException_returnsBadRequest() {
        OrderNotFoundException exception = new OrderNotFoundException();
        ResponseEntity<Object> response = orderExceptionHandler.handleOrderNotFoundException(exception);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(exception.getMessage(), response.getBody());
    }

    @Test
    void handleOrderNotAssignedException_returnsBadRequest() {
        OrderNotAssignedException exception = new OrderNotAssignedException();
        ResponseEntity<Object> response = orderExceptionHandler.handleOrderNotAssignedException(exception);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(exception.getMessage(), response.getBody());
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
    @Test
    void handleOrderCurrentPageInvalidException_returnsBadRequest() {
        OrderCurrentPageInvalidException exception = new OrderCurrentPageInvalidException();
        ResponseEntity<Object> response = orderExceptionHandler.handleOrderCurrentPageInvalidException(exception);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(ConstExceptions.ORDER_CURRENT_PAGE_INVALID, response.getBody());
    }

    @Test
    void handleOrderFilterByInvalidException_returnsBadRequest() {
        OrderFilterByInvalidException exception = new OrderFilterByInvalidException();
        ResponseEntity<Object> response = orderExceptionHandler.handleOrderFilterByInvalidException(exception);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(ConstExceptions.ORDER_FILTER_BY_INVALID, response.getBody());
    }

    @Test
    void handleOrderPageSizeInvalidException_returnsBadRequest() {
        OrderPageSizeInvalidException exception = new OrderPageSizeInvalidException();
        ResponseEntity<Object> response = orderExceptionHandler.handleOrderPageSizeInvalidException(exception);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(ConstExceptions.ORDER_PAGE_SIZE_INVALID, response.getBody());
    }

    @Test
    void handleOrderOrderDirectionInvalidException_returnsBadRequest() {
        OrderOrderDirectionInvalidException exception = new OrderOrderDirectionInvalidException();
        ResponseEntity<Object> response = orderExceptionHandler.handleOrderOrderDirectionInvalidException(exception);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(ConstExceptions.ORDER_ORDER_DIRECTION_INVALID, response.getBody());
    }
    @Test
    void handleEmployeeNotBelongToRestaurantException_returnsBadRequest() {
        EmployeeNotBelongToRestaurantException exception = new EmployeeNotBelongToRestaurantException();
        ResponseEntity<Object> response = orderExceptionHandler.handleEmployeeNotBelongToRestaurantException(exception);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(ConstExceptions.EMPLOYEE_NOT_BELONG_TO_RESTAURANT, response.getBody());
    }
    @Test
    void handleOrderPinNotFoundException_returnsBadRequest() {
        OrderPinNotFoundException exception = new OrderPinNotFoundException();
        ResponseEntity<Object> response = orderExceptionHandler.handleOrderPinNotFoundException(exception);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(exception.getMessage(), response.getBody());
    }

    @Test
    void handleOrderPinInvalidException_returnsBadRequest() {
        OrderPinInvalidException exception = new OrderPinInvalidException();
        ResponseEntity<Object> response = orderExceptionHandler.handleOrderPinInvalidException(exception);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(exception.getMessage(), response.getBody());
    }

    @Test
    void handleOrderIsNotCompletedException_returnsBadRequest() {
        OrderIsNotCompletedException exception = new OrderIsNotCompletedException();
        ResponseEntity<Object> response = orderExceptionHandler.handleOrderIsNotCompletedException(exception);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(exception.getMessage(), response.getBody());
    }
}