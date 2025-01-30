package com.backendchallenge.plazoletaservice.application.http.handler.interfaces;

import com.backendchallenge.plazoletaservice.application.http.dto.CreateDishRequest;
import com.backendchallenge.plazoletaservice.application.http.dto.UpdateDishRequest;

public interface IDishHandler {
    void createDish(CreateDishRequest request, String token);
    void updateDish(UpdateDishRequest request);
}
