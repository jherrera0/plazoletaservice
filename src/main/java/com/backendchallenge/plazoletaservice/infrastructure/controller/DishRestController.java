package com.backendchallenge.plazoletaservice.infrastructure.controller;

import com.backendchallenge.plazoletaservice.application.http.dto.CreateDishRequest;
import com.backendchallenge.plazoletaservice.application.http.handler.interfaces.IDishHandler;
import com.backendchallenge.plazoletaservice.domain.until.ConstDocumentation;
import com.backendchallenge.plazoletaservice.domain.until.ConstRoute;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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
    })
    @PostMapping(ConstRoute.CREATE_DISH)
    public void createDish(@RequestBody @Valid CreateDishRequest request, Long ownerId) {
        dishHandler.createDish(request,ownerId);
    }
}
