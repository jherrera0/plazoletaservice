package com.backendchallenge.plazoletaservice.infrastructure.controller;

import com.backendchallenge.plazoletaservice.application.http.dto.request.ListOrderRequest;
import com.backendchallenge.plazoletaservice.application.http.dto.request.OrderRequest;
import com.backendchallenge.plazoletaservice.application.http.dto.response.OrderResponse;
import com.backendchallenge.plazoletaservice.application.http.dto.response.PageResponse;
import com.backendchallenge.plazoletaservice.application.http.handler.interfaces.IOrderHandler;
import com.backendchallenge.plazoletaservice.domain.until.ConstValidation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class OrderRestControllerTest {

    @Mock
    private IOrderHandler orderHandler;

    @InjectMocks
    private OrderRestController orderRestController;

    AutoCloseable closeable;
    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @Test
    void createOrder_withValidRequest_returnsCreatedStatus() {
        OrderRequest orderRequest = new OrderRequest();
        ResponseEntity<String> response = orderRestController.createOrder(orderRequest);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        verify(orderHandler, times(ConstValidation.ONE)).createOrder(orderRequest);
    }
    @Test
    void listOrders_withValidRequest_returnsOkStatus() {
        ListOrderRequest listOrderRequest = new ListOrderRequest();
        PageResponse<OrderResponse> pageResponse = new PageResponse<>();
        when(orderHandler.getOrders(listOrderRequest)).thenReturn(pageResponse);

        ResponseEntity<PageResponse<OrderResponse>> response = orderRestController.listOrders(listOrderRequest);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(pageResponse, response.getBody());
        verify(orderHandler, times(ConstValidation.ONE)).getOrders(listOrderRequest);
    }
}