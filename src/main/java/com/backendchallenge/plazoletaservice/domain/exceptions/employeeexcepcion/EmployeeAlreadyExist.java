package com.backendchallenge.plazoletaservice.domain.exceptions.employeeexcepcion;

import com.backendchallenge.plazoletaservice.domain.until.ConstExceptions;

public class EmployeeAlreadyExist extends RuntimeException {
    public EmployeeAlreadyExist() {
        super(ConstExceptions.EMPLOYEE_ALREADY_EXIST);
    }
}
