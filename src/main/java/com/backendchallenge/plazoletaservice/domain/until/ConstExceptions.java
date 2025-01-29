package com.backendchallenge.plazoletaservice.domain.until;

public class ConstExceptions {
    public static final String RESTAURANT_NAME_EMPTY = "Restaurant name cannot be empty";
    public static final String RESTAURANT_NIT_EMPTY = "Restaurant nit is empty";
    public static final String RESTAURANT_ADDRESS_EMPTY = "Restaurant address is empty";
    public static final String RESTAURANT_PHONE_EMPTY = "Restaurant phone is empty";
    public static final String RESTAURANT_URL_LOGO_EMPTY = "Restaurant url logo is empty";
    public static final String RESTAURANT_ID_OWNER_EMPTY = "Restaurant id owner is empty";

    public static final String INVALID_RESTAURANT_PHONE_FORMAT = "Restaurant phone format is invalid";
    public static final String INVALID_RESTAURANT_DOCUMENT_FORMAT = "Restaurant document format is invalid";
    public static final String INVALID_RESTAURANT_NAME_FORMAT = "Restaurant name format is invalid";
    public static final String OWNER_NOT_FOUND = "Owner with this id is not found";

    public static final int CODE_400 = 400;
    public static final int CODE_404 = 404;
    public static final int CODE_302 = 302;
    public static final int CODE_500 = 500;

    private ConstExceptions() {
    }
}
