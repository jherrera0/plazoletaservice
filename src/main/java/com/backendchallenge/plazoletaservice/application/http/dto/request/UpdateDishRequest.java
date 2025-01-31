package com.backendchallenge.plazoletaservice.application.http.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateDishRequest {
    private Long dishId;
    private String descriptionUpdate;
    private Integer priceUpdate;
}
