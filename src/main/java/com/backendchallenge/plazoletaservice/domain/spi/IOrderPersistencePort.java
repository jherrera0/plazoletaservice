package com.backendchallenge.plazoletaservice.domain.spi;

import com.backendchallenge.plazoletaservice.domain.model.Order;

public interface IOrderPersistencePort {
    boolean createOrder(Order order);

    boolean findOrderByClientId(Long idClient);
}
