package com.backendchallenge.plazoletaservice.domain.api;

import com.backendchallenge.plazoletaservice.domain.model.Order;
import com.backendchallenge.plazoletaservice.domain.model.PageCustom;

public interface IOrderServicePort {
    void createOrder(Order order);

    PageCustom<Order> getOrders(Long idRestaurant, Integer currentPage, Integer pageSize, String filterBy,
                                        String orderDirection);

    void assignEmployeeToOrder(Long idOrder, Long idRestaurant);
}
