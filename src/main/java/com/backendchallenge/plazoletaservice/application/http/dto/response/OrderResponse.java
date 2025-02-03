package com.backendchallenge.plazoletaservice.application.http.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponse {
    private Long idClient;
    private LocalDate date;
    private String status;
    private Long idEmployee;
    private Long idRestaurant;
    private List<OrderedDishResponse> dishes;
}
