package com.backendchallenge.plazoletaservice.infrastructure.configuration.excepcionhandler;

import com.backendchallenge.plazoletaservice.domain.exceptions.orderexceptions.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class OrderExceptionHandler{
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
