package com.backendchallenge.plazoletaservice.application.http.handler;

import com.backendchallenge.plazoletaservice.application.http.dto.request.ListOrderRequest;
import com.backendchallenge.plazoletaservice.application.http.dto.request.OrderRequest;
import com.backendchallenge.plazoletaservice.application.http.dto.response.OrderResponse;
import com.backendchallenge.plazoletaservice.application.http.dto.response.PageResponse;
import com.backendchallenge.plazoletaservice.application.http.handler.interfaces.IOrderHandler;
import com.backendchallenge.plazoletaservice.application.http.mapper.IOrderRequestMapper;
import com.backendchallenge.plazoletaservice.application.http.mapper.IPageResponseMapper;
import com.backendchallenge.plazoletaservice.domain.api.IOrderServicePort;
import com.backendchallenge.plazoletaservice.domain.until.TokenHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderHandler implements IOrderHandler {
    private final IPageResponseMapper pageResponseMapper;
    private final IOrderRequestMapper orderRequestMapper;
    private final IOrderServicePort orderServicePort;

    @Override
    public void createOrder(OrderRequest request) {
        TokenHolder.getToken();
        orderServicePort.createOrder(orderRequestMapper.toDomain(request));
    }

    @Override
    public PageResponse<OrderResponse> getOrders(ListOrderRequest request) {
        TokenHolder.getToken();
        return pageResponseMapper.toPageResponseOfOrderResponse(orderServicePort.getOrders(request.getIdRestaurant(),
                request.getCurrentPage(),request.getPageSize(),request.getFilterBy(),
                request.getOrderDirection()));
    }
}
