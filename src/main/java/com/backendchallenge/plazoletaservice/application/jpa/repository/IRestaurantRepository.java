package com.backendchallenge.plazoletaservice.application.jpa.repository;

import com.backendchallenge.plazoletaservice.application.jpa.entity.RestaurantEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRestaurantRepository extends JpaRepository<RestaurantEntity, Long> {

}
