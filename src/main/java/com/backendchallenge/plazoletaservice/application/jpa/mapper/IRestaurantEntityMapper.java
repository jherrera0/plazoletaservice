package com.backendchallenge.plazoletaservice.application.jpa.mapper;

import com.backendchallenge.plazoletaservice.application.jpa.entity.RestaurantEntity;
import com.backendchallenge.plazoletaservice.domain.model.Restaurant;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper (componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)

public interface IRestaurantEntityMapper {
    @Mapping(target = "id", ignore = true)
    RestaurantEntity toEntity(Restaurant restaurant);
    Restaurant toModel(RestaurantEntity restaurantEntity);
    List<Restaurant> toModelList(List<RestaurantEntity> content);
}
