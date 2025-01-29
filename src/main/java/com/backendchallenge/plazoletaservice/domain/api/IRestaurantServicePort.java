package com.backendchallenge.plazoletaservice.domain.api;

import com.backendchallenge.plazoletaservice.domain.model.Restaurant;

public interface IRestaurantServicePort {
    void createRestaurant(Restaurant restaurant);
}
