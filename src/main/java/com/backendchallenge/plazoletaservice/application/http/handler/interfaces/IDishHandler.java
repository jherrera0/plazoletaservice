package com.backendchallenge.plazoletaservice.application.http.handler.interfaces;

import com.backendchallenge.plazoletaservice.application.http.dto.request.ChangeStatusRequest;
import com.backendchallenge.plazoletaservice.application.http.dto.request.CreateDishRequest;
import com.backendchallenge.plazoletaservice.application.http.dto.request.UpdateDishRequest;

public interface IDishHandler {
    void createDish(CreateDishRequest request);
    void updateDish(UpdateDishRequest request);
    void changeDishStatus(ChangeStatusRequest request);
}
