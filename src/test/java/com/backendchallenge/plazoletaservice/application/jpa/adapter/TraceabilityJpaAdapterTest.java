package com.backendchallenge.plazoletaservice.application.jpa.adapter;

import com.backendchallenge.plazoletaservice.application.feign.ITraceabilityFeignClient;
import com.backendchallenge.plazoletaservice.application.http.dto.request.AssignEmployeeToOrderTraceabilityRequest;
import com.backendchallenge.plazoletaservice.application.http.dto.request.OrderTraceabilityRequest;
import com.backendchallenge.plazoletaservice.application.http.dto.request.UpdateOrderTraceabilityRequest;
import com.backendchallenge.plazoletaservice.domain.model.Order;
import com.backendchallenge.plazoletaservice.domain.model.OrderedDish;
import com.backendchallenge.plazoletaservice.domain.until.ConstTest;
import com.backendchallenge.plazoletaservice.domain.until.ConstValidation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TraceabilityJpaAdapterTest {
    @Mock
    private ITraceabilityFeignClient traceabilityFeignClient;

    @InjectMocks
    private TraceabilityJpaAdapter traceabilityJpaAdapter;

    AutoCloseable autoCloseable;
    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
    }
    @Test
    void createOrderTraceability_successful() {
        Order order = new Order();
        order.setId(ConstTest.ID_TEST);
        order.setIdClient(ConstTest.ID_TEST);
        order.setIdEmployee(ConstTest.ID_TEST);
        order.setIdRestaurant(ConstTest.ID_TEST);
        order.setDishes(Collections.singletonList(new OrderedDish(ConstTest.ID_TEST, ConstTest.ID_TEST,
                ConstValidation.TWO)));
        order.setStatus(ConstValidation.COMPLETED);

        doNothing().when(traceabilityFeignClient).createOrderTraceability(any(OrderTraceabilityRequest.class));

        traceabilityJpaAdapter.createOrderTraceability(order);

        verify(traceabilityFeignClient, times(ConstValidation.ONE)).createOrderTraceability(any(OrderTraceabilityRequest.class));
    }
    @Test
    void assignEmployeeToOrder_successful() {
        Long idOrder = ConstTest.ID_TEST;
        Long idEmployee = ConstTest.ID_TEST;

        doNothing().when(traceabilityFeignClient).assignEmployeeToOrderTraceability(
                any(AssignEmployeeToOrderTraceabilityRequest.class));

        traceabilityJpaAdapter.assignEmployeeToOrder(idOrder, idEmployee);

        verify(traceabilityFeignClient, times(ConstValidation.ONE))
                .assignEmployeeToOrderTraceability(any(AssignEmployeeToOrderTraceabilityRequest.class));
    }
    @Test
    void updateOrderTraceability_successful() {
        Long idOrder = ConstTest.ID_TEST;
        String status = ConstValidation.COMPLETED;
        LocalDateTime date = LocalDateTime.now();

        doNothing().when(traceabilityFeignClient).updateOrderTraceability(
                any(UpdateOrderTraceabilityRequest.class));

        traceabilityJpaAdapter.updateOrderTraceability(idOrder, status, date);

        verify(traceabilityFeignClient, times(ConstValidation.ONE)).updateOrderTraceability(
                any(UpdateOrderTraceabilityRequest.class));
    }
    @Test
    void createOrderTraceability_nullOrder() {
        assertThrows(NullPointerException.class, () -> traceabilityJpaAdapter.createOrderTraceability(null));
    }

}