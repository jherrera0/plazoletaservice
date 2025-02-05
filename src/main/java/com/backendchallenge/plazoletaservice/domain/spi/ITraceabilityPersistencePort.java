package com.backendchallenge.plazoletaservice.domain.spi;

import com.backendchallenge.plazoletaservice.domain.model.Order;

import java.time.LocalDateTime;

public interface ITraceabilityPersistencePort {
    void createOrderTraceability(Order order);
    void assignEmployeeToOrder(Long idOrder, Long idEmployee);
    void updateOrderTraceability(Long idOrder, String status, LocalDateTime date);
}
