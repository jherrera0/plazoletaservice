package com.backendchallenge.plazoletaservice.application.jpa.adapter;

import com.backendchallenge.plazoletaservice.application.jpa.entity.RestaurantEntity;
import com.backendchallenge.plazoletaservice.application.jpa.mapper.IRestaurantEntityMapper;
import com.backendchallenge.plazoletaservice.application.jpa.repository.IRestaurantRepository;
import com.backendchallenge.plazoletaservice.domain.model.Restaurant;
import com.backendchallenge.plazoletaservice.domain.spi.IRestaurantPersistencePort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RestaurantJpaAdapter implements IRestaurantPersistencePort {
    private final IRestaurantEntityMapper restaurantEntityMapper;
    private final IRestaurantRepository restaurantRepository;
    @Override
    public void createRestaurant(Restaurant restaurant) {
        RestaurantEntity restaurantEntity = restaurantEntityMapper.toEntity(restaurant);
        restaurantRepository.save(restaurantEntity);
    }
}
