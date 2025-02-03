package com.backendchallenge.plazoletaservice.domain.until;

public class ConstValidation {
    public static final String  PHONE_REGEX = "^(\\+\\d{1,2})?(\\d{0,10})$";
    public static final String NIT_REGEX = "\\d+";
    public static final String NAME_REGEX = "^(?!\\d+$)[a-zA-Z0-9\\s]+$";
    public static final Integer ZERO = 0;
    public static final String COMMA = ",";
    public static final int ONE = 1;
    public static final String EMPTY = "";
    public static final String NAME = "name";
    public static final String ASC = "asc";
    public static final String DESC = "desc";
    public static final Integer MINUS_ONE = -1;
    public static final Integer TWO = 2;
    public static final String PENDING = "PENDING";
    public static final String IN_PROCESS = "IN PROCESS";
    public static final String COMPLETED = "COMPLETED";

    private ConstValidation() {
    }
}
