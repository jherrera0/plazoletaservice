package com.backendchallenge.plazoletaservice.application.http.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ListRestaurantsRequest {
    @NotNull
    @NotBlank
    private String orderDirection;
    @Positive
    private Integer limitForPage;
    @PositiveOrZero
    private Integer currentPage;
}
