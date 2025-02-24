package com.backendchallenge.plazoletaservice.application.http.handler;

import com.backendchallenge.plazoletaservice.application.http.dto.request.CreateRestaurantRequest;
import com.backendchallenge.plazoletaservice.application.http.dto.request.ListRestaurantsRequest;
import com.backendchallenge.plazoletaservice.application.http.dto.response.PageResponse;
import com.backendchallenge.plazoletaservice.application.http.dto.response.RestaurantCustomResponse;
import com.backendchallenge.plazoletaservice.application.http.mapper.ICreateRestaurantRequestMapper;
import com.backendchallenge.plazoletaservice.application.http.mapper.IPageResponseMapper;
import com.backendchallenge.plazoletaservice.domain.api.IRestaurantServicePort;
import com.backendchallenge.plazoletaservice.domain.model.PageCustom;
import com.backendchallenge.plazoletaservice.domain.model.Restaurant;
import com.backendchallenge.plazoletaservice.domain.until.ConstTest;
import com.backendchallenge.plazoletaservice.domain.until.ConstValidation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
        pageResponseMapper = mock(IPageResponseMapper.class);
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


    @Test
    void listRestaurants_withValidRequest_shouldReturnMappedResponse() {
        ListRestaurantsRequest request = new ListRestaurantsRequest();
        request.setCurrentPage(ConstTest.CURRENT_PAGE_TEST);
        request.setLimitForPage(ConstTest.LIMIT_FOR_PAGE_TEST);
        request.setOrderDirection(ConstValidation.ASC);

        PageCustom<Restaurant> pageCustom = new PageCustom<>();

        PageResponse<RestaurantCustomResponse> expectedResponse = new PageResponse<>();
        when(restaurantServicePort.
                listRestaurants(ConstTest.LIMIT_FOR_PAGE_TEST, ConstValidation.ASC, ConstTest.CURRENT_PAGE_TEST)).
                thenReturn(pageCustom);
        when(pageResponseMapper.toPageResponseOfCustomRestaurant(pageCustom)).thenReturn(expectedResponse);

        PageResponse<RestaurantCustomResponse> actualResponse = restaurantHandler.listRestaurants(request);

        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void createEmployee_withValidRequest_shouldCallServicePort() {
        Long userId = ConstTest.ID_TEST;
        Long restaurantId = ConstTest.ID_TEST;

        restaurantHandler.createEmployee(userId, restaurantId);

        verify(restaurantServicePort, times(ConstValidation.ONE)).createEmployee(userId, restaurantId);
    }
}