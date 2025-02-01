package com.backendchallenge.plazoletaservice.application.http.mapper;

import com.backendchallenge.plazoletaservice.application.http.dto.response.DishResponse;
import com.backendchallenge.plazoletaservice.application.http.dto.response.RestaurantCustomResponse;
import com.backendchallenge.plazoletaservice.domain.model.Dish;
import com.backendchallenge.plazoletaservice.domain.model.Restaurant;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        uses = {ICategoryResponseMapper.class},
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)

public interface ICustomResponseMapper {
    @Mapping(target = "name", source = "name")
    @Mapping(target="urlLogo", source="urlLogo")
    RestaurantCustomResponse toRestaurantCustomResponse(Restaurant restaurant);
    DishResponse toDishResponse(Dish dish);
}
