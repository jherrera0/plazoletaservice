package com.backendchallenge.plazoletaservice.domain.exceptions.employeeexcepcion;

import com.backendchallenge.plazoletaservice.domain.until.ConstExceptions;

public class EmployeeNotBelongToRestaurantException extends RuntimeException {
    public EmployeeNotBelongToRestaurantException() {
        super(ConstExceptions.EMPLOYEE_NOT_BELONG_TO_RESTAURANT);
    }
}
