package com.backendchallenge.plazoletaservice.application.http.mapper;

import com.backendchallenge.plazoletaservice.application.http.dto.response.DishResponse;
import com.backendchallenge.plazoletaservice.application.http.dto.response.PageResponse;
import com.backendchallenge.plazoletaservice.application.http.dto.response.RestaurantCustomResponse;
import com.backendchallenge.plazoletaservice.domain.model.Dish;
import com.backendchallenge.plazoletaservice.domain.model.PageCustom;
import com.backendchallenge.plazoletaservice.domain.model.Restaurant;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        uses = {ICustomResponseMapper.class},
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)


public interface IPageResponseMapper {
    @Mapping(target = "items", source = "items")
    PageResponse<RestaurantCustomResponse> toPageResponseOfCustomRestaurant(PageCustom<Restaurant> pageCustom);
    PageResponse<DishResponse> toPageResponseOfDishResponse(PageCustom<Dish> dishesByRestaurant);
}
