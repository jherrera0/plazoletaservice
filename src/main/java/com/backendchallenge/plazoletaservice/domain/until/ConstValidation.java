package com.backendchallenge.plazoletaservice.domain.until;

public class ConstValidation {
    public static final String  PHONE_REGEX = "^(\\+\\d{1,2})?(\\d{0,10})$";
    public static final String NIT_REGEX = "\\d+";
    public static final String NAME_REGEX = "^(?!\\d+$)[a-zA-Z0-9\\s]+$";
    public static final Integer ZERO = 0;
    public static final String EMPTY = "";

    private ConstValidation() {
    }
}
