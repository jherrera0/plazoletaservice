package com.backendchallenge.plazoletaservice.application.jpa.repository;

import com.backendchallenge.plazoletaservice.application.jpa.entity.OrderEntity;
import com.backendchallenge.plazoletaservice.application.jpa.entity.RestaurantEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface IOrderRepository extends JpaRepository<OrderEntity, Long> {
    boolean existsByIdClient(Long idClient);

    boolean existsByIdClientAndStatusIsLikeOrStatusIsLike(Long idClient, String status, String status1);

    OrderEntity findByIdClientAndRestaurantAndStatusIsLike(Long idClient, RestaurantEntity restaurant, String status);
}
