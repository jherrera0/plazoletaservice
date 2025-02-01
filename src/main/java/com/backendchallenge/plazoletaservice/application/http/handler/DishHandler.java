package com.backendchallenge.plazoletaservice.application.http.handler;

import com.backendchallenge.plazoletaservice.application.http.dto.request.ChangeStatusRequest;
import com.backendchallenge.plazoletaservice.application.http.dto.request.CreateDishRequest;
import com.backendchallenge.plazoletaservice.application.http.dto.request.UpdateDishRequest;
import com.backendchallenge.plazoletaservice.application.http.dto.response.DishResponse;
import com.backendchallenge.plazoletaservice.application.http.dto.response.PageResponse;
import com.backendchallenge.plazoletaservice.application.http.handler.interfaces.IDishHandler;
import com.backendchallenge.plazoletaservice.application.http.mapper.ICreateDishRequestMapper;
import com.backendchallenge.plazoletaservice.application.http.mapper.IPageResponseMapper;
import com.backendchallenge.plazoletaservice.domain.api.IDishServicePort;
import com.backendchallenge.plazoletaservice.domain.until.TokenHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DishHandler implements IDishHandler {
    private final IDishServicePort dishServicePort;
    private final ICreateDishRequestMapper createDishRequestMapper;
    private final IPageResponseMapper pageResponseMapper;
    @Override
    public void createDish(CreateDishRequest request) {
        TokenHolder.getToken();
        dishServicePort.createDish(createDishRequestMapper.toDomain(request));
    }

    @Override
    public void updateDish(UpdateDishRequest request) {
        TokenHolder.getToken();
        dishServicePort.updateDish(request.getDishId(),request.getDescriptionUpdate(),request.getPriceUpdate());
    }

    @Override
    public void changeDishStatus(ChangeStatusRequest request) {
        TokenHolder.getToken();
        dishServicePort.changeDishStatus(request.getDishId(),request.getStatus());
    }

    @Override
    public PageResponse<DishResponse> getDishesByRestaurant(Long restaurantId, Integer currentPage, Integer pageSize, String filterBy, String orderDirection) {
        return pageResponseMapper.toPageResponseOfDishResponse(dishServicePort.getDishesByRestaurant(restaurantId,currentPage,pageSize,filterBy,orderDirection));
    }
}
