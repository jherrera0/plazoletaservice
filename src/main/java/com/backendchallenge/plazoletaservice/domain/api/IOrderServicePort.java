package com.backendchallenge.plazoletaservice.domain.api;

import com.backendchallenge.plazoletaservice.application.http.dto.response.OrderResponse;
import com.backendchallenge.plazoletaservice.domain.model.Order;
import com.backendchallenge.plazoletaservice.domain.model.PageCustom;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

public interface IOrderServicePort {
    void createOrder(Order order);

    PageCustom<Order> getOrders(Long idRestaurant, Integer currentPage, Integer pageSize, String filterBy,
                                        String orderDirection);
}
