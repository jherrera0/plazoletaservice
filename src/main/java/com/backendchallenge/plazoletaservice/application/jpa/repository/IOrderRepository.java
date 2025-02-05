package com.backendchallenge.plazoletaservice.application.jpa.repository;

import com.backendchallenge.plazoletaservice.application.jpa.entity.OrderEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface IOrderRepository extends JpaRepository<OrderEntity, Long> {
    boolean existsByIdClient(Long idClient);

    boolean existsByIdClientAndStatusIsLikeOrStatusIsLike(Long idClient, String status, String status1);

    @Query("""
    SELECT o FROM OrderEntity o
    WHERE o.restaurant.id = :restaurantId
    AND o.status = :filterBy
""")
    Page<OrderEntity> findAllByRestaurant_IdAndFilter(@Param("restaurantId") Long idRestaurant,
                                                      @Param("filterBy") String filterBy,
                                                      Pageable pageable);

    OrderEntity findByStatusAndIdClientAndRestaurant_Id(String status, Long idClient, Long restaurantId);

    boolean existsByIdClientAndStatusIsLike(Long idClient, String status);
}