package com.backendchallenge.plazoletaservice.application.http.handler.interfaces;

import com.backendchallenge.plazoletaservice.application.http.dto.request.EmployeeRequest;
import com.backendchallenge.plazoletaservice.application.http.dto.request.ListOrderRequest;
import com.backendchallenge.plazoletaservice.application.http.dto.request.OrderDeliveredRequest;
import com.backendchallenge.plazoletaservice.application.http.dto.request.OrderRequest;
import com.backendchallenge.plazoletaservice.application.http.dto.response.OrderResponse;
import com.backendchallenge.plazoletaservice.application.http.dto.response.PageResponse;

public interface IOrderHandler {
    void createOrder(OrderRequest request);
    PageResponse<OrderResponse> getOrders(ListOrderRequest request);
    void assignEmployee(EmployeeRequest request);
    void orderReady(EmployeeRequest request);
    void orderDelivered(OrderDeliveredRequest request);
    void cancelOrder(EmployeeRequest request);
}
