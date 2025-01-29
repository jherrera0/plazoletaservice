package com.backendchallenge.plazoletaservice.application.http.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class CreateRestaurantRequest {
    private String name;
    private String nit;
    private String address;
    private String phone;
    private String urlLogo;
    private Long idOwner;
}
