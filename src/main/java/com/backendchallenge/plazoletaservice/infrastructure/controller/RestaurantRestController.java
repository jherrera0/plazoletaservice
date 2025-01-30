package com.backendchallenge.plazoletaservice.infrastructure.controller;

import com.backendchallenge.plazoletaservice.application.http.dto.CreateRestaurantRequest;
import com.backendchallenge.plazoletaservice.application.http.handler.interfaces.IRestaurantHandler;
import com.backendchallenge.plazoletaservice.domain.until.ConstDocumentation;
import com.backendchallenge.plazoletaservice.domain.until.ConstJwt;
import com.backendchallenge.plazoletaservice.domain.until.ConstRoute;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping(ConstRoute.RESTAURANT)
@Tag(name = ConstDocumentation.RESTAURANT_TAG_NAME, description = ConstDocumentation.RESTAURANT_TAG_DESCRIPTION)
public class RestaurantRestController {
    private final IRestaurantHandler restaurantHandler;
    @Operation(summary = ConstDocumentation.CREATE_RESTAURANT_OPERATION)
    @ApiResponses(value = {
            @ApiResponse(responseCode = ConstDocumentation.CODE_201, description = ConstDocumentation.CREATE_RESTAURANT_CODE_201),
            @ApiResponse(responseCode = ConstDocumentation.CODE_400, description = ConstDocumentation.CREATE_RESTAURANT_CODE_400),
    })
    @PreAuthorize(ConstJwt.HAS_AUTHORITY_ADMIN)
    @PostMapping(ConstRoute.CREATE_RESTAURANT)
    public void createRestaurant(@RequestBody @Valid CreateRestaurantRequest restaurantRequest, @RequestHeader(ConstJwt.HEADER_STRING) String token) {
        restaurantHandler.createRestaurant(restaurantRequest, token);
    }
}
