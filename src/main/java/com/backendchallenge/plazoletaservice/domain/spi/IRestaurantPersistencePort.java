package com.backendchallenge.plazoletaservice.domain.spi;

import com.backendchallenge.plazoletaservice.domain.model.Page;
import com.backendchallenge.plazoletaservice.domain.model.Restaurant;

public interface IRestaurantPersistencePort {
    void createRestaurant(Restaurant restaurant);
    boolean existsRestaurantByIdAndOwner(Long idRestaurant, Long idOwner);
    Page<Restaurant> listRestaurants();
}
