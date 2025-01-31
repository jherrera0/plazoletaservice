package com.backendchallenge.plazoletaservice.domain.until;

public class ConstDocumentation {


    public static final String RESTAURANT_TAG_NAME = "Restaurant Rest API";
    public static final String RESTAURANT_TAG_DESCRIPTION = "API to manage restaurant creation requests";
    public static final String CODE_400 = "400";
    public static final String CODE_201 = "201";
    public static final String CREATE_RESTAURANT_OPERATION = "Create a new restaurant";
    public static final String CREATE_RESTAURANT_CODE_400 = "Invalid data to request create restaurant";
    public static final String CREATE_RESTAURANT_CODE_201 = "Restaurant created successfully";
    public static final String DISH_TAG_NAME = "Dish Rest API";
    public static final String DISH_TAG_DESCRIPTION = "API to manage dish creation requests";
    public static final String CREATE_DISH_OPERATION = "Create a new dish";
    public static final String CREATE_DISH_CODE_201 = "Dish created successfully";
    public static final String CREATE_DISH_CODE_400 = "Invalid data to request create dish";
    public static final String UPDATE_DISH_OPERATION = "Update a dish";
    public static final String UPDATE_DISH_CODE_201 = "Dish updated successfully";
    public static final String UPDATE_DISH_CODE_400 = "Invalid data to request update dish";
    public static final String CHANGE_DISH_STATUS_OPERATION = "Change dish status";
    public static final String CHANGE_DISH_STATUS_CODE_201 = "Dish status changed successfully";
    public static final String CHANGE_DISH_STATUS_CODE_400 = "Invalid data to request change dish status";
    public static final String CODE_403 = "403";
    public static final String CHANGE_DISH_STATUS_CODE_403 = "User not authorized to change dish status";
    public static final String UPDATE_DISH_CODE_403 = "User not authorized to update dish";
    public static final String CREATE_DISH_CODE_403 = "User not authorized to create dish";

    private ConstDocumentation() {
    }
}
