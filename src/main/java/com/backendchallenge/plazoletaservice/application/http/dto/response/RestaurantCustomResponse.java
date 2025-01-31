package com.backendchallenge.plazoletaservice.application.http.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantCustomResponse {
    private String name;
    private String urlLogo;
}
