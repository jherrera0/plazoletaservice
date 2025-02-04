package com.backendchallenge.plazoletaservice.application.jpa.mapper;

import com.backendchallenge.plazoletaservice.application.jpa.entity.OrderedDishEntity;
import com.backendchallenge.plazoletaservice.domain.model.OrderedDish;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)

public interface IOrderedDishEntityMapper {
    OrderedDishEntity toEntity(OrderedDish orderedDish);
    @Mapping(target = "idDish", source = "dish.id")
    OrderedDish toDomain(OrderedDishEntity orderedDishEntity);

    List<OrderedDish> toDomainList(List<OrderedDishEntity> orderedDishEntities);
}
