package com.backendchallenge.plazoletaservice.domain.usecase;

import com.backendchallenge.plazoletaservice.domain.api.IDishServicePort;
import com.backendchallenge.plazoletaservice.domain.exceptions.dishexceptions.*;
import com.backendchallenge.plazoletaservice.domain.exceptions.restaurantexceptions.OwnerNotFoundException;
import com.backendchallenge.plazoletaservice.domain.exceptions.restaurantexceptions.RestaurantNotFoundException;
import com.backendchallenge.plazoletaservice.domain.model.Dish;
import com.backendchallenge.plazoletaservice.domain.spi.IDishPersistencePort;
import com.backendchallenge.plazoletaservice.domain.spi.IRestaurantPersistencePort;
import com.backendchallenge.plazoletaservice.domain.spi.IUserPersistencePort;
import com.backendchallenge.plazoletaservice.domain.until.ConstTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class DishCaseTest {

    private IDishPersistencePort dishPersistencePort;
    private IRestaurantPersistencePort restaurantPersistencePort;
    private IUserPersistencePort userPersistencePort;
    private IDishServicePort dishServicePort;

    @BeforeEach
    void setUp() {
        dishPersistencePort = mock(IDishPersistencePort.class);
        restaurantPersistencePort = mock(IRestaurantPersistencePort.class);
        userPersistencePort = mock(IUserPersistencePort.class);
        dishServicePort = new DishCase(dishPersistencePort, restaurantPersistencePort, userPersistencePort);
    }

    @Test
    void createDish_Success() {
        Dish dish = new Dish();
        dish.setIdRestaurant(ConstTest.ID_TEST);
        dish.setName(ConstTest.DISH_NAME_TEST);
        dish.setPrice(ConstTest.DISH_PRICE_TEST);
        dish.setDescription(ConstTest.DISH_DESCRIPTION_TEST);
        dish.setUrlImage(ConstTest.DISH_URL_IMAGE_TEST);
        dish.setCategory(ConstTest.DISH_CATEGORY_TEST);

        when(userPersistencePort.findOwnerById(ConstTest.ID_TEST)).thenReturn(true);
        when(restaurantPersistencePort.existsRestaurantByIdAndOwner(ConstTest.ID_TEST, ConstTest.ID_TEST)).thenReturn(true);
        when(dishPersistencePort.createDish(dish)).thenReturn(true);

        dishServicePort.createDish(dish, ConstTest.ID_TEST);

        verify(dishPersistencePort, times(1)).createDish(dish);
    }

    @Test
    void createDish_OwnerNotFound() {
        Dish dish = new Dish();
        dish.setIdRestaurant(ConstTest.ID_TEST);

        when(userPersistencePort.findOwnerById(ConstTest.ID_TEST)).thenReturn(false);

        assertThrows(OwnerNotFoundException.class, () -> dishServicePort.createDish(dish, ConstTest.ID_TEST));
    }

    @Test
    void createDish_RestaurantNotFound() {
        Dish dish = new Dish();
        dish.setIdRestaurant(ConstTest.ID_TEST);

        when(userPersistencePort.findOwnerById(ConstTest.ID_TEST)).thenReturn(true);
        when(restaurantPersistencePort.existsRestaurantByIdAndOwner(ConstTest.ID_TEST, ConstTest.ID_TEST)).thenReturn(false);

        assertThrows(RestaurantNotFoundException.class, () -> dishServicePort.createDish(dish, ConstTest.ID_TEST));
    }

    @Test
    void createDish_DishNameEmpty() {
        Dish dish = new Dish();
        dish.setIdRestaurant(ConstTest.ID_TEST);
        dish.setName(ConstTest.DISH_NAME_EMPTY);

        when(userPersistencePort.findOwnerById(ConstTest.ID_TEST)).thenReturn(true);
        when(restaurantPersistencePort.existsRestaurantByIdAndOwner(ConstTest.ID_TEST, ConstTest.ID_TEST)).thenReturn(true);

        assertThrows(DishNameEmptyException.class, () -> dishServicePort.createDish(dish, ConstTest.ID_TEST));
    }

    @Test
    void createDish_DishPriceEmpty() {
        Dish dish = new Dish();
        dish.setIdRestaurant(ConstTest.ID_TEST);
        dish.setName(ConstTest.DISH_NAME_TEST);
        dish.setPrice(ConstTest.DISH_PRICE_NULL);

        when(userPersistencePort.findOwnerById(ConstTest.ID_TEST)).thenReturn(true);
        when(restaurantPersistencePort.existsRestaurantByIdAndOwner(ConstTest.ID_TEST, ConstTest.ID_TEST)).thenReturn(true);

        assertThrows(DishPriceEmptyException.class, () -> dishServicePort.createDish(dish, ConstTest.ID_TEST));
    }

    @Test
    void createDish_DishDescriptionEmpty() {
        Dish dish = new Dish();
        dish.setIdRestaurant(ConstTest.ID_TEST);
        dish.setName(ConstTest.DISH_NAME_TEST);
        dish.setPrice(ConstTest.DISH_PRICE_TEST);
        dish.setDescription(ConstTest.DISH_DESCRIPTION_EMPTY);

        when(userPersistencePort.findOwnerById(ConstTest.ID_TEST)).thenReturn(true);
        when(restaurantPersistencePort.existsRestaurantByIdAndOwner(ConstTest.ID_TEST, ConstTest.ID_TEST)).thenReturn(true);

        assertThrows(DishDescriptionEmptyException.class, () -> dishServicePort.createDish(dish, ConstTest.ID_TEST));
    }

    @Test
    void createDish_DishUrlImageEmpty() {
        Dish dish = new Dish();
        dish.setIdRestaurant(ConstTest.ID_TEST);
        dish.setName(ConstTest.DISH_NAME_TEST);
        dish.setPrice(ConstTest.DISH_PRICE_TEST);
        dish.setDescription(ConstTest.DISH_DESCRIPTION_TEST);
        dish.setUrlImage(ConstTest.DISH_URL_IMAGE_EMPTY);

        when(userPersistencePort.findOwnerById(ConstTest.ID_TEST)).thenReturn(true);
        when(restaurantPersistencePort.existsRestaurantByIdAndOwner(ConstTest.ID_TEST, ConstTest.ID_TEST)).thenReturn(true);

        assertThrows(DishUrlImageEmptyException.class, () -> dishServicePort.createDish(dish, ConstTest.ID_TEST));
    }

    @Test
    void createDish_DishCategoryEmpty() {
        Dish dish = new Dish();
        dish.setIdRestaurant(ConstTest.ID_TEST);
        dish.setName(ConstTest.DISH_NAME_TEST);
        dish.setPrice(ConstTest.DISH_PRICE_TEST);
        dish.setDescription(ConstTest.DISH_DESCRIPTION_TEST);
        dish.setUrlImage(ConstTest.DISH_URL_IMAGE_TEST);
        dish.setCategory(ConstTest.DISH_CATEGORY_EMPTY);

        when(userPersistencePort.findOwnerById(ConstTest.ID_TEST)).thenReturn(true);
        when(restaurantPersistencePort.existsRestaurantByIdAndOwner(ConstTest.ID_TEST, ConstTest.ID_TEST)).thenReturn(true);

        assertThrows(DishCategoryEmptyException.class, () -> dishServicePort.createDish(dish, ConstTest.ID_TEST));
    }

    @Test
    void createDish_DishPriceInvalidValue() {
        Dish dish = new Dish();
        dish.setIdRestaurant(ConstTest.ID_TEST);
        dish.setName(ConstTest.DISH_NAME_TEST);
        dish.setPrice(ConstTest.DISH_PRICE_INVALID);
        dish.setDescription(ConstTest.DISH_DESCRIPTION_TEST);
        dish.setUrlImage(ConstTest.DISH_URL_IMAGE_TEST);
        dish.setCategory(ConstTest.DISH_CATEGORY_TEST);

        when(userPersistencePort.findOwnerById(ConstTest.ID_TEST)).thenReturn(true);
        when(restaurantPersistencePort.existsRestaurantByIdAndOwner(ConstTest.ID_TEST, ConstTest.ID_TEST)).thenReturn(true);

        assertThrows(DishPriceInvalidValueException.class, () -> dishServicePort.createDish(dish, ConstTest.ID_TEST));
    }

    @Test
    void createDish_RestaurantNotFound_DishPersistenceFailure() {
        Dish dish = new Dish();
        dish.setIdRestaurant(ConstTest.ID_TEST);
        dish.setName(ConstTest.DISH_NAME_TEST);
        dish.setPrice(ConstTest.DISH_PRICE_TEST);
        dish.setDescription(ConstTest.DISH_DESCRIPTION_TEST);
        dish.setUrlImage(ConstTest.DISH_URL_IMAGE_TEST);
        dish.setCategory(ConstTest.DISH_CATEGORY_TEST);

        when(userPersistencePort.findOwnerById(ConstTest.ID_TEST)).thenReturn(true);
        when(restaurantPersistencePort.existsRestaurantByIdAndOwner(ConstTest.ID_TEST, ConstTest.ID_TEST)).thenReturn(true);
        when(dishPersistencePort.createDish(dish)).thenReturn(false);

        assertThrows(RestaurantNotFoundException.class, () -> dishServicePort.createDish(dish, ConstTest.ID_TEST));
    }
}