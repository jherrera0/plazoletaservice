package com.backendchallenge.plazoletaservice.application.jpa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class OrderedDishEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    private DishEntity dish;

    @ManyToOne
    @JoinColumn(name = "order.id")
    private OrderEntity order;

    private int quantity;
}