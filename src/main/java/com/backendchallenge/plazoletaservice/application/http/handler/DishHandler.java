package com.backendchallenge.plazoletaservice.application.http.handler;

import com.backendchallenge.plazoletaservice.application.http.dto.CreateDishRequest;
import com.backendchallenge.plazoletaservice.application.http.dto.UpdateDishRequest;
import com.backendchallenge.plazoletaservice.application.http.handler.interfaces.IDishHandler;
import com.backendchallenge.plazoletaservice.application.http.mapper.ICreateDishRequestMapper;
import com.backendchallenge.plazoletaservice.domain.api.IDishServicePort;
import com.backendchallenge.plazoletaservice.domain.until.TokenHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DishHandler implements IDishHandler {
    private final IDishServicePort dishServicePort;
    private final ICreateDishRequestMapper createDishRequestMapper;

    @Override
    public void createDish(CreateDishRequest request, String token) {
        TokenHolder.setToken(token);
        dishServicePort.createDish(createDishRequestMapper.toDomain(request));
    }

    @Override
    public void updateDish(UpdateDishRequest request) {
        dishServicePort.updateDish(request.getDishId(),request.getDescriptionUpdate(),request.getPriceUpdate());
    }
}
