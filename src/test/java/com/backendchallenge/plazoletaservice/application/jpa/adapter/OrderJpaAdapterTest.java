package com.backendchallenge.plazoletaservice.application.jpa.adapter;

import com.backendchallenge.plazoletaservice.application.jpa.entity.DishEntity;
import com.backendchallenge.plazoletaservice.application.jpa.entity.OrderEntity;
import com.backendchallenge.plazoletaservice.application.jpa.entity.RestaurantEntity;
import com.backendchallenge.plazoletaservice.application.jpa.mapper.IOrderEntityMapper;
import com.backendchallenge.plazoletaservice.application.jpa.repository.IDishRepository;
import com.backendchallenge.plazoletaservice.application.jpa.repository.IOrderRepository;
import com.backendchallenge.plazoletaservice.application.jpa.repository.IOrderedDishRepository;
import com.backendchallenge.plazoletaservice.application.jpa.repository.IRestaurantRepository;
import com.backendchallenge.plazoletaservice.domain.model.Order;
import com.backendchallenge.plazoletaservice.domain.model.OrderedDish;
import com.backendchallenge.plazoletaservice.domain.until.ConstTest;
import com.backendchallenge.plazoletaservice.domain.until.ConstValidation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

class OrderJpaAdapterTest {

    @Mock
    private IOrderRepository orderRepository;

    @Mock
    private IRestaurantRepository restaurantRepository;

    @Mock
    private IOrderEntityMapper orderEntityMapper;

    @Mock
    private IDishRepository dishRepository;

    @Mock
    private IOrderedDishRepository orderedDishRepository;

    @InjectMocks
    private OrderJpaAdapter orderJpaAdapter;

    AutoCloseable closeable;

    @BeforeEach
    void setUp() {
     closeable = MockitoAnnotations.openMocks(this);
    }

    @Test
    void createOrder_successful() {
        Order order = new Order();
        order.setIdRestaurant(ConstTest.ID_TEST);
        order.setDishes(Collections.singletonList(new OrderedDish(ConstTest.ID_TEST, ConstTest.ID_TEST, ConstValidation.TWO)));

        OrderEntity orderEntity = new OrderEntity();
        RestaurantEntity restaurantEntity = new RestaurantEntity();
        restaurantEntity.setId(ConstTest.ID_TEST);
        restaurantEntity.setName(ConstTest.NAME_TEST);
        restaurantEntity.setAddress(ConstTest.ADDRESS_TEST);
        restaurantEntity.setPhone(ConstTest.PHONE_TEST);
        orderEntity.setRestaurant(restaurantEntity);

        when(orderEntityMapper.toEntity(any(Order.class))).thenReturn(orderEntity);
        when(restaurantRepository.findById(any(Long.class))).thenReturn(Optional.of(restaurantEntity));
        when(dishRepository.findById(any(Long.class))).thenReturn(Optional.of(new DishEntity()));
        when(orderedDishRepository.saveAll(anyList())).thenReturn(Collections.emptyList());
        when(orderRepository.save(any(OrderEntity.class))).thenReturn(orderEntity);

        assertTrue(orderJpaAdapter.createOrder(order));
    }

    @Test
    void createOrder_restaurantNotFound() {
        Order order = new Order();
        order.setIdRestaurant(ConstTest.ID_TEST);
        order.setDishes(Collections.singletonList(new OrderedDish(ConstTest.ID_TEST,
                ConstTest.ID_TEST, ConstValidation.TWO)));

        OrderEntity orderEntity = new OrderEntity();

        when(orderEntityMapper.toEntity(any(Order.class))).thenReturn(orderEntity);
        when(restaurantRepository.findById(any(Long.class))).thenReturn(Optional.empty());

        assertFalse(orderJpaAdapter.createOrder(order));
    }

    @Test
    void createOrder_dishNotFound() {
        Order order = new Order();
        order.setIdRestaurant(ConstTest.ID_TEST);
        order.setDishes(Collections.singletonList(new OrderedDish(ConstTest.ID_TEST, ConstTest.ID_TEST,
                ConstValidation.TWO)));

        OrderEntity orderEntity = new OrderEntity();
        RestaurantEntity restaurantEntity = new RestaurantEntity();
        restaurantEntity.setId(ConstTest.ID_TEST);
        restaurantEntity.setName(ConstTest.NAME_TEST);
        restaurantEntity.setAddress(ConstTest.ADDRESS_TEST);
        restaurantEntity.setPhone(ConstTest.PHONE_TEST);
        orderEntity.setRestaurant(restaurantEntity);

        when(orderEntityMapper.toEntity(any(Order.class))).thenReturn(orderEntity);
        when(restaurantRepository.findById(any(Long.class))).thenReturn(Optional.of(restaurantEntity));
        when(dishRepository.findById(any(Long.class))).thenReturn(Optional.empty());

        assertFalse(orderJpaAdapter.createOrder(order));
    }

    @Test
    void createOrder_noDishes() {
        Order order = new Order();
        order.setIdRestaurant(ConstTest.ID_TEST);
        order.setDishes(Collections.emptyList());

        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setRestaurant(new RestaurantEntity());

        when(orderEntityMapper.toEntity(any(Order.class))).thenReturn(orderEntity);
        when(restaurantRepository.findById(any(Long.class))).thenReturn(Optional.of(new RestaurantEntity()));

        assertFalse(orderJpaAdapter.createOrder(order));
    }


    @Test
    void findOrderByClientId_orderExists() {
        when(orderRepository.existsByIdClientAndStatusIsLikeOrStatusIsLike(any(Long.class), any(String.class),
                any(String.class)))
                .thenReturn(true);

        assertTrue(orderJpaAdapter.findOrderByClientId(ConstTest.ID_TEST));
    }
    @Test
    void findOrderByClientId_orderDoesNotExist() {
        when(orderRepository.existsByIdClientAndStatusIsLikeOrStatusIsLike(any(Long.class), any(String.class),
                any(String.class)))
                .thenReturn(false);

        assertFalse(orderJpaAdapter.findOrderByClientId(ConstTest.ID_TEST));
    }

}