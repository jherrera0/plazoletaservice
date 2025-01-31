package com.backendchallenge.plazoletaservice.domain.api;

import com.backendchallenge.plazoletaservice.domain.model.Page;
import com.backendchallenge.plazoletaservice.domain.model.Restaurant;

public interface IRestaurantServicePort {
    Page<Restaurant> listRestaurants(Integer pageSize, String orderDirection);
    void createRestaurant(Restaurant restaurant);
}
