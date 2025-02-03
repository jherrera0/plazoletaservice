package com.backendchallenge.plazoletaservice.application.jpa.mapper;

import com.backendchallenge.plazoletaservice.application.jpa.entity.OrderedDishEntity;
import com.backendchallenge.plazoletaservice.domain.model.OrderedDish;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)

public interface IOrderedDishEntityMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "dish", ignore = true)
    OrderedDishEntity toEntity(OrderedDish orderedDish);
}
