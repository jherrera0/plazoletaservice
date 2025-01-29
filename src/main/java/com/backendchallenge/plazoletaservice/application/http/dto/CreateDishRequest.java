package com.backendchallenge.plazoletaservice.application.http.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateDishRequest {
    private Long idRestaurant;
    private String name;
    private Integer price;
    private String description;
    private String urlImage;
    private String category;
}
