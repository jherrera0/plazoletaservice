package com.backendchallenge.plazoletaservice.application.http.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ChangeStatusRequest {
    @NotNull
    @Positive
    private Long dishId;
    @NotNull
    private Boolean status;
}
