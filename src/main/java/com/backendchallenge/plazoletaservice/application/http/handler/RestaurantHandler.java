package com.backendchallenge.plazoletaservice.application.http.handler;

import com.backendchallenge.plazoletaservice.application.http.dto.CreateRestaurantRequest;
import com.backendchallenge.plazoletaservice.application.http.handler.interfaces.IRestaurantHandler;
import com.backendchallenge.plazoletaservice.application.http.mapper.ICreateRestaurantRequestMapper;
import com.backendchallenge.plazoletaservice.domain.api.IRestaurantServicePort;
import com.backendchallenge.plazoletaservice.domain.until.TokenHolder;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class RestaurantHandler implements IRestaurantHandler {
    private final IRestaurantServicePort restaurantServicePort;
    private final ICreateRestaurantRequestMapper createRestaurantRequestMapper;
    @Override
    public void createRestaurant(CreateRestaurantRequest request, String token) {
        TokenHolder.setToken(token);
        restaurantServicePort.createRestaurant(createRestaurantRequestMapper.toDomain(request));

    }
}
