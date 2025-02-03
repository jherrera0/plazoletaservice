package com.backendchallenge.plazoletaservice.application.jpa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@IdClass(OrderedDishId.class)
@Table(name = "ordered_dish")
@NoArgsConstructor
@AllArgsConstructor
public class OrderedDishEntity {
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dish_id")
    private DishEntity dish;
    @Id
    @ManyToOne
    @JoinColumn(name = "order_id")
    private OrderEntity order;

    private int quantity;
}