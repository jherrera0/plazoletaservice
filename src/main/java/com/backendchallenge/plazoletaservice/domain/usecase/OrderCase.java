package com.backendchallenge.plazoletaservice.domain.usecase;

import com.backendchallenge.plazoletaservice.domain.api.IOrderServicePort;
import com.backendchallenge.plazoletaservice.domain.exceptions.orderexceptions.*;
import com.backendchallenge.plazoletaservice.domain.exceptions.restaurantexceptions.RestaurantNotFoundException;
import com.backendchallenge.plazoletaservice.domain.model.Order;
import com.backendchallenge.plazoletaservice.domain.spi.IDishPersistencePort;
import com.backendchallenge.plazoletaservice.domain.spi.IJwtPersistencePort;
import com.backendchallenge.plazoletaservice.domain.spi.IOrderPersistencePort;
import com.backendchallenge.plazoletaservice.domain.spi.IRestaurantPersistencePort;
import com.backendchallenge.plazoletaservice.domain.until.ConstValidation;
import com.backendchallenge.plazoletaservice.domain.until.TokenHolder;

public class OrderCase implements IOrderServicePort {

    private final IOrderPersistencePort orderPersistencePort;
    private final IRestaurantPersistencePort restaurantPersistencePort;
    private final IDishPersistencePort dishPersistencePort;
    private final IJwtPersistencePort jwtPersistencePort;

    public OrderCase(IOrderPersistencePort orderPersistencePort,
                     IRestaurantPersistencePort restaurantPersistencePort,
                     IDishPersistencePort dishPersistencePort,
                     IJwtPersistencePort jwtPersistencePort) {
        this.orderPersistencePort = orderPersistencePort;
        this.restaurantPersistencePort = restaurantPersistencePort;
        this.dishPersistencePort = dishPersistencePort;
        this.jwtPersistencePort = jwtPersistencePort;
    }

    @Override
    public void createOrder(Order order) {
        String token = TokenHolder.getTokenValue();
        order.setIdClient(jwtPersistencePort.getUserId(token));
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
        if(!orderPersistencePort.createOrder(order)){
            throw new OrderNotCreatedException();
        }
    }
}
