package com.backendchallenge.plazoletaservice.application.jpa.mapper;

import com.backendchallenge.plazoletaservice.application.jpa.entity.OrderEntity;
import com.backendchallenge.plazoletaservice.domain.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)

public interface IOrderEntityMapper {
    @Mapping(target = "idRestaurant", source = "restaurant.id")
    Order toDomain(OrderEntity orderEntity);
    List<Order> toDomainList(List<OrderEntity> orderEntities);
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "restaurant", ignore = true)
    OrderEntity toEntity(Order order);
}
