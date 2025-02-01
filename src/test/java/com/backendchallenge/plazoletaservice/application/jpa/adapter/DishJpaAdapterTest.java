package com.backendchallenge.plazoletaservice.application.jpa.adapter;

import com.backendchallenge.plazoletaservice.application.jpa.entity.DishEntity;
import com.backendchallenge.plazoletaservice.application.jpa.entity.RestaurantEntity;
import com.backendchallenge.plazoletaservice.application.jpa.mapper.IDishEntityMapper;
import com.backendchallenge.plazoletaservice.application.jpa.repository.IDishRepository;
import com.backendchallenge.plazoletaservice.application.jpa.repository.IRestaurantRepository;
import com.backendchallenge.plazoletaservice.domain.model.Category;
import com.backendchallenge.plazoletaservice.domain.model.Dish;
import com.backendchallenge.plazoletaservice.domain.model.PageCustom;
import com.backendchallenge.plazoletaservice.domain.until.ConstTest;
import com.backendchallenge.plazoletaservice.domain.until.ConstValidation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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
        Dish dish = new Dish(ConstTest.ID_TEST, ConstTest.ID_TEST, ConstTest.DISH_NAME_TEST, ConstTest.DISH_PRICE_TEST,
                ConstTest.DISH_DESCRIPTION_TEST, ConstTest.DISH_URL_IMAGE_TEST, ConstTest.CATEGORIES_TEST);
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
        when(dishEntityMapper.toDomain(dishEntity)).thenReturn(new Dish(ConstTest.ID_TEST, ConstTest.ID_TEST,
                ConstTest.DISH_NAME_TEST, ConstTest.DISH_PRICE_TEST,
                ConstTest.DISH_DESCRIPTION_TEST, ConstTest.DISH_URL_IMAGE_TEST, ConstTest.CATEGORIES_TEST));

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

    @Test
    void getDishesByRestaurant_returnsDishesPageSuccessfully() {
        Long restaurantId = ConstTest.ID_TEST;
        Integer currentPage = ConstTest.CURRENT_PAGE_TEST;
        Integer pageSize = ConstTest.PAGE_SIZE_TEST;
        String filterBy = ConstTest.FILTER_BY_TEST;
        String orderDirection = ConstTest.ORDER_DIRECTION_TEST;
        List<DishEntity> dishEntities = List.of(new DishEntity());
        Page<DishEntity> page = mock(Page.class);

        when(page.getNumber()).thenReturn(currentPage);
        when(page.getSize()).thenReturn(pageSize);
        when(page.getTotalPages()).thenReturn(1);
        when(page.getContent()).thenReturn(dishEntities);
        when(dishRepository.findAllDishesByRestaurantAndFilter(eq(restaurantId), anyList(), any(Pageable.class))).thenReturn(page);
        when(dishEntityMapper.toDomainList(dishEntities)).thenReturn(List.of(new Dish()));

        PageCustom<Dish> result = dishJpaAdapter.getDishesByRestaurant(restaurantId, currentPage, pageSize, filterBy, orderDirection);

        assertNotNull(result);
        assertEquals(currentPage, result.getCurrentPage());
        assertEquals(pageSize, result.getPageSize());
        assertEquals(ConstValidation.ONE, result.getTotalPages());
        assertNotNull(result.getItems());
        assertFalse(result.getItems().isEmpty());
    }

    @Test
    void getDishesByRestaurant_returnsEmptyPageWhenNoDishesFound() {
        Long restaurantId = ConstTest.ID_TEST;
        Integer currentPage = ConstTest.CURRENT_PAGE_TEST;
        Integer pageSize = ConstTest.PAGE_SIZE_TEST;
        String filterBy = ConstTest.FILTER_BY_TEST;
        String orderDirection = ConstTest.ORDER_DIRECTION_TEST;
        List<DishEntity> emptyList = List.of();
        Page<DishEntity> page = mock(Page.class);

        when(page.getNumber()).thenReturn(currentPage);
        when(page.getSize()).thenReturn(pageSize);
        when(page.getTotalPages()).thenReturn(ConstValidation.ZERO);
        when(page.getContent()).thenReturn(emptyList);
        when(dishRepository.findAllDishesByRestaurantAndFilter(eq(restaurantId), anyList(), any(Pageable.class)))
                .thenReturn(page);
        when(dishEntityMapper.toDomainList(emptyList)).thenReturn(List.of());

        PageCustom<Dish> result = dishJpaAdapter.getDishesByRestaurant(restaurantId,
                currentPage, pageSize, filterBy, orderDirection);

        assertNotNull(result);
        assertEquals(currentPage, result.getCurrentPage());
        assertEquals(pageSize, result.getPageSize());
        assertEquals(ConstValidation.ZERO, result.getTotalPages());
        assertNotNull(result.getItems());
        assertTrue(result.getItems().isEmpty());
    }

    @Test
    void getDishesByRestaurant_handlesInvalidPageRequest() {
        Long restaurantId = ConstTest.ID_TEST;
        Integer currentPage = 10;
        Integer pageSize = 5;
        String filterBy = ConstTest.FILTER_BY_TEST;
        String orderDirection = ConstTest.ORDER_DIRECTION_TEST;
        Page<DishEntity> page = mock(Page.class);

        when(page.getTotalPages()).thenReturn(ConstValidation.ONE);
        when(dishRepository.findAllDishesByRestaurantAndFilter(eq(restaurantId), anyList(), any(Pageable.class))).
                thenReturn(page);

        PageCustom<Dish> result = dishJpaAdapter.getDishesByRestaurant(restaurantId, currentPage, pageSize, filterBy,
                orderDirection);

        assertNotNull(result);
        assertEquals(ConstValidation.MINUS_ONE, result.getCurrentPage().intValue());
        assertNull(result.getPageSize());
        assertNull(result.getTotalPages());
        assertNull(result.getItems());
    }
}