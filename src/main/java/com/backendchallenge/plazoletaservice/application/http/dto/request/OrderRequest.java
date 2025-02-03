package com.backendchallenge.plazoletaservice.application.http.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequest {
    @Positive
    @NotNull
    private Long idRestaurant;
    @NotNull
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate date;
    @NotNull
    private List<OrderedDishRequest> dishes;
}
