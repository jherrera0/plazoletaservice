package com.backendchallenge.plazoletaservice.application.http.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ListOrderRequest {
    @Positive
    private Long idRestaurant;
    @PositiveOrZero
    private Integer currentPage;
    @Positive
    private Integer pageSize;
    @NotNull
    @NotBlank
    private String filterBy;
    @NotNull
    @NotBlank
    private String orderDirection;
}
