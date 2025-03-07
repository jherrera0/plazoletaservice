package com.backendchallenge.plazoletaservice.domain.spi;

import com.backendchallenge.plazoletaservice.domain.model.Order;
import com.backendchallenge.plazoletaservice.domain.model.PageCustom;

public interface IOrderPersistencePort {
    boolean createOrder(Order order);

    boolean findOrderByClientId(Long idClient);

    PageCustom<Order> getOrders(Long idRestaurant, Integer currentPage, Integer pageSize, String filterBy, String orderDirection);

    boolean existsOrderById(Long idOrder);

    Order getOrderById(Long idOrder);

    void updateOrder(Order order);

    Order getOrderByParams(String status, Long idClient, Long idRestaurant);
}
