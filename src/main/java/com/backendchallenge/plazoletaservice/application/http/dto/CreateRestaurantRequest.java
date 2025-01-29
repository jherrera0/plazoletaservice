package com.backendchallenge.plazoletaservice.application.http.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class CreateRestaurantRequest {
    @NotNull
    @NotBlank
    private String name;
    @NotNull
    @NotBlank
    private String nit;
    @NotNull
    @NotBlank
    private String address;
    @NotNull
    @NotBlank
    private String phone;
    @NotNull
    @NotBlank
    private String urlLogo;
    @Positive
    private Long idOwner;
}
