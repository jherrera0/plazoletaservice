package com.backendchallenge.plazoletaservice.infrastructure.controller;

import com.backendchallenge.plazoletaservice.application.http.dto.request.EmployeeRequest;
import com.backendchallenge.plazoletaservice.application.http.dto.request.ListOrderRequest;
import com.backendchallenge.plazoletaservice.application.http.dto.request.OrderDeliveredRequest;
import com.backendchallenge.plazoletaservice.application.http.dto.request.OrderRequest;
import com.backendchallenge.plazoletaservice.application.http.dto.response.OrderResponse;
import com.backendchallenge.plazoletaservice.application.http.dto.response.PageResponse;
import com.backendchallenge.plazoletaservice.application.http.handler.interfaces.IOrderHandler;
import com.backendchallenge.plazoletaservice.domain.until.ConstDocumentation;
import com.backendchallenge.plazoletaservice.domain.until.ConstJwt;
import com.backendchallenge.plazoletaservice.domain.until.ConstRoute;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping(ConstRoute.ORDER)
@Tag(name = ConstDocumentation.ORDER_TAG_NAME, description = ConstDocumentation.ORDER_TAG_DESCRIPTION)
public class OrderRestController {
    private final IOrderHandler orderHandler;

    @Operation(summary = ConstDocumentation.CREATE_ORDER_OPERATION)
    @ApiResponses(value = {
            @ApiResponse(responseCode = ConstDocumentation.CODE_201, description = ConstDocumentation.CREATE_ORDER_CODE_201),
            @ApiResponse(responseCode = ConstDocumentation.CODE_400, description = ConstDocumentation.CREATE_ORDER_CODE_400),
    })
    @PreAuthorize(ConstJwt.HAS_AUTHORITY_CLIENT)
    @PostMapping(ConstRoute.CREATE_ORDER)
    public ResponseEntity<String> createOrder(@RequestBody @Valid OrderRequest orderRequest) {
        orderHandler.createOrder(orderRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = ConstDocumentation.LIST_ORDERS_OPERATION)
    @ApiResponses(value = {
            @ApiResponse(responseCode = ConstDocumentation.CODE_201, description = ConstDocumentation.LIST_ORDERS_CODE_201),
            @ApiResponse(responseCode = ConstDocumentation.CODE_400, description = ConstDocumentation.LIST_ORDERS_CODE_400),
    })
    @PreAuthorize(ConstJwt.HAS_AUTHORITY_EMPLOYEE)
    @GetMapping(ConstRoute.LIST_ORDERS)
    public ResponseEntity<PageResponse<OrderResponse>> listOrders(@RequestBody @Valid ListOrderRequest listOrdersRequest) {
        return ResponseEntity.ok(orderHandler.getOrders(listOrdersRequest));
    }


    @Operation(summary = ConstDocumentation.ASSIGN_EMPLOYEE_TO_ORDER_OPERATION)
    @ApiResponses(value = {
            @ApiResponse(responseCode = ConstDocumentation.CODE_201,
                    description = ConstDocumentation.ASSIGN_EMPLOYEE_TO_ORDER_CODE_201),
            @ApiResponse(responseCode = ConstDocumentation.CODE_400,
                    description = ConstDocumentation.ASSIGN_EMPLOYEE_TO_ORDER_CODE_400),
            @ApiResponse(responseCode = ConstDocumentation.CODE_403,
                    description = ConstDocumentation.ASSIGN_EMPLOYEE_TO_ORDER_CODE_403),
    })
    @PreAuthorize(ConstJwt.HAS_AUTHORITY_EMPLOYEE)
    @PostMapping(ConstRoute.ASSIGN_EMPLOYEE_TO_ORDER)
    public ResponseEntity<String> assignEmployeeToOrder(@RequestBody @Valid EmployeeRequest employeeRequest) {
        orderHandler.assignEmployee(employeeRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = ConstDocumentation.ORDER_READY_OPERATION)
    @ApiResponses(value = {
            @ApiResponse(responseCode = ConstDocumentation.CODE_201, description = ConstDocumentation.ORDER_READY_CODE_201),
            @ApiResponse(responseCode = ConstDocumentation.CODE_400, description = ConstDocumentation.ORDER_READY_CODE_400),
            @ApiResponse(responseCode = ConstDocumentation.CODE_403, description = ConstDocumentation.ORDER_READY_CODE_403),
    })
    @PreAuthorize(ConstJwt.HAS_AUTHORITY_EMPLOYEE)
    @PostMapping(ConstRoute.ORDER_READY)
    public ResponseEntity<String> orderReady(@RequestBody @Valid EmployeeRequest request) {
        orderHandler.orderReady(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = ConstDocumentation.DELIVER_ORDER_OPERATION)
    @ApiResponses(value = {
            @ApiResponse(responseCode = ConstDocumentation.CODE_201,
                    description = ConstDocumentation.DELIVER_ORDER_CODE_201),
            @ApiResponse(responseCode = ConstDocumentation.CODE_400,
                    description = ConstDocumentation.DELIVER_ORDER_CODE_400),
            @ApiResponse(responseCode = ConstDocumentation.CODE_403,
                    description = ConstDocumentation.DELIVER_ORDER_CODE_403),
    })
    @PreAuthorize(ConstJwt.HAS_AUTHORITY_EMPLOYEE)
    @PostMapping(ConstRoute.DELIVER_ORDER)
    public ResponseEntity<String> deliverOrder(@RequestBody @Valid OrderDeliveredRequest request) {
        orderHandler.orderDelivered(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = ConstDocumentation.CANCEL_ORDER_OPERATION)
    @ApiResponses(value = {
            @ApiResponse(responseCode = ConstDocumentation.CODE_201,
                    description = ConstDocumentation.CANCEL_ORDER_CODE_201),
            @ApiResponse(responseCode = ConstDocumentation.CODE_400,
                    description = ConstDocumentation.CANCEL_ORDER_CODE_400),
            @ApiResponse(responseCode = ConstDocumentation.CODE_403,
                    description = ConstDocumentation.CANCEL_ORDER_CODE_403),
    })
    @PreAuthorize(ConstJwt.HAS_AUTHORITY_CLIENT)
    @PostMapping(ConstRoute.CANCEL_ORDER)
    public ResponseEntity<Void> cancelOrder(@RequestBody @Valid EmployeeRequest request) {
        orderHandler.cancelOrder(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
