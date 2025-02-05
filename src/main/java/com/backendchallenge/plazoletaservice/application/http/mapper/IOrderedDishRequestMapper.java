package com.backendchallenge.plazoletaservice.application.http.mapper;

import com.backendchallenge.plazoletaservice.application.http.dto.request.OrderedDishRequest;
import com.backendchallenge.plazoletaservice.domain.model.OrderedDish;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)

public interface IOrderedDishRequestMapper {

    OrderedDish toDomain(OrderedDishRequest orderedDishRequest);
}
