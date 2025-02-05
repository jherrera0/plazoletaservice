package com.backendchallenge.plazoletaservice.application.jpa.adapter;

import com.backendchallenge.plazoletaservice.application.feign.ITraceabilityFeignClient;
import com.backendchallenge.plazoletaservice.application.http.dto.request.AssignEmployeeToOrderTraceabilityRequest;
import com.backendchallenge.plazoletaservice.application.http.dto.request.OrderTraceabilityRequest;
import com.backendchallenge.plazoletaservice.application.http.dto.request.StatusChange;
import com.backendchallenge.plazoletaservice.application.http.dto.request.UpdateOrderTraceabilityRequest;
import com.backendchallenge.plazoletaservice.domain.model.Order;
import com.backendchallenge.plazoletaservice.domain.spi.ITraceabilityPersistencePort;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
public class TraceabilityJpaAdapter implements ITraceabilityPersistencePort {
    private final ITraceabilityFeignClient traceabilityFeignClient;
    @Override
    public void createOrderTraceability(Order order) {
        OrderTraceabilityRequest orderTraceabilityRequest = new OrderTraceabilityRequest();
        orderTraceabilityRequest.setId(order.getId());
        orderTraceabilityRequest.setIdClient(order.getIdClient());
        orderTraceabilityRequest.setIdEmployee(order.getIdEmployee());
        orderTraceabilityRequest.setIdRestaurant(order.getIdRestaurant());
        orderTraceabilityRequest.setDishes(order.getDishes());
        orderTraceabilityRequest.setStatusChanges( List.of(new StatusChange(LocalDateTime.now(), order.getStatus())));
        traceabilityFeignClient.createOrderTraceability(orderTraceabilityRequest);
    }

    @Override
    public void assignEmployeeToOrder(Long idOrder, Long idEmployee) {
        AssignEmployeeToOrderTraceabilityRequest assignEmployeeToOrderTraceabilityRequest =
                new AssignEmployeeToOrderTraceabilityRequest();
        assignEmployeeToOrderTraceabilityRequest.setOrderId(idOrder);
        assignEmployeeToOrderTraceabilityRequest.setEmployeeId(idEmployee);
        traceabilityFeignClient.assignEmployeeToOrderTraceability(assignEmployeeToOrderTraceabilityRequest);
    }

    @Override
    public void updateOrderTraceability(Long idOrder, String status, LocalDateTime date) {
        UpdateOrderTraceabilityRequest updateOrderTraceabilityRequest = new UpdateOrderTraceabilityRequest();
        updateOrderTraceabilityRequest.setIdOrder(idOrder);
        updateOrderTraceabilityRequest.setStatus(status);
        updateOrderTraceabilityRequest.setDate(date);
        traceabilityFeignClient.updateOrderTraceability(updateOrderTraceabilityRequest);
    }
}
