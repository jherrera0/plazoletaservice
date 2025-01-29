package com.backendchallenge.plazoletaservice.application.http.handler.interfaces;

import com.backendchallenge.plazoletaservice.application.http.dto.CreateDishRequest;

public interface IDishHandler {
    void createDish(CreateDishRequest request, Long ownerId);
}
