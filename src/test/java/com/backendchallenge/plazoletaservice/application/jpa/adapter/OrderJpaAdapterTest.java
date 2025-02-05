package com.backendchallenge.plazoletaservice.application.jpa.adapter;

import com.backendchallenge.plazoletaservice.application.jpa.entity.DishEntity;
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
import com.backendchallenge.plazoletaservice.domain.model.OrderedDish;
import com.backendchallenge.plazoletaservice.domain.model.PageCustom;
import com.backendchallenge.plazoletaservice.domain.until.ConstTest;
import com.backendchallenge.plazoletaservice.domain.until.ConstValidation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

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

    @Mock
    private IOrderedDishEntityMapper orderedDishEntityMapper;
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
        when(orderRepository.existsByIdClientAndStatusIsLike(any(Long.class),
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
    @Test
    void getOrders_successful() {
        Long idRestaurant = ConstTest.ID_TEST;
        Integer currentPage = ConstTest.CURRENT_PAGE_TEST;
        Integer pageSize = ConstTest.PAGE_SIZE_TEST;
        String filterBy = ConstValidation.PENDING;
        String orderDirection = ConstValidation.ASC;

        List<OrderEntity> orderEntitiesList = Collections.singletonList(new OrderEntity());
        Page<OrderEntity> orderEntities = new PageImpl<>(orderEntitiesList);

        when(orderRepository.findAllByRestaurant_IdAndFilter(any(Long.class), any(String.class), any(Pageable.class)))
                .thenReturn(orderEntities);
        when(orderEntityMapper.toDomainList(anyList())).thenReturn(Collections.singletonList(new Order()));
        when(orderedDishRepository.findAllByOrder_Id(any(Long.class))).thenReturn(Collections.
                singletonList(new OrderedDishEntity()));
        when(orderedDishEntityMapper.toDomainList(anyList())).thenReturn(Collections.singletonList(new OrderedDish()));

        PageCustom<Order> result = orderJpaAdapter.getOrders(idRestaurant, currentPage, pageSize, filterBy,
                orderDirection);

        assertNotNull(result);
        assertEquals(ConstValidation.ONE, result.getItems().size());
    }
    @Test
    void getDishesByRestaurant_handlesInvalidPageRequest() {
        Long idRestaurant = ConstTest.ID_TEST;
        Integer currentPage = ConstTest.CURRENT_PAGE_OVER_VALUE_TEST;
        Integer pageSize = ConstTest.PAGE_SIZE_TEST;
        String filterBy = ConstTest.FILTER_BY_TEST;
        String orderDirection = ConstTest.ORDER_DIRECTION_TEST;
        Page<OrderEntity> page = mock(Page.class);

        when(page.getTotalPages()).thenReturn(ConstValidation.ONE);
        when(orderRepository.findAllByRestaurant_IdAndFilter(any(Long.class), any(String.class), any(Pageable.class)))
                .thenReturn(page);

        PageCustom<Order> result = orderJpaAdapter.getOrders(idRestaurant, currentPage, pageSize, filterBy,
                orderDirection);

        assertNotNull(result);
        assertEquals(ConstValidation.MINUS_ONE, result.getCurrentPage().intValue());
        assertNull(result.getPageSize());
        assertNull(result.getTotalPages());
        assertNull(result.getItems());
    }
    @Test
    void existsOrderById_orderExists() {
        when(orderRepository.existsById(any(Long.class))).thenReturn(true);

        assertTrue(orderJpaAdapter.existsOrderById(ConstTest.ID_TEST));
    }

    @Test
    void existsOrderById_orderDoesNotExist() {
        when(orderRepository.existsById(any(Long.class))).thenReturn(false);

        assertFalse(orderJpaAdapter.existsOrderById(ConstTest.ID_TEST));
    }

    @Test
    void getOrderById_orderExists() {
        OrderEntity orderEntity = new OrderEntity();
        when(orderRepository.findById(any(Long.class))).thenReturn(Optional.of(orderEntity));
        when(orderEntityMapper.toDomain(any(OrderEntity.class))).thenReturn(new Order());

        Order order = orderJpaAdapter.getOrderById(ConstTest.ID_TEST);

        assertNotNull(order);
    }

    @Test
    void getOrderById_orderDoesNotExist() {
        when(orderRepository.findById(any(Long.class))).thenReturn(Optional.empty());
        when(orderEntityMapper.toDomain(any(OrderEntity.class))).thenReturn(new Order());

        Order order = orderJpaAdapter.getOrderById(ConstTest.ID_TEST);

        assertNotNull(order);
    }

    @Test
    void updateOrder_successful() {
        Order order = new Order();
        order.setIdRestaurant(ConstTest.ID_TEST);

        OrderEntity orderEntity = new OrderEntity();
        RestaurantEntity restaurantEntity = new RestaurantEntity();
        restaurantEntity.setId(ConstTest.ID_TEST);
        orderEntity.setRestaurant(restaurantEntity);

        when(orderEntityMapper.toEntity(any(Order.class))).thenReturn(orderEntity);
        when(restaurantRepository.findById(any(Long.class))).thenReturn(Optional.of(restaurantEntity));

        orderJpaAdapter.updateOrder(order);

        verify(orderRepository, times(ConstValidation.ONE)).save(orderEntity);
    }
    @Test
    void getOrderByParams_orderExists() {
        OrderEntity orderEntity = new OrderEntity();
        when(orderRepository.findByStatusAndIdClientAndRestaurant_Id(anyString(), anyLong(), anyLong()))
                .thenReturn(orderEntity);
        when(orderEntityMapper.toDomain(any(OrderEntity.class))).thenReturn(new Order());

        Order order = orderJpaAdapter.getOrderByParams(ConstValidation.PENDING, ConstTest.ID_TEST, ConstTest.ID_TEST);

        assertNotNull(order);
    }
    @Test
    void getOrderByParams_orderDoesNotExist() {
        when(orderRepository.findByStatusAndIdClientAndRestaurant_Id(anyString(), anyLong(), anyLong()))
                .thenReturn(null);
        when(orderEntityMapper.toDomain(any(OrderEntity.class))).thenReturn(null);

        Order order = orderJpaAdapter.getOrderByParams(ConstValidation.PENDING, ConstTest.ID_TEST, ConstTest.ID_TEST);

        assertNull(order);
    }
}