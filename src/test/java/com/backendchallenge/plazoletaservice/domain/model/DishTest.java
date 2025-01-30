package com.backendchallenge.plazoletaservice.domain.model;

import com.backendchallenge.plazoletaservice.domain.until.ConstTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DishTest {

    @Test
    void dishCreation_Success() {
        Dish dish = new Dish(ConstTest.ID_TEST, ConstTest.ID_TEST, ConstTest.DISH_NAME_TEST, ConstTest.DISH_PRICE_TEST, ConstTest.DISH_DESCRIPTION_TEST, ConstTest.DISH_URL_IMAGE_TEST, ConstTest.DISH_CATEGORY_TEST);

        assertEquals(ConstTest.ID_TEST, dish.getId());
        assertEquals(ConstTest.ID_TEST, dish.getIdRestaurant());
        assertEquals(ConstTest.DISH_NAME_TEST, dish.getName());
        assertEquals(ConstTest.DISH_PRICE_TEST, dish.getPrice());
        assertEquals(ConstTest.DISH_DESCRIPTION_TEST, dish.getDescription());
        assertEquals(ConstTest.DISH_URL_IMAGE_TEST, dish.getUrlImage());
        assertEquals(ConstTest.DISH_CATEGORY_TEST, dish.getCategory());
    }

    @Test
    void dishSetters_Success() {
        Dish dish = new Dish();
        dish.setId(ConstTest.ID_TEST);
        dish.setIdRestaurant(ConstTest.ID_TEST);
        dish.setName(ConstTest.DISH_NAME_TEST);
        dish.setPrice(ConstTest.DISH_PRICE_TEST);
        dish.setDescription(ConstTest.DISH_DESCRIPTION_TEST);
        dish.setUrlImage(ConstTest.DISH_URL_IMAGE_TEST);
        dish.setCategory(ConstTest.DISH_CATEGORY_TEST);
        dish.setAvailable(ConstTest.AVAILABLE_TEST);

        assertEquals(ConstTest.ID_TEST, dish.getId());
        assertEquals(ConstTest.ID_TEST, dish.getIdRestaurant());
        assertEquals(ConstTest.DISH_NAME_TEST, dish.getName());
        assertEquals(ConstTest.DISH_PRICE_TEST, dish.getPrice());
        assertEquals(ConstTest.DISH_DESCRIPTION_TEST, dish.getDescription());
        assertEquals(ConstTest.DISH_URL_IMAGE_TEST, dish.getUrlImage());
        assertEquals(ConstTest.DISH_CATEGORY_TEST, dish.getCategory());
        assertEquals(ConstTest.AVAILABLE_TEST, dish.getAvailable());
    }

    @Test
    void dishName_Null() {
        Dish dish = new Dish();
        dish.setName(null);

        assertNull(dish.getName());
    }

    @Test
    void dishPrice_Negative() {
        Dish dish = new Dish();
        dish.setPrice(ConstTest.DISH_PRICE_INVALID);

        assertEquals(ConstTest.DISH_PRICE_INVALID, dish.getPrice());
    }

    @Test
    void dishUrlImage_Empty() {
        Dish dish = new Dish();
        dish.setUrlImage(ConstTest.DISH_URL_IMAGE_EMPTY);

        assertEquals(ConstTest.DISH_URL_IMAGE_EMPTY, dish.getUrlImage());
    }
}