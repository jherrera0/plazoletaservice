package com.backendchallenge.plazoletaservice.application.http.dto.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateOrderTraceabilityRequest {
    private Long idOrder;
    private String status;
    private LocalDateTime date;
}
