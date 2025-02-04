package com.backendchallenge.plazoletaservice.application.http.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssignEmployeeRequest {
    private Long idRestaurant;
    private Long idOrder;
}
