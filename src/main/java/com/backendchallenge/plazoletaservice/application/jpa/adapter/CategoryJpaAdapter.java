package com.backendchallenge.plazoletaservice.application.jpa.adapter;

import com.backendchallenge.plazoletaservice.application.jpa.mapper.ICategoryEntityMapper;
import com.backendchallenge.plazoletaservice.application.jpa.repository.ICategoryRepository;
import com.backendchallenge.plazoletaservice.domain.model.Category;
import com.backendchallenge.plazoletaservice.domain.spi.ICategoryPersistencePort;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class CategoryJpaAdapter implements ICategoryPersistencePort {
    private final ICategoryRepository categoryRepository;
    private final ICategoryEntityMapper categoryEntityMapper;

    @Override
    public List<Category> getCategoriesByNames(List<Category> categories) {
        return categories.stream()
                .map(category -> categoryRepository.findByName(category.getName())
                        .map(categoryEntityMapper::toDomain)
                        .orElseGet(() -> categoryEntityMapper.toDomain(
                                categoryRepository.save(categoryEntityMapper.toEntity(category)))
                        ))
                .toList();
    }
}

