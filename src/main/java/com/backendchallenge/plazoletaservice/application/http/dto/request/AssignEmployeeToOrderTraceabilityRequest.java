package com.backendchallenge.plazoletaservice.application.http.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssignEmployeeToOrderTraceabilityRequest {
    private Long orderId;
    private Long employeeId;
}
