package com.backendchallenge.plazoletaservice.domain.spi;

import com.backendchallenge.plazoletaservice.domain.model.Category;

import java.util.List;

public interface ICategoryPersistencePort {
    List<Category> getCategoriesByNames(List<Category> categories);
}
