package com.backendchallenge.plazoletaservice.application.jpa.mapper;

import com.backendchallenge.plazoletaservice.application.jpa.entity.CategoryEntity;
import com.backendchallenge.plazoletaservice.domain.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)

public interface ICategoryEntityMapper {
    @Mapping(target = "id", ignore = true)
    CategoryEntity toEntity(Category category);
    Category toDomain(CategoryEntity categoryEntity);
}
