package com.backendchallenge.plazoletaservice.application.http.handler;

import com.backendchallenge.plazoletaservice.application.http.dto.request.AssignEmployeeRequest;
import com.backendchallenge.plazoletaservice.application.http.dto.request.ListOrderRequest;
import com.backendchallenge.plazoletaservice.application.http.dto.request.OrderRequest;
import com.backendchallenge.plazoletaservice.application.http.dto.response.OrderResponse;
import com.backendchallenge.plazoletaservice.application.http.dto.response.PageResponse;
import com.backendchallenge.plazoletaservice.application.http.mapper.IOrderRequestMapper;
import com.backendchallenge.plazoletaservice.application.http.mapper.IPageResponseMapper;
import com.backendchallenge.plazoletaservice.domain.api.IOrderServicePort;
import com.backendchallenge.plazoletaservice.domain.model.Order;
import com.backendchallenge.plazoletaservice.domain.model.PageCustom;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class OrderHandlerTest {

    @Mock
    private IOrderRequestMapper orderRequestMapper;

    @Mock
    private IOrderServicePort orderServicePort;

    @Mock
    private IPageResponseMapper pageResponseMapper;

    @InjectMocks
    private OrderHandler orderHandler;

    AutoCloseable closeable;

    private static final String VALID_TOKEN = ConstTest.VALID_TOKEN;
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
    void createOrder_withValidRequest() {
        OrderRequest request = new OrderRequest();
        when(orderRequestMapper.toDomain(any(OrderRequest.class))).thenReturn(new Order());
        when(TokenHolder.getToken()).thenReturn(VALID_TOKEN);

        orderHandler.createOrder(request);

        verify(orderRequestMapper, times(ConstValidation.ONE)).toDomain(request);
        verify(orderServicePort, times(ConstValidation.ONE)).createOrder(any(Order.class));
    }

    @Test
    void createOrder_withNullRequest() {
        orderHandler.createOrder(null);

        verify(orderRequestMapper, never()).toDomain(any(OrderRequest.class));
        verify(orderServicePort, never()).createOrder(any(Order.class));
    }
    @Test
    void getOrders_withValidRequest() {
        ListOrderRequest request = new ListOrderRequest(ConstTest.VALID_ID_RESTAURANT,
                ConstTest.VALID_CURRENT_PAGE,
                ConstTest.VALID_PAGE_SIZE,
                ConstTest.VALID_FILTER_BY,
                ConstTest.VALID_ORDER_DIRECTION);
        PageCustom<Order> pageCustom = new PageCustom<>();
        PageResponse<OrderResponse> pageResponse = new PageResponse<>();

        when(orderServicePort.getOrders(ConstTest.VALID_ID_RESTAURANT,
                ConstTest.VALID_CURRENT_PAGE,
                ConstTest.VALID_PAGE_SIZE,
                ConstTest.VALID_FILTER_BY,
                ConstTest.VALID_ORDER_DIRECTION)).thenReturn(pageCustom);
        when(pageResponseMapper.toPageResponseOfOrderResponse(pageCustom)).thenReturn(pageResponse);

        PageResponse<OrderResponse> result = orderHandler.getOrders(request);

        assertEquals(pageResponse, result);
        verify(orderServicePort, times(ConstValidation.ONE)).getOrders(ConstTest.VALID_ID_RESTAURANT,
                ConstTest.VALID_CURRENT_PAGE,
                ConstTest.VALID_PAGE_SIZE,
                ConstTest.VALID_FILTER_BY,
                ConstTest.VALID_ORDER_DIRECTION);
        verify(pageResponseMapper, times(ConstValidation.ONE)).toPageResponseOfOrderResponse(pageCustom);
    }
    @Test
    void assignEmployee_withValidRequest() {
        AssignEmployeeRequest request = new AssignEmployeeRequest(ConstTest.ID_TEST, ConstTest.VALID_ID_RESTAURANT);
        when(TokenHolder.getToken()).thenReturn(ConstTest.VALID_TOKEN);

        orderHandler.assignEmployee(request);

        verify(orderServicePort, times(ConstValidation.ONE)).assignEmployeeToOrder(request.getIdOrder(),
                request.getIdRestaurant());
    }
}