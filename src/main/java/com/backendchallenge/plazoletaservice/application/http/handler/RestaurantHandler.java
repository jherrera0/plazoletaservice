package com.backendchallenge.plazoletaservice.application.http.handler;

import com.backendchallenge.plazoletaservice.application.http.dto.request.CreateRestaurantRequest;
import com.backendchallenge.plazoletaservice.application.http.dto.request.ListRestaurantsRequest;
import com.backendchallenge.plazoletaservice.application.http.dto.response.PageResponse;
import com.backendchallenge.plazoletaservice.application.http.dto.response.RestaurantCustomResponse;
import com.backendchallenge.plazoletaservice.application.http.handler.interfaces.IRestaurantHandler;
import com.backendchallenge.plazoletaservice.application.http.mapper.ICreateRestaurantRequestMapper;
import com.backendchallenge.plazoletaservice.application.http.mapper.IPageResponseMapper;
import com.backendchallenge.plazoletaservice.domain.api.IRestaurantServicePort;
import com.backendchallenge.plazoletaservice.domain.until.TokenHolder;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class RestaurantHandler implements IRestaurantHandler {
    private final IRestaurantServicePort restaurantServicePort;
    private final ICreateRestaurantRequestMapper createRestaurantRequestMapper;
    private final IPageResponseMapper pageResponseMapper;
    @Override
    public void createRestaurant(CreateRestaurantRequest request) {
        TokenHolder.getToken();
        restaurantServicePort.createRestaurant(createRestaurantRequestMapper.toDomain(request));
    }

    @Override
    public PageResponse<RestaurantCustomResponse> listRestaurants(ListRestaurantsRequest request) {
        return pageResponseMapper.toPageResponseOfCustomRestaurant(restaurantServicePort.listRestaurants(request.getLimitForPage(),request.getOrderDirection(),request.getCurrentPage()));
    }

    @Override
    public void createEmployee(Long userId, Long restaurantId) {
        TokenHolder.getToken();
        restaurantServicePort.createEmployee(userId,restaurantId);
    }
}
