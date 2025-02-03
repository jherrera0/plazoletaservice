package com.backendchallenge.plazoletaservice.application.jpa.repository;

import com.backendchallenge.plazoletaservice.application.jpa.entity.RestaurantWorkersEntity;
import com.backendchallenge.plazoletaservice.application.jpa.entity.RestaurantsWorkerId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRestaurantsWorkersRepository extends JpaRepository<RestaurantWorkersEntity, RestaurantsWorkerId> {
    boolean existsByEmployedIdAndRestaurantId(Long employedId, Long restaurantId);
}
