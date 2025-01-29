package com.backendchallenge.plazoletaservice.application.http.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateDishRequest {
    @Positive
    private Long idRestaurant;
    @NotBlank
    @NotNull
    private String name;
    @Positive
    private Integer price;
    @NotBlank
    @NotNull
    private String description;
    @NotBlank
    @NotNull
    private String urlImage;
    @NotBlank
    @NotNull
    private String category;
}
