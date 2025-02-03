package com.backendchallenge.plazoletaservice.domain.spi;

import com.backendchallenge.plazoletaservice.domain.model.Order;

public interface IOrderPersistencePort {
    void createOrder(Order order);

    boolean findOrderByClientId(Long idClient);
}
