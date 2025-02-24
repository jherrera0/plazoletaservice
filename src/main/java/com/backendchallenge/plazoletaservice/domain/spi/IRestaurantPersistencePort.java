package com.backendchallenge.plazoletaservice.domain.spi;

import com.backendchallenge.plazoletaservice.domain.model.PageCustom;
import com.backendchallenge.plazoletaservice.domain.model.Restaurant;

public interface IRestaurantPersistencePort {
    void createRestaurant(Restaurant restaurant);
    boolean existsRestaurantByIdAndOwner(Long idRestaurant, Long idOwner);
    PageCustom<Restaurant> listRestaurants(Integer pageSize, String orderDirection, Integer currentPage);
    boolean existsRestaurantById(Long restaurantId);
}
