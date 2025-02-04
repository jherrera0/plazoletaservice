package com.backendchallenge.plazoletaservice.application.jpa.adapter;

import com.backendchallenge.plazoletaservice.application.jpa.entity.OrderEntity;
import com.backendchallenge.plazoletaservice.application.jpa.entity.OrderedDishEntity;
import com.backendchallenge.plazoletaservice.application.jpa.entity.RestaurantEntity;
import com.backendchallenge.plazoletaservice.application.jpa.mapper.IOrderEntityMapper;
import com.backendchallenge.plazoletaservice.application.jpa.mapper.IOrderedDishEntityMapper;
import com.backendchallenge.plazoletaservice.application.jpa.repository.IDishRepository;
import com.backendchallenge.plazoletaservice.application.jpa.repository.IOrderRepository;
import com.backendchallenge.plazoletaservice.application.jpa.repository.IOrderedDishRepository;
import com.backendchallenge.plazoletaservice.application.jpa.repository.IRestaurantRepository;
import com.backendchallenge.plazoletaservice.domain.model.Order;
import com.backendchallenge.plazoletaservice.domain.model.PageCustom;
import com.backendchallenge.plazoletaservice.domain.spi.IOrderPersistencePort;
import com.backendchallenge.plazoletaservice.domain.until.ConstValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class OrderJpaAdapter implements IOrderPersistencePort {
    private final IOrderRepository orderRepository;
    private final IRestaurantRepository restaurantRepository;
    private final IOrderEntityMapper orderEntityMapper;
    private final IDishRepository dishRepository;
    private final IOrderedDishRepository orderedDishRepository;
    private final IOrderedDishEntityMapper orderedDishEntityMapper;

    @Override
    public boolean createOrder(Order order) {
        OrderEntity orderEntity = orderEntityMapper.toEntity(order);
        orderEntity.setRestaurant(restaurantRepository.findById(order.getIdRestaurant()).orElse(new RestaurantEntity()));
        if (orderEntity.getRestaurant().equals(new RestaurantEntity())) {
            return false;
        }
        orderRepository.save(orderEntity);
         List<OrderedDishEntity> orderedDishEntities = new ArrayList<>();
        order.getDishes().forEach(dish -> {
            OrderedDishEntity orderedDishEntity = new OrderedDishEntity();
            orderedDishEntity.setDish(dishRepository.findById(dish.getIdDish()).orElse(null));
            if (orderedDishEntity.getDish() == null) {
                return;
            }
            orderedDishEntity.setQuantity(dish.getQuantity());
            orderedDishEntity.setOrder(orderEntity);
            orderedDishEntities.add(orderedDishEntity);
        });
        if (orderedDishEntities.isEmpty()) {
            return false;
        }
        orderedDishRepository.saveAll(orderedDishEntities);
        return true;
    }

    @Override
    public boolean findOrderByClientId(Long idClient) {
        return orderRepository.existsByIdClientAndStatusIsLikeOrStatusIsLike(idClient, ConstValidation.PENDING,ConstValidation.IN_PROCESS);
    }

    @Override
    public PageCustom<Order> getOrders(Long idRestaurant, Integer currentPage, Integer pageSize, String filterBy, String orderDirection) {
        Pageable pageable = PageRequest.of(currentPage, pageSize, Sort.by(Sort.Direction.fromString(orderDirection),ConstValidation.ID));
        Page<OrderEntity> orderEntities = orderRepository.findAllByRestaurant_IdAndFilter(idRestaurant, filterBy, pageable);
        if(orderEntities.getTotalPages()>= currentPage+ConstValidation.ONE){
            PageCustom<Order> pageCustom = new PageCustom<>(orderEntities.getNumber(), orderEntities.getSize(),
                    orderEntities.getTotalPages(),orderEntityMapper.toDomainList(orderEntities.getContent()));

            pageCustom.getItems().forEach(order -> {
                List<OrderedDishEntity> orderedDishEntities = orderedDishRepository.findAllByOrder_Id(order.getId());
                order.setDishes(orderedDishEntityMapper.toDomainList(orderedDishEntities));
            });
            return pageCustom;

        }
        return new PageCustom<>(ConstValidation.MINUS_ONE,null,null,null);
    }

    @Override
    public boolean existsOrderById(Long idOrder) {
        return orderRepository.existsById(idOrder);
    }

    @Override
    public Order getOrderById(Long idOrder) {
        return orderEntityMapper.toDomain(orderRepository.findById(idOrder).orElse(new OrderEntity()));
    }

    @Override
    public void updateOrder(Order order) {
        OrderEntity orderEntity = orderEntityMapper.toEntity(order);
        orderEntity.setRestaurant(restaurantRepository.findById(order.getIdRestaurant()).orElse(new RestaurantEntity()));
        orderRepository.save(orderEntity);
    }



}
