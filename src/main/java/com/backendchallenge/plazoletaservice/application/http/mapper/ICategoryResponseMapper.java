package com.backendchallenge.plazoletaservice.application.http.mapper;

import com.backendchallenge.plazoletaservice.application.http.dto.response.CategoryResponse;
import com.backendchallenge.plazoletaservice.domain.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)

public interface ICategoryResponseMapper {
    CategoryResponse toResponse(Category category);
}
