package com.backendchallenge.plazoletaservice.application.jpa.repository;

import com.backendchallenge.plazoletaservice.application.jpa.entity.OrderedDishEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IOrderedDishRepository extends JpaRepository<OrderedDishEntity, Long> {
}
