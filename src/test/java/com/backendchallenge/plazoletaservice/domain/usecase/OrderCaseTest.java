package com.backendchallenge.plazoletaservice.domain.usecase;

import com.backendchallenge.plazoletaservice.domain.exceptions.employeeexcepcion.EmployeeNotBelongToRestaurantException;
import com.backendchallenge.plazoletaservice.domain.exceptions.orderexceptions.*;
import com.backendchallenge.plazoletaservice.domain.exceptions.restaurantexceptions.RestaurantNotFoundException;
import com.backendchallenge.plazoletaservice.domain.model.Order;
import com.backendchallenge.plazoletaservice.domain.model.OrderedDish;
import com.backendchallenge.plazoletaservice.domain.model.PageCustom;
import com.backendchallenge.plazoletaservice.domain.spi.*;
import com.backendchallenge.plazoletaservice.domain.until.ConstTest;
import com.backendchallenge.plazoletaservice.domain.until.ConstValidation;
import com.backendchallenge.plazoletaservice.domain.until.TokenHolder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.MockitoAnnotations;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class OrderCaseTest {

    @Mock
    private IOrderPersistencePort orderPersistencePort;

    @Mock
    private IRestaurantPersistencePort restaurantPersistencePort;

    @Mock
    private IDishPersistencePort dishPersistencePort;

    @Mock
    private IJwtPersistencePort jwtPersistencePort;

    @Mock
    private IUserPersistencePort userPersistencePort;

    @InjectMocks
    private OrderCase orderCase;

    private AutoCloseable closeable;
    private MockedStatic<TokenHolder> mockedTokenHolder;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
        mockedTokenHolder = mockStatic(TokenHolder.class);
        mockedTokenHolder.when(TokenHolder::getTokenValue).thenReturn(ConstTest.VALID_TOKEN);
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
        mockedTokenHolder.close();
    }

    @Test
    void createOrder_successful() {
        Order order = new Order();
        order.setIdRestaurant(ConstTest.ID_TEST);
        order.setDishes(Collections.singletonList(new OrderedDish(ConstTest.ID_TEST, ConstTest.ID_TEST, ConstValidation.TWO)));

        when(jwtPersistencePort.getUserId(anyString())).thenReturn(ConstTest.ID_TEST);
        when(orderPersistencePort.findOrderByClientId(anyLong())).thenReturn(false);
        when(restaurantPersistencePort.existsRestaurantById(anyLong())).thenReturn(true);
        when(dishPersistencePort.getRestaurantIdByDishId(anyLong())).thenReturn(ConstTest.ID_TEST);
        when(orderPersistencePort.createOrder(any(Order.class))).thenReturn(true);

        orderCase.createOrder(order);

        verify(orderPersistencePort, times(1)).createOrder(any(Order.class));
    }

    @Test
    void createOrder_orderAlreadyExists() {
        Order order = new Order();
        order.setIdRestaurant(ConstTest.ID_TEST);
        order.setDishes(Collections.singletonList(new OrderedDish(ConstTest.ID_TEST, ConstTest.ID_TEST, ConstValidation.TWO)));

        when(jwtPersistencePort.getUserId(anyString())).thenReturn(ConstTest.ID_TEST);
        when(orderPersistencePort.findOrderByClientId(anyLong())).thenReturn(true);

        assertThrows(OrderAlreadyExistsException.class, () -> orderCase.createOrder(order));
    }

    @Test
    void createOrder_invalidRestaurantId() {
        Order order = new Order();
        order.setIdRestaurant(Long.valueOf(ConstValidation.ZERO));

        when(jwtPersistencePort.getUserId(anyString())).thenReturn(ConstTest.ID_TEST);

        assertThrows(OrderIdRestaurantInvalidException.class, () -> orderCase.createOrder(order));
    }

    @Test
    void createOrder_restaurantNotFound() {
        Order order = new Order();
        order.setIdRestaurant(ConstTest.ID_TEST);
        order.setDishes(Collections.singletonList(new OrderedDish(ConstTest.ID_TEST, ConstTest.ID_TEST, ConstValidation.TWO)));

        when(jwtPersistencePort.getUserId(anyString())).thenReturn(ConstTest.ID_TEST);
        when(orderPersistencePort.findOrderByClientId(anyLong())).thenReturn(false);
        when(restaurantPersistencePort.existsRestaurantById(anyLong())).thenReturn(false);

        assertThrows(RestaurantNotFoundException.class, () -> orderCase.createOrder(order));
    }

    @Test
    void createOrder_dishesNotEmpty() {
        Order order = new Order();
        order.setIdRestaurant(ConstTest.ID_TEST);
        order.setDishes(Collections.emptyList());

        when(jwtPersistencePort.getUserId(anyString())).thenReturn(ConstTest.ID_TEST);
        when(orderPersistencePort.findOrderByClientId(anyLong())).thenReturn(false);
        when(restaurantPersistencePort.existsRestaurantById(anyLong())).thenReturn(true);

        assertThrows(OrderDishesNotEmptyException.class, () -> orderCase.createOrder(order));
    }

    @Test
    void createOrder_dishNotFoundInRestaurant() {
        Order order = new Order();
        order.setIdRestaurant(ConstTest.ID_TEST);
        order.setDishes(Collections.singletonList(new OrderedDish(ConstTest.ID_TEST, ConstTest.ID_TEST, ConstValidation.TWO)));

        when(jwtPersistencePort.getUserId(anyString())).thenReturn(ConstTest.ID_TEST);
        when(orderPersistencePort.findOrderByClientId(anyLong())).thenReturn(false);
        when(restaurantPersistencePort.existsRestaurantById(anyLong())).thenReturn(true);
        when(dishPersistencePort.getRestaurantIdByDishId(anyLong())).thenReturn(ConstTest.ID_TEST + 1);

        assertThrows(DishNotFoundInRestaurantException.class, () -> orderCase.createOrder(order));
    }

    @Test
    void createOrder_invalidDishId() {
        Order order = new Order();
        order.setIdRestaurant(ConstTest.ID_TEST);
        order.setDishes(Collections.singletonList(new OrderedDish(ConstValidation.ZERO.longValue(), ConstValidation.ZERO.longValue(), ConstValidation.TWO)));

        when(jwtPersistencePort.getUserId(anyString())).thenReturn(ConstTest.ID_TEST);
        when(orderPersistencePort.findOrderByClientId(anyLong())).thenReturn(false);
        when(restaurantPersistencePort.existsRestaurantById(anyLong())).thenReturn(true);
        when(dishPersistencePort.getRestaurantIdByDishId(anyLong())).thenReturn(ConstTest.ID_TEST);

        assertThrows(OrderDishIdInvalidException.class, () -> orderCase.createOrder(order));
    }

    @Test
    void createOrder_invalidDishQuantity() {
        Order order = new Order();
        order.setIdRestaurant(ConstTest.ID_TEST);
        order.setDishes(Collections.singletonList(new OrderedDish(ConstTest.ID_TEST, ConstTest.ID_TEST, ConstValidation.ZERO)));

        when(jwtPersistencePort.getUserId(anyString())).thenReturn(ConstTest.ID_TEST);
        when(orderPersistencePort.findOrderByClientId(anyLong())).thenReturn(false);
        when(restaurantPersistencePort.existsRestaurantById(anyLong())).thenReturn(true);
        when(dishPersistencePort.getRestaurantIdByDishId(anyLong())).thenReturn(ConstTest.ID_TEST);

        assertThrows(OrderDishQuantityInvalidException.class, () -> orderCase.createOrder(order));
    }

    @Test
    void createOrder_orderNotCreated() {
        Order order = new Order();
        order.setIdRestaurant(ConstTest.ID_TEST);
        order.setDishes(Collections.singletonList(new OrderedDish(ConstTest.ID_TEST, ConstTest.ID_TEST, ConstValidation.TWO)));

        when(jwtPersistencePort.getUserId(anyString())).thenReturn(ConstTest.ID_TEST);
        when(orderPersistencePort.findOrderByClientId(anyLong())).thenReturn(false);
        when(restaurantPersistencePort.existsRestaurantById(anyLong())).thenReturn(true);
        when(dishPersistencePort.getRestaurantIdByDishId(anyLong())).thenReturn(ConstTest.ID_TEST);
        when(orderPersistencePort.createOrder(any(Order.class))).thenReturn(false);

        assertThrows(OrderNotCreatedException.class, () -> orderCase.createOrder(order));
    }

    @Test
    void getOrders_successful() {
        PageCustom<Order> expectedPageCustom = new PageCustom<>(ConstTest.VALID_CURRENT_PAGE,
                ConstTest.VALID_PAGE_SIZE,
                ConstValidation.ONE,
                Collections.singletonList(new Order()));
        when(orderPersistencePort.getOrders(anyLong(), anyInt(), anyInt(), anyString(), anyString()))
                .thenReturn(expectedPageCustom);
        when(jwtPersistencePort.getUserId(anyString())).thenReturn(ConstTest.ID_TEST);
        when(restaurantPersistencePort.existsRestaurantById(anyLong())).thenReturn(true);
        when(userPersistencePort.findEmployeeByIds(anyLong(), anyLong())).thenReturn(true);

        PageCustom<Order> result = orderCase.getOrders(ConstTest.VALID_ID_RESTAURANT,
                ConstTest.VALID_CURRENT_PAGE, ConstTest.VALID_PAGE_SIZE,
                ConstTest.VALID_FILTER_BY,
                ConstTest.VALID_ORDER_DIRECTION);

        assertEquals(expectedPageCustom, result);
    }

    @Test
    void getOrders_invalidCurrentPage() {
        when(jwtPersistencePort.getUserId(anyString())).thenReturn(ConstTest.ID_TEST);
        when(restaurantPersistencePort.existsRestaurantById(anyLong())).thenReturn(true);
        when(userPersistencePort.findEmployeeByIds(anyLong(), anyLong())).thenReturn(true);

        assertThrows(OrderCurrentPageInvalidException.class, () -> orderCase.getOrders(ConstTest.VALID_ID_RESTAURANT,
                null, ConstTest.VALID_PAGE_SIZE,
                ConstTest.VALID_FILTER_BY,
                ConstTest.VALID_ORDER_DIRECTION));
    }

    @Test
    void getOrders_invalidPageSize() {
        when(jwtPersistencePort.getUserId(anyString())).thenReturn(ConstTest.ID_TEST);
        when(restaurantPersistencePort.existsRestaurantById(anyLong())).thenReturn(true);
        when(userPersistencePort.findEmployeeByIds(anyLong(), anyLong())).thenReturn(true);
        when(orderPersistencePort.getOrders(anyLong(), anyInt(), anyInt(), anyString(), anyString())).thenReturn(null);

        assertThrows(OrderPageSizeInvalidException.class, () -> orderCase.getOrders(ConstTest.VALID_ID_RESTAURANT,
                ConstTest.VALID_CURRENT_PAGE, ConstTest.INVALID_PAGE_SIZE,
                ConstTest.VALID_FILTER_BY,
                ConstTest.VALID_ORDER_DIRECTION));
    }

    @Test
    void getOrders_invalidFilterBy() {
        when(jwtPersistencePort.getUserId(anyString())).thenReturn(ConstTest.ID_TEST);
        when(restaurantPersistencePort.existsRestaurantById(anyLong())).thenReturn(true);
        when(userPersistencePort.findEmployeeByIds(anyLong(), anyLong())).thenReturn(true);
        when(orderPersistencePort.getOrders(anyLong(), anyInt(), anyInt(), anyString(), anyString())).thenReturn(null);

        assertThrows(OrderFilterByInvalidException.class, () -> orderCase.getOrders(ConstTest.VALID_ID_RESTAURANT,
                ConstTest.VALID_CURRENT_PAGE, ConstTest.VALID_PAGE_SIZE,
                null,
                ConstTest.VALID_ORDER_DIRECTION));
    }

    @Test
    void getOrders_invalidOrderDirection() {
        when(jwtPersistencePort.getUserId(anyString())).thenReturn(ConstTest.ID_TEST);
        when(restaurantPersistencePort.existsRestaurantById(anyLong())).thenReturn(true);
        when(userPersistencePort.findEmployeeByIds(anyLong(), anyLong())).thenReturn(true);
        when(orderPersistencePort.getOrders(anyLong(), anyInt(), anyInt(), anyString(), anyString())).thenReturn(null);

        assertThrows(OrderOrderDirectionInvalidException.class, () -> orderCase.getOrders(ConstTest.VALID_ID_RESTAURANT,
                ConstTest.VALID_CURRENT_PAGE, ConstTest.VALID_PAGE_SIZE,
                ConstTest.VALID_FILTER_BY,
                ConstTest.INVALID_ORDER_DIRECTION));
    }

    @Test
    void getOrders_restaurantNotFound() {
        when(jwtPersistencePort.getUserId(anyString())).thenReturn(ConstTest.ID_TEST);
        when(restaurantPersistencePort.existsRestaurantById(anyLong())).thenReturn(false);

        assertThrows(RestaurantNotFoundException.class, () -> orderCase.getOrders(ConstTest.VALID_ID_RESTAURANT,
                ConstTest.VALID_CURRENT_PAGE, ConstTest.VALID_PAGE_SIZE,
                ConstTest.VALID_FILTER_BY,
                ConstTest.VALID_ORDER_DIRECTION));
    }

    @Test
    void getOrders_employeeNotBelongToRestaurant() {
        when(jwtPersistencePort.getUserId(anyString())).thenReturn(ConstTest.ID_TEST);
        when(restaurantPersistencePort.existsRestaurantById(anyLong())).thenReturn(true);
        when(userPersistencePort.findEmployeeByIds(anyLong(), anyLong())).thenReturn(false);

        assertThrows(EmployeeNotBelongToRestaurantException.class, () -> orderCase.getOrders(ConstTest.VALID_ID_RESTAURANT,
                ConstTest.VALID_CURRENT_PAGE, ConstTest.VALID_PAGE_SIZE,
                ConstTest.VALID_FILTER_BY,
                ConstTest.VALID_ORDER_DIRECTION));
    }

    @Test
    void getOrders_CurrentPageExceed(){
        when(orderPersistencePort.getOrders(anyLong(), anyInt(), anyInt(), anyString(), anyString()))
                .thenReturn(new PageCustom<>(ConstValidation.MINUS_ONE,null,null,null));
        when(jwtPersistencePort.getUserId(anyString())).thenReturn(ConstTest.ID_TEST);
        when(restaurantPersistencePort.existsRestaurantById(anyLong())).thenReturn(true);
        when(userPersistencePort.findEmployeeByIds(anyLong(), anyLong())).thenReturn(true);
        
        assertThrows(OrderCurrentPageInvalidException.class, () -> orderCase.getOrders(ConstTest.VALID_ID_RESTAURANT,
                ConstTest.CURRENT_PAGE_TEST, ConstTest.VALID_PAGE_SIZE,
                ConstTest.VALID_FILTER_BY,
                ConstTest.VALID_ORDER_DIRECTION));
    }
}