package com.backendchallenge.plazoletaservice.application.http.mapper;

import com.backendchallenge.plazoletaservice.application.http.dto.CreateRestaurantRequest;
import com.backendchallenge.plazoletaservice.domain.model.Restaurant;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)

public interface ICreateRestaurantRequestMapper {
    Restaurant toDomain(CreateRestaurantRequest createRestaurantRequest);

}
