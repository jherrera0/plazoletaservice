package com.backendchallenge.plazoletaservice.application.jpa.mapper;

import com.backendchallenge.plazoletaservice.application.jpa.entity.DishEntity;
import com.backendchallenge.plazoletaservice.domain.model.Dish;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)

public interface IDishEntityMapper {
    DishEntity toEntity(Dish dish);
    @Mapping(target = "id", source = "id")
    @Mapping(target = "idRestaurant", source = "restaurant.id")
    Dish toDomain(DishEntity dishEntity);
    List<Dish> toDomainList(List<DishEntity> content);
}
