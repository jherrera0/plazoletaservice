package com.backendchallenge.plazoletaservice.application.jpa.adapter;

import com.backendchallenge.plazoletaservice.application.jpa.entity.DishEntity;
import com.backendchallenge.plazoletaservice.application.jpa.entity.RestaurantEntity;
import com.backendchallenge.plazoletaservice.application.jpa.mapper.IDishEntityMapper;
import com.backendchallenge.plazoletaservice.application.jpa.repository.IDishRepository;
import com.backendchallenge.plazoletaservice.application.jpa.repository.IRestaurantRepository;
import com.backendchallenge.plazoletaservice.domain.model.Dish;
import com.backendchallenge.plazoletaservice.domain.until.ConstTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DishJpaAdapterTest {

    private IDishRepository dishRepository;
    private IRestaurantRepository restaurantRepository;
    private IDishEntityMapper dishEntityMapper;
    private DishJpaAdapter dishJpaAdapter;

    @BeforeEach
    void setUp() {
        dishRepository = mock(IDishRepository.class);
        restaurantRepository = mock(IRestaurantRepository.class);
        dishEntityMapper = mock(IDishEntityMapper.class);
        dishJpaAdapter = new DishJpaAdapter(dishRepository, restaurantRepository, dishEntityMapper);
    }

    @Test
    void createDish_savesDishSuccessfully() {
        Dish dish = new Dish(ConstTest.ID_TEST, ConstTest.ID_TEST, ConstTest.DISH_NAME_TEST, ConstTest.DISH_PRICE_TEST, ConstTest.DISH_DESCRIPTION_TEST, ConstTest.DISH_URL_IMAGE_TEST, ConstTest.DISH_CATEGORY_TEST);
        DishEntity dishEntity = new DishEntity();
        RestaurantEntity restaurantEntity = new RestaurantEntity();
        restaurantEntity.setId(ConstTest.ID_TEST);

        when(dishEntityMapper.toEntity(dish)).thenReturn(dishEntity);
        when(restaurantRepository.findById(ConstTest.ID_TEST)).thenReturn(java.util.Optional.of(restaurantEntity));

        Boolean result = dishJpaAdapter.createDish(dish);

        assertTrue(result);
        verify(dishRepository, times(1)).save(dishEntity);
    }

    @Test
    void createDish_returnsFalseWhenRestaurantNotFound() {
        Dish dish = new Dish(ConstTest.ID_TEST, ConstTest.ID_TEST, ConstTest.DISH_NAME_TEST, ConstTest.DISH_PRICE_TEST, ConstTest.DISH_DESCRIPTION_TEST, ConstTest.DISH_URL_IMAGE_TEST, ConstTest.DISH_CATEGORY_TEST);
        DishEntity dishEntity = new DishEntity();

        when(dishEntityMapper.toEntity(dish)).thenReturn(dishEntity);
        when(restaurantRepository.findById(ConstTest.ID_TEST)).thenReturn(java.util.Optional.empty());

        Boolean result = dishJpaAdapter.createDish(dish);

        assertFalse(result);
        verify(dishRepository, never()).save(dishEntity);
    }

    @Test
    void createDish_throwsExceptionWhenDishIsNull() {
        assertThrows(NullPointerException.class, () -> dishJpaAdapter.createDish(null));
    }
}