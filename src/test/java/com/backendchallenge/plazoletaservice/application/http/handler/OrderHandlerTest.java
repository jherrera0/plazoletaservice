package com.backendchallenge.plazoletaservice.application.http.handler;

import com.backendchallenge.plazoletaservice.application.http.dto.request.OrderRequest;
import com.backendchallenge.plazoletaservice.application.http.mapper.IOrderRequestMapper;
import com.backendchallenge.plazoletaservice.domain.api.IOrderServicePort;
import com.backendchallenge.plazoletaservice.domain.model.Order;
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

import static org.mockito.Mockito.*;

class OrderHandlerTest {

    @Mock
    private IOrderRequestMapper orderRequestMapper;

    @Mock
    private IOrderServicePort orderServicePort;

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

}