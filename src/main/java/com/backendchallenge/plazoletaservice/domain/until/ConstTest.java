package com.backendchallenge.plazoletaservice.domain.until;

import com.backendchallenge.plazoletaservice.domain.model.Category;

import java.util.List;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

public class ConstTest {

    public static final Long ID_TEST = 1L;
    public static final String NAME_TEST = "Test Restaurant";
    public static final String NIT_TEST = "123456789";
    public static final String ADDRESS_TEST = "123 Test St";
    public static final String PHONE_TEST = "3244710909";
    public static final String URL_LOGO_TEST = "http://test.com/logo.png";
    public static final String EMPTY_TEST = "";
    public static final String INVALID_NAME_TEST = "Invalid@Name";
    public static final String INVALID_PHONE_TEST = "InvalidPhone";
    public static final String INVALID_NIT_TEST = "InvalidNIT";

    public static final String DISH_NAME_TEST = "Test Dish";
    public static final Integer DISH_PRICE_TEST = 10000;
    public static final String DISH_CATEGORY_TEST = "Test Category";
    public static final String DISH_URL_IMAGE_TEST = "http://test.com/dish.png";
    public static final String DISH_DESCRIPTION_TEST = "Test Description";

    public static final String OWNER_ID_TEST = "100";
    public static final String OWNER_NAME_LABEL = "ownerId";


    public static final String DISH_NAME_EMPTY = "";
    public static final Integer DISH_PRICE_NULL = null;
    public static final String DISH_DESCRIPTION_EMPTY = "";
    public static final String DISH_URL_IMAGE_EMPTY = "";
    public static final String DISH_CATEGORY_EMPTY = "";
    public static final Integer DISH_PRICE_INVALID = -1;
    public static final Boolean AVAILABLE_TEST = true;
    public static final String CATEGORY_DESCRIPTION_TEST = "Test Category Description";
    public static final String CATEGORY_NAME_TEST = "Test Category Name";
    public static final List<Category> CATEGORIES_TEST = List.of(new Category(CATEGORY_NAME_TEST, CATEGORY_DESCRIPTION_TEST));

    public static final String FIELD1 = "field1";
    public static final String FIELD2 = "field2";
    public static final String ERROR1 = "error1";
    public static final String ERROR2 = "error2";

    public static final String INVALID_TOKEN = "invalid.token.here";
    public static final String USER_VALID = "valid@valid.com";
    public static final String VALID_TOKEN = "valid.token.here";
    public static final String ROLE_NAME_TEST = "RoleTest";
    public static final @NotNull Boolean DISH_STATUS_TEST = true;
    public static final @PositiveOrZero Integer CURRENT_PAGE_TEST = 0;
    public static final @Positive Integer LIMIT_FOR_PAGE_TEST = 3;
    public static final int PAGE_SIZE_TEST = 10;
    public static final Integer PAGE_SIZE_TEST_EXCEEDED = 11;
    public static final int INVALID_PAGE_SIZE_TEST = 0;
    public static final int INVALID_CURRENT_PAGE_TEST = -1;
    public static final Integer TOTAL_PAGES = 5;
    public static final String FILTER_BY_TEST = "category,category2";
    public static final String ORDER_DIRECTION_TEST = "asc";
    public static final String ORDER_DIRECTION_INVALID = "invalid";
    public static final Long INVALID_ID = -1L;
    public static final Long ID_2_TEST = 2L;


    private ConstTest() {
    }
}
