package com.backendchallenge.plazoletaservice.application.http.handler.interfaces;

import com.backendchallenge.plazoletaservice.application.http.dto.request.OrderRequest;

public interface IOrderHandler {
    void createOrder(OrderRequest request);
}
