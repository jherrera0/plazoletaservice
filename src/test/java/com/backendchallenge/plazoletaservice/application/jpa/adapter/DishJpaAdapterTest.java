package com.backendchallenge.plazoletaservice.application.jpa.adapter;

import com.backendchallenge.plazoletaservice.application.jpa.entity.DishEntity;
import com.backendchallenge.plazoletaservice.application.jpa.entity.RestaurantEntity;
import com.backendchallenge.plazoletaservice.application.jpa.mapper.IDishEntityMapper;
import com.backendchallenge.plazoletaservice.application.jpa.repository.IDishRepository;
import com.backendchallenge.plazoletaservice.application.jpa.repository.IRestaurantRepository;
import com.backendchallenge.plazoletaservice.domain.model.Category;
import com.backendchallenge.plazoletaservice.domain.model.Dish;
import com.backendchallenge.plazoletaservice.domain.until.ConstTest;
import com.backendchallenge.plazoletaservice.domain.until.ConstValidation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

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
        Dish dish = new Dish(ConstTest.ID_TEST, ConstTest.ID_TEST, ConstTest.DISH_NAME_TEST, ConstTest.DISH_PRICE_TEST,
                ConstTest.DISH_DESCRIPTION_TEST, ConstTest.DISH_URL_IMAGE_TEST,
                List.of(new Category(ConstTest.ID_TEST,ConstTest.CATEGORY_NAME_TEST, ConstTest.CATEGORY_DESCRIPTION_TEST)));
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
        Dish dish = new Dish(ConstTest.ID_TEST, ConstTest.ID_TEST, ConstTest.DISH_NAME_TEST, ConstTest.DISH_PRICE_TEST
                , ConstTest.DISH_DESCRIPTION_TEST,
                ConstTest.DISH_URL_IMAGE_TEST,
                List.of(new Category(ConstTest.ID_TEST,ConstTest.CATEGORY_NAME_TEST,
                        ConstTest.CATEGORY_DESCRIPTION_TEST)));
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
    @Test
    void findDishById_returnsTrueWhenDishExists() {
        when(dishRepository.existsById(ConstTest.ID_TEST)).thenReturn(true);

        boolean result = dishJpaAdapter.findDishById(ConstTest.ID_TEST);

        assertTrue(result);
    }

    @Test
    void findDishById_returnsFalseWhenDishDoesNotExist() {
        when(dishRepository.existsById(ConstTest.ID_TEST)).thenReturn(false);

        boolean result = dishJpaAdapter.findDishById(ConstTest.ID_TEST);

        assertFalse(result);
    }

    @Test
    void updateDish_updatesDishSuccessfully() {
        DishEntity dishEntity = new DishEntity();
        dishEntity.setId(ConstTest.ID_TEST);

        when(dishRepository.findById(ConstTest.ID_TEST)).thenReturn(java.util.Optional.of(dishEntity));

        dishJpaAdapter.updateDish(ConstTest.ID_TEST, ConstTest.DISH_DESCRIPTION_TEST, ConstTest.DISH_PRICE_TEST);

        verify(dishRepository, times(1)).save(dishEntity);
        assertEquals(ConstTest.DISH_DESCRIPTION_TEST, dishEntity.getDescription());
        assertEquals(ConstTest.DISH_PRICE_TEST, dishEntity.getPrice());
    }

    @Test
    void updateDish_doesNotUpdateWhenDishNotFound() {
        when(dishRepository.findById(ConstTest.ID_TEST)).thenReturn(java.util.Optional.empty());

        dishJpaAdapter.updateDish(ConstTest.ID_TEST, ConstTest.DISH_DESCRIPTION_TEST, ConstTest.DISH_PRICE_TEST);

        verify(dishRepository, never()).save(any(DishEntity.class));
    }

    @Test
    void getRestaurantIdByDishId_returnsRestaurantIdSuccessfully() {
        RestaurantEntity restaurantEntity = new RestaurantEntity();
        restaurantEntity.setId(ConstTest.ID_TEST);
        DishEntity dishEntity = new DishEntity();
        dishEntity.setId(ConstTest.ID_TEST);
        dishEntity.setRestaurant(restaurantEntity);

        when(dishRepository.findById(ConstTest.ID_TEST)).thenReturn(java.util.Optional.of(dishEntity));

        Long result = dishJpaAdapter.getRestaurantIdByDishId(ConstTest.ID_TEST);

        assertEquals(ConstTest.ID_TEST, result);
    }
    @Test
    void getRestaurantIdByDishId_returnsZeroWhenDishNotFound() {
        when(dishRepository.findById(ConstTest.ID_TEST)).thenReturn(java.util.Optional.empty());

        Long result = dishJpaAdapter.getRestaurantIdByDishId(ConstTest.ID_TEST);

        assertEquals(ConstValidation.ZERO.longValue(), result);
    }
    @Test
    void changeDishStatus_updatesStatusSuccessfully() {
        Dish dish = new Dish(ConstTest.ID_TEST, ConstTest.ID_TEST, ConstTest.DISH_NAME_TEST, ConstTest.DISH_PRICE_TEST, ConstTest.DISH_DESCRIPTION_TEST, ConstTest.DISH_URL_IMAGE_TEST, ConstTest.DISH_CATEGORY_TEST);
        DishEntity dishEntity = new DishEntity();
        RestaurantEntity restaurantEntity = new RestaurantEntity();
        restaurantEntity.setId(ConstTest.ID_TEST);

        when(dishEntityMapper.toEntity(dish)).thenReturn(dishEntity);
        when(restaurantRepository.findById(ConstTest.ID_TEST)).thenReturn(java.util.Optional.of(restaurantEntity));

        dishJpaAdapter.changeDishStatus(dish, ConstTest.ID_TEST);

        verify(dishRepository, times(1)).save(dishEntity);
    }

    @Test
    void getDishById_returnsDishSuccessfully() {
        DishEntity dishEntity = new DishEntity();
        dishEntity.setId(ConstTest.ID_TEST);

        when(dishRepository.findById(ConstTest.ID_TEST)).thenReturn(java.util.Optional.of(dishEntity));
        when(dishEntityMapper.toDomain(dishEntity)).thenReturn(new Dish(ConstTest.ID_TEST, ConstTest.ID_TEST, ConstTest.DISH_NAME_TEST, ConstTest.DISH_PRICE_TEST, ConstTest.DISH_DESCRIPTION_TEST, ConstTest.DISH_URL_IMAGE_TEST, ConstTest.DISH_CATEGORY_TEST));

        Dish result = dishJpaAdapter.getDishById(ConstTest.ID_TEST);

        assertNotNull(result);
        assertEquals(ConstTest.ID_TEST, result.getId());
    }

    @Test
    void getDishById_returnsEmptyDishWhenNotFound() {
        when(dishRepository.findById(ConstTest.ID_TEST)).thenReturn(java.util.Optional.empty());
        when(dishEntityMapper.toDomain(any(DishEntity.class))).thenReturn(new Dish());

        Dish result = dishJpaAdapter.getDishById(ConstTest.ID_TEST);

        assertNotNull(result);
        assertNull(result.getId());
    }
}