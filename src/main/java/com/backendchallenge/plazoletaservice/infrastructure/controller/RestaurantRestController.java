package com.backendchallenge.plazoletaservice.infrastructure.controller;

import com.backendchallenge.plazoletaservice.application.http.dto.CreateRestaurantRequest;
import com.backendchallenge.plazoletaservice.application.http.handler.interfaces.IRestaurantHandler;
import com.backendchallenge.plazoletaservice.domain.until.ConstRute;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(ConstRute.RESTAURANT)
public class RestaurantRestController {
    private final IRestaurantHandler restaurantHandler;
    @PostMapping(ConstRute.CREATE_RESTAURANT)
    public void createRestaurant(@RequestBody @Valid CreateRestaurantRequest restaurantRequest) {
        restaurantHandler.createRestaurant(restaurantRequest);
    }
}
