package com.backendchallenge.plazoletaservice.infrastructure.configuration.excepcionhandler;

import com.backendchallenge.plazoletaservice.domain.exceptions.dishexceptions.*;
import lombok.NonNull;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class DishExceptionHandler  extends ResponseEntityExceptionHandler {
    @ExceptionHandler(DishesCurrentPageInvalidException.class)
    public ResponseEntity<Object> handleDishesCurrentPageInvalidException(DishesCurrentPageInvalidException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(DishesOrderDirectionEmptyException.class)
    public ResponseEntity<Object> handleDishesOrderDirectionEmptyException(DishesOrderDirectionEmptyException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(DishesOrderDirectionInvalidException.class)
    public ResponseEntity<Object> handleDishesOrderDirectionInvalidException(DishesOrderDirectionInvalidException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(DishesPageSizeInvalidException.class)
    public ResponseEntity<Object> handleDishesPageSizeInvalidException(DishesPageSizeInvalidException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(DishPriceUpdateEmptyException.class)
    public ResponseEntity<Object> handleDishPriceUpdateEmptyException(DishPriceUpdateEmptyException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(DishDescriptionUpdateEmptyException.class)
    public ResponseEntity<Object> handleDishDescriptionUpdateEmptyException(DishDescriptionUpdateEmptyException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(DishCategoryEmptyException.class)
    public ResponseEntity<Object> handleDishCategoryEmptyException(DishCategoryEmptyException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(DishDescriptionEmptyException.class)
    public ResponseEntity<Object> handleDishDescriptionEmptyException(DishDescriptionEmptyException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(DishPriceEmptyException.class)
    public ResponseEntity<Object> handleDishPriceEmptyException(DishPriceEmptyException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(DishUrlImageEmptyException.class)
    public ResponseEntity<Object> handleDishUrlImageEmptyException(DishUrlImageEmptyException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(DishPriceInvalidValueException.class)
    public ResponseEntity<Object> handleDishPriceInvalidValueException(DishPriceInvalidValueException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  @NonNull HttpHeaders headers,
                                                                  @NonNull HttpStatusCode status,
                                                                  @NonNull WebRequest request) {
        Map<String, Object> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->

                errors.put(error.getField(), error.getDefaultMessage())
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }


}
