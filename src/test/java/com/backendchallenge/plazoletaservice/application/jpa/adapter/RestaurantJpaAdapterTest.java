package com.backendchallenge.plazoletaservice.application.jpa.adapter;

import com.backendchallenge.plazoletaservice.application.jpa.entity.RestaurantEntity;
import com.backendchallenge.plazoletaservice.application.jpa.mapper.IRestaurantEntityMapper;
import com.backendchallenge.plazoletaservice.application.jpa.repository.IRestaurantRepository;
import com.backendchallenge.plazoletaservice.domain.model.Restaurant;
import com.backendchallenge.plazoletaservice.domain.until.ConstTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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

}