package com.backendchallenge.plazoletaservice.infrastructure.controller;

import com.backendchallenge.plazoletaservice.application.http.dto.request.ChangeStatusRequest;
import com.backendchallenge.plazoletaservice.application.http.dto.request.CreateDishRequest;
import com.backendchallenge.plazoletaservice.application.http.dto.request.UpdateDishRequest;
import com.backendchallenge.plazoletaservice.application.http.handler.interfaces.IDishHandler;
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
@RequestMapping(ConstRoute.DISH)
@Tag(name = ConstDocumentation.DISH_TAG_NAME, description = ConstDocumentation.DISH_TAG_DESCRIPTION)
public class DishRestController {
    private final IDishHandler dishHandler;

    @Operation(summary = ConstDocumentation.CREATE_DISH_OPERATION)
    @ApiResponses(value = {
            @ApiResponse(responseCode = ConstDocumentation.CODE_201, description = ConstDocumentation.CREATE_DISH_CODE_201),
            @ApiResponse(responseCode = ConstDocumentation.CODE_400, description = ConstDocumentation.CREATE_DISH_CODE_400),
            @ApiResponse(responseCode = ConstDocumentation.CODE_403, description = ConstDocumentation.CREATE_DISH_CODE_403),
    })
    @PreAuthorize(ConstJwt.HAS_AUTHORITY_OWNER)
    @PostMapping(ConstRoute.CREATE_DISH)
    public ResponseEntity<Object> createDish(@RequestBody @Valid CreateDishRequest request, @RequestHeader(ConstJwt.HEADER_STRING) String token) {
        dishHandler.createDish(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = ConstDocumentation.UPDATE_DISH_OPERATION)
    @ApiResponses(value = {
            @ApiResponse(responseCode = ConstDocumentation.CODE_201, description = ConstDocumentation.UPDATE_DISH_CODE_201),
            @ApiResponse(responseCode = ConstDocumentation.CODE_400, description = ConstDocumentation.UPDATE_DISH_CODE_400),
            @ApiResponse(responseCode = ConstDocumentation.CODE_403, description = ConstDocumentation.UPDATE_DISH_CODE_403),
    })
    @PreAuthorize(ConstJwt.HAS_AUTHORITY_OWNER)
    @PutMapping(ConstRoute.UPDATE_DISH)
    public ResponseEntity<Object> updateDish(@RequestBody @Valid UpdateDishRequest request) {
        dishHandler.updateDish(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = ConstDocumentation.CHANGE_DISH_STATUS_OPERATION)
    @ApiResponses(value = {
            @ApiResponse(responseCode = ConstDocumentation.CODE_201, description = ConstDocumentation.CHANGE_DISH_STATUS_CODE_201),
            @ApiResponse(responseCode = ConstDocumentation.CODE_400, description = ConstDocumentation.CHANGE_DISH_STATUS_CODE_400),
            @ApiResponse(responseCode = ConstDocumentation.CODE_403, description = ConstDocumentation.CHANGE_DISH_STATUS_CODE_403),
    })
    @PreAuthorize(ConstJwt.HAS_AUTHORITY_OWNER)
    @PutMapping(ConstRoute.CHANGE_DISH_STATUS)
    public ResponseEntity<Object> changeDishStatus(@RequestBody @Valid ChangeStatusRequest request) {
        dishHandler.changeDishStatus(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
