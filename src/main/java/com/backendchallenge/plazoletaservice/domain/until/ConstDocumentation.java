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
    public static final String CREATE_RESTAURANT_CODE_403 = "User not authorized to create restaurant";
    public static final String LIST_RESTAURANTS_OPERATION = "List all restaurants";
    public static final String LIST_RESTAURANTS_CODE_201 = "Restaurants listed successfully";
    public static final String LIST_DISHES_OPERATION = "List all dishes of a restaurant";
    public static final String LIST_DISHES_CODE_201 = "Dishes listed successfully";
    public static final String LIST_DISHES_CODE_400 = "Invalid data to request list dishes";
    public static final String LIST_DISHES_CODE_403 = "User not authorized to list dishes";
    public static final String ORDER_TAG_NAME = "Order Rest API";
    public static final String ORDER_TAG_DESCRIPTION = "API to manage order creation requests";
    public static final String CREATE_ORDER_OPERATION = "Create a new order";
    public static final String CREATE_ORDER_CODE_201 = "Order created successfully";
    public static final String CREATE_ORDER_CODE_400 = "Invalid data to request create order";
    public static final String CREATE_EMPLOYEE_OPERATION = "Create a new employee user";
    public static final String CREATE_EMPLOYEE_CODE_201 = "Employee user created successfully";
    public static final String CREATE_EMPLOYEE_CODE_400 = "Invalid data to request create employee user";
    public static final String CREATE_EMPLOYEE_CODE_403 = "User not authorized to create employee user";
    public static final String LIST_ORDERS_OPERATION = "List all orders";
    public static final String LIST_ORDERS_CODE_201 = "Orders listed successfully";
    public static final String ASSIGN_EMPLOYEE_TO_ORDER_OPERATION = "Assign an employee to an order";
    public static final String ASSIGN_EMPLOYEE_TO_ORDER_CODE_201 = "Employee assigned to order successfully";
    public static final String ASSIGN_EMPLOYEE_TO_ORDER_CODE_400 = "Invalid data to request assign employee to order";
    public static final String ASSIGN_EMPLOYEE_TO_ORDER_CODE_403 = "User not authorized to assign employee to order";
    public static final String LIST_ORDERS_CODE_400 = "Invalid data to request list orders";
    public static final String LIST_ORDERS_CODE_403 = "User not authorized to list orders";
    public static final String CREATE_ORDER_CODE_403 = "User not authorized to create order";
    public static final String ORDER_READY_OPERATION = "Order ready";
    public static final String ORDER_READY_CODE_201 = "Order status changed to ready successfully";
    public static final String ORDER_READY_CODE_400 = "Invalid data to request order ready";
    public static final String ORDER_READY_CODE_403 = "User not authorized to change order status to ready";

    public static final String DELIVER_ORDER_OPERATION = "Order delivered";
    public static final String DELIVER_ORDER_CODE_201 = "Order status changed to delivered successfully";
    public static final String DELIVER_ORDER_CODE_400 = "Invalid data to request order delivered";
    public static final String DELIVER_ORDER_CODE_403 = "User not authorized to change order status to delivered";


    private ConstDocumentation() {
    }
}
