package com.backendchallenge.plazoletaservice.application.http.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderedDishResponse {
    private Long dishId;
    private Integer quantity;
}
