package com.backendchallenge.plazoletaservice.application.http.mapper;

import com.backendchallenge.plazoletaservice.domain.model.Category;
import com.backendchallenge.plazoletaservice.application.http.dto.request.CreateDishRequest;
import com.backendchallenge.plazoletaservice.domain.model.Dish;
import com.backendchallenge.plazoletaservice.domain.until.ConstValidation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Mapper(componentModel = "spring")
public interface ICreateDishRequestMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "idRestaurant", source = "createDishRequest.idRestaurant")
    @Mapping(target = "categories", source = "createDishRequest", qualifiedByName = "mapCategories")
    @Mapping(target = "available", constant = "true")
    Dish toDomain(CreateDishRequest createDishRequest);

    @Named("mapCategories")
    default List<Category> mapCategories(CreateDishRequest createDishRequest) {
        String[] names = createDishRequest.getCategoryName().split(ConstValidation.COMMA);
        String[] descriptions = createDishRequest.getCategoryDescription().split(ConstValidation.COMMA);

        List<Category> categories = new ArrayList<>();
        int minLength = Math.min(names.length, descriptions.length);

        IntStream.range(ConstValidation.ZERO, minLength)
                .forEach(i -> categories.add(new Category(names[i].trim(), descriptions[i].trim())));

        return categories;
    }
}
