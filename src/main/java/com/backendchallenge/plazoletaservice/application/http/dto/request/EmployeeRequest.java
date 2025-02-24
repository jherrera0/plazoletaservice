package com.backendchallenge.plazoletaservice.application.http.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeRequest {
    @Positive
    @NotNull
    private Long idRestaurant;
    @Positive
    @NotNull
    private Long idOrder;
}
