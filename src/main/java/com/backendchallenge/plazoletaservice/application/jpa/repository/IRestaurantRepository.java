package com.backendchallenge.plazoletaservice.application.jpa.repository;

import com.backendchallenge.plazoletaservice.application.jpa.entity.RestaurantEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IRestaurantRepository extends JpaRepository<RestaurantEntity, Long> {
    @Query("SELECT CASE WHEN COUNT(r) > 0 THEN true ELSE false END FROM RestaurantEntity r WHERE r.id = :idRestaurant AND r.idOwner = :idOwner")
    boolean existsByIdAndIdOwner(Long idRestaurant, Long idOwner);}
