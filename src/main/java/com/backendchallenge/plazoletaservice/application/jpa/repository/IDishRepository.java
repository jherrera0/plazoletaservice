package com.backendchallenge.plazoletaservice.application.jpa.repository;

import com.backendchallenge.plazoletaservice.application.jpa.entity.DishEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IDishRepository extends JpaRepository<DishEntity, Long> {
    @Modifying
    @Query("""
    UPDATE DishEntity d 
    SET d.available = :#{#dish.available} 
    WHERE d.id = :#{#dish.id} 
    AND d.restaurant.id IN (
        SELECT r.id FROM RestaurantEntity r WHERE r.idOwner = :#{#ownerId}
    )
    """)
    void updateDishStatus(@Param("dish") DishEntity dishEntity,
                          @Param("ownerId") Long ownerId);
}
