package com.backendchallenge.plazoletaservice.application.jpa.mapper;

import com.backendchallenge.plazoletaservice.application.jpa.entity.OrderEntity;
import com.backendchallenge.plazoletaservice.domain.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)

public interface IOrderEntityMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "restaurant", ignore = true)
    OrderEntity toEntity(Order order);
}
