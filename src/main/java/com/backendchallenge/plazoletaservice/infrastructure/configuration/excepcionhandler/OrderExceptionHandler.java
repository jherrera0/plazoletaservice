package com.backendchallenge.plazoletaservice.infrastructure.configuration.excepcionhandler;

import com.backendchallenge.plazoletaservice.domain.exceptions.employeeexcepcion.EmployeeNotBelongToRestaurantException;
import com.backendchallenge.plazoletaservice.domain.exceptions.orderexceptions.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class OrderExceptionHandler{
    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<Object> handleOrderNotFoundException(OrderNotFoundException ex){
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
    @ExceptionHandler(OrderNotAssignedException.class)
    public ResponseEntity<Object> handleOrderNotAssignedException(OrderNotAssignedException ex){
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
    @ExceptionHandler(EmployeeNotBelongToRestaurantException.class)
    public ResponseEntity<Object> handleEmployeeNotBelongToRestaurantException(EmployeeNotBelongToRestaurantException ex){
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(OrderCurrentPageInvalidException.class)
    public ResponseEntity<Object> handleOrderCurrentPageInvalidException(OrderCurrentPageInvalidException ex){
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(OrderFilterByInvalidException.class)
    public ResponseEntity<Object> handleOrderFilterByInvalidException(OrderFilterByInvalidException ex){
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(OrderPageSizeInvalidException.class)
    public ResponseEntity<Object> handleOrderPageSizeInvalidException(OrderPageSizeInvalidException ex){
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(OrderOrderDirectionInvalidException.class)
    public ResponseEntity<Object> handleOrderOrderDirectionInvalidException(OrderOrderDirectionInvalidException ex){
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(DishNotFoundInRestaurantException.class)
    public ResponseEntity<Object> handleDishNotFoundInRestaurantException(DishNotFoundInRestaurantException ex){
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(OrderAlreadyExistsException.class)
    public ResponseEntity<Object> handleOrderAlreadyExistsException(OrderAlreadyExistsException ex){
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(OrderDishesNotEmptyException.class)
    public ResponseEntity<Object> handleOrderDishesNotEmptyException(OrderDishesNotEmptyException ex){
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(OrderDishIdInvalidException.class)
    public ResponseEntity<Object> handleOrderDishIdInvalidException(OrderDishIdInvalidException ex){
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(OrderDishQuantityInvalidException.class)
    public ResponseEntity<Object> handleOrderDishQuantityInvalidException(OrderDishQuantityInvalidException ex){
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(OrderIdRestaurantInvalidException.class)
    public ResponseEntity<Object> handleOrderIdRestaurantInvalidException(OrderIdRestaurantInvalidException ex){
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(OrderNotCreatedException.class)
    public ResponseEntity<Object> handleOrderNotCreatedException(OrderNotCreatedException ex){
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}
