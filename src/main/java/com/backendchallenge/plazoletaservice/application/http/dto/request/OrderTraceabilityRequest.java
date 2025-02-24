package com.backendchallenge.plazoletaservice.application.http.dto.request;

import com.backendchallenge.plazoletaservice.domain.model.OrderedDish;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderTraceabilityRequest {

    private Long id;
    private Long idClient;
    private Long idEmployee;
    private Long idRestaurant;
    private List<OrderedDish> dishes;
    private List<StatusChange> statusChanges;
}
