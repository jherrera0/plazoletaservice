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

import java.util.Objects;

public class OrderCase implements IOrderServicePort {

    private final IOrderPersistencePort orderPersistencePort;
    private final IRestaurantPersistencePort restaurantPersistencePort;
    private final IDishPersistencePort dishPersistencePort;
    private final IJwtPersistencePort jwtPersistencePort;
    private final IUserPersistencePort userPersistencePort;
    private final INotificationPersistencePort notificationPersistencePort;

    public OrderCase(IOrderPersistencePort orderPersistencePort,
                     IRestaurantPersistencePort restaurantPersistencePort,
                     IDishPersistencePort dishPersistencePort,
                     IJwtPersistencePort jwtPersistencePort,
                     IUserPersistencePort userPersistencePort,
                     INotificationPersistencePort notificationPersistencePort) {
        this.orderPersistencePort = orderPersistencePort;
        this.restaurantPersistencePort = restaurantPersistencePort;
        this.dishPersistencePort = dishPersistencePort;
        this.jwtPersistencePort = jwtPersistencePort;
        this.userPersistencePort = userPersistencePort;
        this.notificationPersistencePort = notificationPersistencePort;
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
        Long idUser = getIdUser();
        validatedParamToList(idRestaurant, currentPage, pageSize, filterBy, orderDirection, idUser);
        PageCustom<Order> orderPageCustom = orderPersistencePort.getOrders(idRestaurant, currentPage, pageSize, filterBy, orderDirection);
        if (orderPageCustom.getCurrentPage().equals(ConstValidation.MINUS_ONE)) {
            throw new OrderCurrentPageInvalidException();
        }
        return orderPageCustom;
    }

    @Override
    public void assignEmployeeToOrder(Long idOrder, Long idRestaurant) {
        Order order = validatedUserParams(idOrder, idRestaurant);
        if (validatedOrderParams(idRestaurant, order)) {
            throw new OrderNotAssignedException();
        }
        orderPersistencePort.updateOrder(order);
    }


    @Override
    public void notifyOrderReady(Long idOrder, Long idRestaurant) {
        Order order = validatedUserParams(idOrder, idRestaurant);
        if (!Objects.equals(order.getIdEmployee(), getIdUser())) {
            throw new OrderNotBelongToEmployeeException();
        }
        order.setStatus(ConstValidation.COMPLETED);
        orderPersistencePort.updateOrder(order);
        notificationPersistencePort.sendNotification(userPersistencePort.getPhone(order.getIdClient()), order.getId());

    }

    @Override
    public void deliverOrder(Long idOrder, Long idRestaurant, String pin) {
        Order order = validatedUserParams(idOrder, idRestaurant);
        if (!Objects.equals(order.getIdEmployee(), getIdUser())) {
            throw new OrderNotBelongToEmployeeException();
        }
        if(!order.getStatus().equals(ConstValidation.COMPLETED)) {
            throw new OrderIsNotCompletedException();
        }
        if (!notificationPersistencePort.existPinByPhoneNumber(userPersistencePort.getPhone(order.getIdClient()))){
            throw new OrderPinNotFoundException();
        }
        if (!notificationPersistencePort.findPinByPhoneNumber(userPersistencePort.getPhone(order.getIdClient())).equals(pin)) {
            throw new OrderPinInvalidException();
        }
        order.setStatus(ConstValidation.DELIVERED);
        orderPersistencePort.updateOrder(order);
    }

    private Long getIdUser() {
        String token = TokenHolder.getTokenValue().substring(ConstJwt.LINESTRING_INDEX);
        return jwtPersistencePort.getUserId(token);
    }
    private Order validatedUserParams(Long idOrder, Long idRestaurant) {
        if (!restaurantPersistencePort.existsRestaurantById(idRestaurant)) {
            throw new RestaurantNotFoundException();
        }
        if(!userPersistencePort.findEmployeeByIds(getIdUser(), idRestaurant)) {
            throw new EmployeeNotBelongToRestaurantException();
        }
        if (!orderPersistencePort.existsOrderById(idOrder)) {
            throw new OrderNotFoundException();
        }

        return orderPersistencePort.getOrderById(idOrder);
    }

    private void validatedParamToList(Long idRestaurant, Integer currentPage, Integer pageSize, String filterBy, String orderDirection, Long idUser) {
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
    }
    private static boolean validatedOrderParams(Long idRestaurant, Order order) {
        return order.equals(new Order()) || !(order.getIdRestaurant().equals(idRestaurant)) ||
                order.getIdEmployee() != null ||
                order.getStatus().equals(ConstValidation.IN_PROCESS) ||
                order.getStatus().equals((ConstValidation.COMPLETED));
    }
}
