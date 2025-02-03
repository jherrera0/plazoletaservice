package com.backendchallenge.plazoletaservice.domain.api;

import com.backendchallenge.plazoletaservice.domain.model.Order;

public interface IOrderServicePort {
    void createOrder(Order order);
}
