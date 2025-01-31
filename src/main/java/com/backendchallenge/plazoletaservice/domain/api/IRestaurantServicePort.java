package com.backendchallenge.plazoletaservice.domain.api;

import com.backendchallenge.plazoletaservice.domain.model.PageCustom;
import com.backendchallenge.plazoletaservice.domain.model.Restaurant;

public interface IRestaurantServicePort {
    PageCustom<Restaurant> listRestaurants(Integer pageSize, String orderDirection, Integer currentPage);
    void createRestaurant(Restaurant restaurant);
}
