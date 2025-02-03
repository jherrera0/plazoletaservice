package com.backendchallenge.plazoletaservice.application.http.mapper;

import com.backendchallenge.plazoletaservice.application.http.dto.request.OrderRequest;
import com.backendchallenge.plazoletaservice.domain.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        uses = {IOrderedDishRequestMapper.class},
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IOrderRequestMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "idClient", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "idEmployee", ignore = true)
    @Mapping(target = "dishes", source = "dishes")
    Order toDomain(OrderRequest orderRequest);
}
