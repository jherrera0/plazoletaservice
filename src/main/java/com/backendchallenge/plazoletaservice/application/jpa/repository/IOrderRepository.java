package com.backendchallenge.plazoletaservice.application.jpa.repository;

import com.backendchallenge.plazoletaservice.application.jpa.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IOrderRepository extends JpaRepository<OrderEntity, Long> {
}
