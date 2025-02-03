package com.backendchallenge.plazoletaservice.application.jpa.adapter;

import com.backendchallenge.plazoletaservice.application.jpa.entity.OrderEntity;
import com.backendchallenge.plazoletaservice.application.jpa.entity.OrderedDishEntity;
import com.backendchallenge.plazoletaservice.application.jpa.entity.RestaurantEntity;
import com.backendchallenge.plazoletaservice.application.jpa.mapper.IOrderEntityMapper;
import com.backendchallenge.plazoletaservice.application.jpa.repository.IDishRepository;
import com.backendchallenge.plazoletaservice.application.jpa.repository.IOrderRepository;
import com.backendchallenge.plazoletaservice.application.jpa.repository.IRestaurantRepository;
import com.backendchallenge.plazoletaservice.domain.model.Order;
import com.backendchallenge.plazoletaservice.domain.spi.IOrderPersistencePort;
import com.backendchallenge.plazoletaservice.domain.until.ConstValidation;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class OrderJpaAdapter implements IOrderPersistencePort {
    private final IOrderRepository orderRepository;
    private final IRestaurantRepository restaurantRepository;
    private final IDishRepository dishRepository;
    private final IOrderEntityMapper orderEntityMapper;

    @Override
    public boolean createOrder(Order order) {
        OrderEntity orderEntity = orderEntityMapper.toEntity(order);
        orderEntity.setRestaurant(restaurantRepository.findById(order.getIdRestaurant()).orElse(new RestaurantEntity()));
        if (orderEntity.getRestaurant().equals(new RestaurantEntity())){
            return false;
        }
        List<OrderedDishEntity> orderedDishEntities = new ArrayList<>();
        order.getDishes().forEach(dish -> {
            OrderedDishEntity orderedDishEntity = new OrderedDishEntity();
            orderedDishEntity.setDish(dishRepository.findById(dish.getDishId()).orElse(null));
            if (orderedDishEntity.getDish() == null){
                return;
            }
            orderedDishEntity.setQuantity(dish.getQuantity());
            orderedDishEntities.add(orderedDishEntity);
        });
        if (orderedDishEntities.isEmpty()){
            return false;
        }
        orderEntity.setOrderedDishes(orderedDishEntities);
        orderRepository.save(orderEntity);
        return true;
    }

    @Override
    public boolean findOrderByClientId(Long idClient) {
        return false;
    }
}
