package com.backendchallenge.plazoletaservice.application.jpa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "restaurants_workers")
@IdClass(RestaurantsWorkerId.class)
@Builder
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantWorkersEntity {
    @Id
    @Column(name = "restaurant_id", nullable = false)
    private Long restaurantId;

    @Id
    @Column(name = "employed_id", nullable = false)
    private Long employedId;

}
