package com.backendchallenge.plazoletaservice.domain.until;

public class ConstExceptions {
    public static final String RESTAURANT_NAME_EMPTY = "Restaurant name cannot be empty";
    public static final String RESTAURANT_NIT_EMPTY = "Restaurant nit is empty";
    public static final String RESTAURANT_ADDRESS_EMPTY = "Restaurant address is empty";
    public static final String RESTAURANT_PHONE_EMPTY = "Restaurant phone is empty";
    public static final String RESTAURANT_URL_LOGO_EMPTY = "Restaurant url logo is empty";
    public static final String RESTAURANT_ID_OWNER_EMPTY = "Restaurant id owner is empty";
    public static final String RESTAURANT_WITH_ID_AND_OWNER_NOT_FOUND = "Restaurant with id and owner not found";

    public static final String INVALID_RESTAURANT_PHONE_FORMAT = "Restaurant phone format is invalid";
    public static final String INVALID_RESTAURANT_DOCUMENT_FORMAT = "Restaurant document format is invalid";
    public static final String INVALID_RESTAURANT_NAME_FORMAT = "Restaurant name format is invalid";
    public static final String OWNER_NOT_FOUND = "Owner with this id is not found";

    public static final int CODE_400 = 400;
    public static final int CODE_404 = 404;
    public static final int CODE_302 = 302;


    public static final String DISH_NAME_EMPTY = "Dish name cannot be empty";
    public static final String DISH_PRICE_EMPTY = "Dish price cannot be empty";
    public static final String DISH_DESCRIPTION_EMPTY = "Dish description cannot be empty";
    public static final String DISH_URL_IMAGE_EMPTY = "Dish url image cannot be empty";
    public static final String DISH_CATEGORY_EMPTY = "Dish category cannot be empty";
    public static final String DISH_PRICE_INVALID_VALUE = "Dish price is invalid";

    public static final String DISH_DESCRIPTION_UPDATE_EMPTY = "Dish description update cannot be empty";
    public static final String DISH_PRICE_UPDATE_EMPTY = "Dish price update cannot be empty";
    public static final String DISH_NOT_FOUND = "Dish not found";

    public static final String MALFORMED_JWT = "Malformed JWT";
    public static final String TOKEN_UNSUPPORTED = "Token is unsupported";
    public static final String TOKEN_EXPIRED = "Token is expired";

    public static final String INVALID_TOKEN = "Invalid token";
    public static final String USER_NOT_FOUND = "User not found";
    public static final String RESTAURANT_PAGE_SIZE_INVALID = "Restaurant page size is invalid";
    public static final String RESTAURANT_ORDER_DIRECTION_EMPTY = "Restaurant order direction is empty";
    public static final String RESTAURANT_CURRENT_PAGE_INVALID = "Restaurant current page is invalid";
    public static final String RESTAURANT_ORDER_DIRECTION_INVALID = "Restaurant order direction is invalid";
    public static final String DISHES_PAGE_SIZE_INVALID = "Dishes page size is invalid";
    public static final String DISHES_CURRENT_PAGE_INVALID = "Dishes current page is invalid";
    public static final String DISHES_ORDER_DIRECTION_EMPTY = "Dishes order direction is empty";

    private ConstExceptions() {
    }
}
