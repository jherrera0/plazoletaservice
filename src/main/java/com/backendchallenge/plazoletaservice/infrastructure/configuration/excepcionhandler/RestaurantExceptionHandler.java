package com.backendchallenge.plazoletaservice.infrastructure.configuration.excepcionhandler;

import com.backendchallenge.plazoletaservice.domain.exceptions.restaurantexceptions.*;
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
public class RestaurantExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(InvalidRestaurantDocumentFormatException.class)
    public ResponseEntity<Object> handleInvalidRestaurantDocumentFormatException(InvalidRestaurantDocumentFormatException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(InvalidRestaurantNameFormatException.class)
    public ResponseEntity<Object> handleInvalidRestaurantNameFormatException(InvalidRestaurantNameFormatException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(InvalidRestaurantPhoneFormatException.class)
    public ResponseEntity<Object> handleInvalidRestaurantPhoneFormatException(InvalidRestaurantPhoneFormatException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(OwnerNotFoundException.class)
    public ResponseEntity<Object> handleOwnerNotFoundException(OwnerNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(RestaurantAddressEmptyException.class)
    public ResponseEntity<Object> handleRestaurantAddressEmptyException(RestaurantAddressEmptyException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(RestaurantIdOwnerEmptyException.class)
    public ResponseEntity<Object> handleRestaurantIdOwnerEmptyException(RestaurantIdOwnerEmptyException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(RestaurantNameEmptyException.class)
    public ResponseEntity<Object> handleRestaurantNameEmptyException(RestaurantNameEmptyException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(RestaurantNitEmptyException.class)
    public ResponseEntity<Object> handleRestaurantNitEmptyException(RestaurantNitEmptyException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(RestaurantPhoneEmptyException.class)
    public ResponseEntity<Object> handleRestaurantPhoneEmptyException(RestaurantPhoneEmptyException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(RestaurantUrlLogoEmptyException.class)
    public ResponseEntity<Object> handleRestaurantUrlLogoEmptyException(RestaurantUrlLogoEmptyException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(RestaurantNotFoundException.class)
    public ResponseEntity<Object> handleRestaurantNotFoundException(RestaurantNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
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
