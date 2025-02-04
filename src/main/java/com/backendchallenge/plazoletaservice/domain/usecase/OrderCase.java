package com.backendchallenge.plazoletaservice.domain.usecase;

import com.backendchallenge.plazoletaservice.domain.api.IOrderServicePort;
import com.backendchallenge.plazoletaservice.domain.exceptions.employeeexcepcion.EmployeeNotBelongToRestaurantException;
import com.backendchallenge.plazoletaservice.domain.exceptions.orderexceptions.*;
import com.backendchallenge.plazoletaservice.domain.exceptions.restaurantexceptions.RestaurantNotFoundException;
import com.backendchallenge.plazoletaservice.domain.model.Order;
import com.backendchallenge.plazoletaservice.domain.model.PageCustom;
import com.backendchallenge.plazoletaservice.domain.spi.*;
import com.backendchallenge.plazoletaservice.domain.until.ConstJwt;
import com.backendchallenge.plazoletaservice.domain.until.ConstValidation;
import com.backendchallenge.plazoletaservice.domain.until.TokenHolder;

public class OrderCase implements IOrderServicePort {

    private final IOrderPersistencePort orderPersistencePort;
    private final IRestaurantPersistencePort restaurantPersistencePort;
    private final IDishPersistencePort dishPersistencePort;
    private final IJwtPersistencePort jwtPersistencePort;
    private final IUserPersistencePort userPersistencePort;

    public OrderCase(IOrderPersistencePort orderPersistencePort,
                     IRestaurantPersistencePort restaurantPersistencePort,
                     IDishPersistencePort dishPersistencePort,
                     IJwtPersistencePort jwtPersistencePort,
                     IUserPersistencePort userPersistencePort) {
        this.orderPersistencePort = orderPersistencePort;
        this.restaurantPersistencePort = restaurantPersistencePort;
        this.dishPersistencePort = dishPersistencePort;
        this.jwtPersistencePort = jwtPersistencePort;
        this.userPersistencePort = userPersistencePort;
    }

    @Override
    public void createOrder(Order order) {
        String token = TokenHolder.getTokenValue().substring(ConstJwt.LINESTRING_INDEX);
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
            if (!order.getIdRestaurant().equals(dishPersistencePort.getRestaurantIdByDishId(dish.getIdDish()))) {
                throw new DishNotFoundInRestaurantException();
            }
            if (dish.getIdDish() == null || dish.getIdDish() <= ConstValidation.ZERO) {
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

    @Override
    public PageCustom<Order> getOrders(Long idRestaurant, Integer currentPage, Integer pageSize, String filterBy, String orderDirection) {
        String token = TokenHolder.getTokenValue().substring(ConstJwt.LINESTRING_INDEX);
        Long idUser = jwtPersistencePort.getUserId(token);
        if (!restaurantPersistencePort.existsRestaurantById(idRestaurant)) {
            throw new RestaurantNotFoundException();
        }
        if(!userPersistencePort.findEmployeeByIds(idUser, idRestaurant)) {
            throw new EmployeeNotBelongToRestaurantException();
        }
        if(currentPage == null || currentPage < ConstValidation.ZERO) {
            throw new OrderCurrentPageInvalidException();
        }
        if(pageSize == null || pageSize <= ConstValidation.ZERO) {
            throw new OrderPageSizeInvalidException();
        }
        if(filterBy == null || filterBy.isEmpty()) {
            throw new OrderFilterByInvalidException();
        }
        if(!orderDirection.equals(ConstValidation.ASC) && !orderDirection.equals(ConstValidation.DESC)) {
            throw new OrderOrderDirectionInvalidException();
        }
        PageCustom<Order> orderPageCustom = orderPersistencePort.getOrders(idRestaurant, currentPage, pageSize, filterBy, orderDirection);
        if (orderPageCustom.getCurrentPage().equals(ConstValidation.MINUS_ONE)) {
            throw new OrderCurrentPageInvalidException();
        }
        return orderPageCustom;
    }
}
