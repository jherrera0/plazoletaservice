package com.backendchallenge.plazoletaservice.domain.usecase;

import com.backendchallenge.plazoletaservice.domain.api.IOrderServicePort;
import com.backendchallenge.plazoletaservice.domain.exceptions.orderexceptions.*;
import com.backendchallenge.plazoletaservice.domain.exceptions.restaurantexceptions.RestaurantNotFoundException;
import com.backendchallenge.plazoletaservice.domain.model.Order;
import com.backendchallenge.plazoletaservice.domain.spi.IDishPersistencePort;
import com.backendchallenge.plazoletaservice.domain.spi.IOrderPersistencePort;
import com.backendchallenge.plazoletaservice.domain.spi.IRestaurantPersistencePort;
import com.backendchallenge.plazoletaservice.domain.until.ConstValidation;

public class OrderCase implements IOrderServicePort {

    private final IOrderPersistencePort orderPersistencePort;
    private final IRestaurantPersistencePort restaurantPersistencePort;
    private final IDishPersistencePort dishPersistencePort;

    public OrderCase(IOrderPersistencePort orderPersistencePort,
                     IRestaurantPersistencePort restaurantPersistencePort,
                     IDishPersistencePort dishPersistencePort) {
        this.orderPersistencePort = orderPersistencePort;
        this.restaurantPersistencePort = restaurantPersistencePort;
        this.dishPersistencePort = dishPersistencePort;
    }

    @Override
    public void createOrder(Order order) {
        if (orderPersistencePort.findOrderByClientId(order.getIdClient())) {
            throw new OrderAlreadyExistsException();
        }
        if(order.getIdRestaurant() == null || order.getIdRestaurant() <= ConstValidation.ZERO) {
            throw new OrderIdRestaurantInvalidException();
        }

        if (!restaurantPersistencePort.existsRestaurantById(order.getIdRestaurant())) {
            throw new RestaurantNotFoundException();
        }
        if (order.getDishes().isEmpty()) {
            throw new OrderDishesNotEmptyException();
        }
        order.getDishes().forEach(dish -> {
            if (!order.getIdRestaurant().equals(dishPersistencePort.getRestaurantIdByDishId(dish.getId()))) {
                throw new DishNotFoundInRestaurantException();
            }
            if (dish.getDishId() == null || dish.getDishId() <= ConstValidation.ZERO) {
                throw new OrderDishIdInvalidException();
            }
            if(dish.getQuantity() <= ConstValidation.ZERO) {
                throw new OrderDishQuantityInvalidException();
            }
        });
        order.setStatus(ConstValidation.PENDING);
        orderPersistencePort.createOrder(order);
    }
}
