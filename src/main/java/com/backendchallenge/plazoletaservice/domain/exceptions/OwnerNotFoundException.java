package com.backendchallenge.plazoletaservice.domain.exceptions;

import com.backendchallenge.plazoletaservice.domain.until.ConstExceptions;

public class OwnerNotFoundException extends RuntimeException {
    public OwnerNotFoundException() {
        super(ConstExceptions.OWNER_NOT_FOUND);
    }
}
