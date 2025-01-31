package com.backendchallenge.plazoletaservice.application.jpa.adapter;

import com.backendchallenge.plazoletaservice.application.jpa.entity.RestaurantEntity;
import com.backendchallenge.plazoletaservice.application.jpa.mapper.IRestaurantEntityMapper;
import com.backendchallenge.plazoletaservice.application.jpa.repository.IRestaurantRepository;
import com.backendchallenge.plazoletaservice.domain.model.PageCustom;
import com.backendchallenge.plazoletaservice.domain.model.Restaurant;
import com.backendchallenge.plazoletaservice.domain.until.ConstTest;
import com.backendchallenge.plazoletaservice.domain.until.ConstValidation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.data.domain.Sort.Direction;
import static org.mockito.Mockito.*;

class RestaurantJpaAdapterTest {

    private IRestaurantEntityMapper restaurantEntityMapper;
    private IRestaurantRepository restaurantRepository;
    private RestaurantJpaAdapter restaurantJpaAdapter;

    @BeforeEach
    void setUp() {
        restaurantEntityMapper = mock(IRestaurantEntityMapper.class);
        restaurantRepository = mock(IRestaurantRepository.class);
        restaurantJpaAdapter = new RestaurantJpaAdapter(restaurantEntityMapper, restaurantRepository);
    }

    @Test
    void createRestaurant_savesRestaurantSuccessfully() {
        Restaurant restaurant = new Restaurant(
                ConstTest.ID_TEST,
                ConstTest.NAME_TEST,
                ConstTest.NIT_TEST,
                ConstTest.ADDRESS_TEST,
                ConstTest.PHONE_TEST,
                ConstTest.URL_LOGO_TEST,
                ConstTest.ID_TEST
        );
        RestaurantEntity restaurantEntity = new RestaurantEntity();

        when(restaurantEntityMapper.toEntity(restaurant)).thenReturn(restaurantEntity);

        restaurantJpaAdapter.createRestaurant(restaurant);

        verify(restaurantRepository, times(1)).save(restaurantEntity);
    }
    @Test
    void existsRestaurantByIdAndOwner_returnsTrueWhenExists() {
        when(restaurantRepository.existsByIdAndIdOwner(ConstTest.ID_TEST, ConstTest.ID_TEST)).thenReturn(true);

        boolean result = restaurantJpaAdapter.existsRestaurantByIdAndOwner(ConstTest.ID_TEST, ConstTest.ID_TEST);

        assertTrue(result);
    }

    @Test
    void existsRestaurantByIdAndOwner_returnsFalseWhenNotExists() {
        when(restaurantRepository.existsByIdAndIdOwner(ConstTest.ID_TEST, ConstTest.ID_TEST)).thenReturn(false);

        boolean result = restaurantJpaAdapter.existsRestaurantByIdAndOwner(ConstTest.ID_TEST, ConstTest.ID_TEST);

        assertFalse(result);
    }
    @Test
    void listRestaurants_returnsValidPageData() {
        RestaurantEntity restaurantEntity = new RestaurantEntity();
        List<RestaurantEntity> restaurantEntities = List.of(restaurantEntity);
        Page<RestaurantEntity> restaurantPage = mock(Page.class);
        when(restaurantRepository.findAll(PageRequest.of(ConstTest.CURRENT_PAGE_TEST, ConstTest.PAGE_SIZE_TEST, Sort.by(Direction.ASC, ConstValidation.NAME))))
                .thenReturn(restaurantPage);
        when(restaurantPage.getContent()).thenReturn(restaurantEntities);
        when(restaurantPage.getTotalPages()).thenReturn(ConstValidation.TWO);
        when(restaurantPage.getNumber()).thenReturn(ConstTest.CURRENT_PAGE_TEST);
        when(restaurantPage.getSize()).thenReturn(ConstTest.PAGE_SIZE_TEST);
        List<Restaurant> restaurants = List.of(new Restaurant());
        when(restaurantEntityMapper.toModelList(restaurantEntities)).thenReturn(restaurants);

        PageCustom<Restaurant> result = restaurantJpaAdapter.listRestaurants(ConstTest.PAGE_SIZE_TEST,
                ConstValidation.ASC, ConstTest.CURRENT_PAGE_TEST);

        assertEquals(ConstTest.CURRENT_PAGE_TEST, result.getCurrentPage());
        assertEquals(ConstTest.PAGE_SIZE_TEST, result.getPageSize());
        assertEquals(ConstValidation.TWO, result.getTotalPages());
        assertEquals(restaurants, result.getItems());
    }

    @Test
    void listRestaurants_returnsEmptyPage() {
        Page<RestaurantEntity> restaurantPage = mock(Page.class);
        when(restaurantRepository.findAll(PageRequest.of(ConstTest.CURRENT_PAGE_TEST, ConstTest.PAGE_SIZE_TEST,
                Sort.by(Direction.ASC, ConstValidation.NAME))))
                .thenReturn(restaurantPage);
        when(restaurantPage.getContent()).thenReturn(List.of());
        when(restaurantPage.getTotalPages()).thenReturn(ConstValidation.ZERO);
        when(restaurantPage.getNumber()).thenReturn(ConstTest.CURRENT_PAGE_TEST);
        when(restaurantPage.getSize()).thenReturn(ConstTest.PAGE_SIZE_TEST);

        PageCustom<Restaurant> result = restaurantJpaAdapter.listRestaurants(ConstTest.PAGE_SIZE_TEST,
                ConstValidation.ASC, ConstTest.CURRENT_PAGE_TEST);
        assertEquals(ConstTest.CURRENT_PAGE_TEST, result.getCurrentPage());
        assertEquals(ConstTest.PAGE_SIZE_TEST, result.getPageSize());
        assertEquals(ConstValidation.ZERO, result.getTotalPages());
        assertTrue(result.getItems().isEmpty());
    }

    @Test
    void listRestaurants_returnsMinusOneWhenPageExceedsTotal() {
        Page<RestaurantEntity> restaurantPage = mock(Page.class);
        when(restaurantRepository.findAll(PageRequest.of(ConstTest.CURRENT_PAGE_TEST, ConstTest.PAGE_SIZE_TEST,
                Sort.by(Direction.ASC, ConstValidation.NAME))))
                .thenReturn(restaurantPage);
        when(restaurantPage.getTotalPages()).thenReturn(ConstValidation.ZERO);
        when(restaurantPage.getNumber()).thenReturn(ConstTest.PAGE_SIZE_TEST_EXCEEDED);

        PageCustom<Restaurant> result = restaurantJpaAdapter.listRestaurants(ConstTest.PAGE_SIZE_TEST,
                ConstValidation.ASC, ConstTest.CURRENT_PAGE_TEST);
        assertEquals(ConstValidation.MINUS_ONE, result.getCurrentPage());
        assertNull(result.getPageSize());
        assertNull(result.getTotalPages());
        assertNull(result.getItems());
    }
}