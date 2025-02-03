package com.backendchallenge.plazoletaservice.infrastructure.controller;

import com.backendchallenge.plazoletaservice.application.http.dto.request.OrderRequest;
import com.backendchallenge.plazoletaservice.application.http.handler.interfaces.IOrderHandler;
import com.backendchallenge.plazoletaservice.domain.until.ConstDocumentation;
import com.backendchallenge.plazoletaservice.domain.until.ConstRoute;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
    @PostMapping(ConstRoute.CREATE_ORDER)
    public ResponseEntity<String> createOrder(@RequestBody @Valid OrderRequest orderRequest) {
        orderHandler.createOrder(orderRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


}
