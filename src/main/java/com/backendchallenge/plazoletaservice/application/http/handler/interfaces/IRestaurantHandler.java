package com.backendchallenge.plazoletaservice.application.http.handler.interfaces;

import com.backendchallenge.plazoletaservice.application.http.dto.request.CreateRestaurantRequest;
import com.backendchallenge.plazoletaservice.application.http.dto.response.PageResponse;
import com.backendchallenge.plazoletaservice.application.http.dto.response.RestaurantCustomResponse;

public interface IRestaurantHandler {
    void createRestaurant(CreateRestaurantRequest request);
    PageResponse<RestaurantCustomResponse> listRestaurants();
}
