package com.backendchallenge.plazoletaservice.application.jpa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class DishEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer price;
    private String description;
    private String urlImage;
    private Boolean available;

    @ManyToOne(fetch = FetchType.LAZY)
    private RestaurantEntity restaurant;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<CategoryEntity> categories;
}
