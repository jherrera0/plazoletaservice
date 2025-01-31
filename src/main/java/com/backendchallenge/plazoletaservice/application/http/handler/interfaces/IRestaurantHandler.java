package com.backendchallenge.plazoletaservice.application.http.handler.interfaces;

import com.backendchallenge.plazoletaservice.application.http.dto.request.CreateRestaurantRequest;

public interface IRestaurantHandler {
    void createRestaurant(CreateRestaurantRequest request);
}
