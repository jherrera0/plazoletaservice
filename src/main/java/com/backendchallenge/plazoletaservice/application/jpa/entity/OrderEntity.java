package com.backendchallenge.plazoletaservice.application.jpa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long idClient;
    private LocalDate date;
    private Long idEmployee;

    @OneToOne(cascade = CascadeType.ALL)
    private RestaurantEntity restaurant;

    @OneToMany(mappedBy = "OrderEntity", cascade = CascadeType.ALL)
    private List<OrderedDishEntity> orderedDishes;
}
