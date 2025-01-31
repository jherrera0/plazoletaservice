package com.backendchallenge.plazoletaservice.application.http.handler;

import com.backendchallenge.plazoletaservice.application.http.dto.request.CreateRestaurantRequest;
import com.backendchallenge.plazoletaservice.application.http.mapper.ICreateRestaurantRequestMapper;
import com.backendchallenge.plazoletaservice.application.http.mapper.IPageResponseMapper;
import com.backendchallenge.plazoletaservice.domain.api.IRestaurantServicePort;
import com.backendchallenge.plazoletaservice.domain.model.Restaurant;
import com.backendchallenge.plazoletaservice.domain.until.ConstTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class RestaurantHandlerTest {

    private IRestaurantServicePort restaurantServicePort;
    private ICreateRestaurantRequestMapper createRestaurantRequestMapper;
    private IPageResponseMapper pageResponseMapper;
    private RestaurantHandler restaurantHandler;

    @BeforeEach
    void setUp() {
        restaurantServicePort = mock(IRestaurantServicePort.class);
        createRestaurantRequestMapper = mock(ICreateRestaurantRequestMapper.class);
        restaurantHandler = new RestaurantHandler(restaurantServicePort,
                createRestaurantRequestMapper,
                pageResponseMapper);
    }

    @Test
    void createRestaurant_callsServicePortWithMappedRequest() {
        CreateRestaurantRequest request = new CreateRestaurantRequest();
        Restaurant restaurant = new Restaurant(
                ConstTest.ID_TEST,
                ConstTest.NAME_TEST,
                ConstTest.NIT_TEST,
                ConstTest.ADDRESS_TEST,
                ConstTest.PHONE_TEST,
                ConstTest.URL_LOGO_TEST,
                ConstTest.ID_TEST
        );

        when(createRestaurantRequestMapper.toDomain(request)).thenReturn(restaurant);

        restaurantHandler.createRestaurant(request);

        verify(restaurantServicePort, times(1)).createRestaurant(restaurant);
    }
}