package com.backendchallenge.plazoletaservice.application.jpa.adapter;

import com.backendchallenge.plazoletaservice.application.jpa.entity.CategoryEntity;
import com.backendchallenge.plazoletaservice.application.jpa.mapper.ICategoryEntityMapper;
import com.backendchallenge.plazoletaservice.application.jpa.repository.ICategoryRepository;
import com.backendchallenge.plazoletaservice.domain.model.Category;
import com.backendchallenge.plazoletaservice.domain.until.ConstTest;
import com.backendchallenge.plazoletaservice.domain.until.ConstValidation;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class CategoryJpaAdapterTest {

    private final ICategoryRepository categoryRepository = mock(ICategoryRepository.class);
    private final ICategoryEntityMapper categoryEntityMapper = mock(ICategoryEntityMapper.class);
    private final CategoryJpaAdapter categoryJpaAdapter = new CategoryJpaAdapter(categoryRepository, categoryEntityMapper);

    @Test
    void testGetCategoriesByNames_foundInRepository() {
        // Arrange
        Category category = new Category(ConstTest.CATEGORY_NAME_TEST, ConstTest.CATEGORY_DESCRIPTION_TEST);
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setName(ConstTest.CATEGORY_NAME_TEST);
        categoryEntity.setDescription(ConstTest.CATEGORY_DESCRIPTION_TEST);

        when(categoryRepository.findByName(ConstTest.CATEGORY_NAME_TEST)).thenReturn(Optional.of(categoryEntity));
        when(categoryEntityMapper.toDomain(categoryEntity)).thenReturn(category);

        // Act
        List<Category> result = categoryJpaAdapter.getCategoriesByNames(List.of(category));

        // Assert
        assertEquals(ConstValidation.ONE, result.size());
        assertEquals(category, result.get(ConstValidation.ZERO));
        verify(categoryRepository, times(ConstValidation.ONE)).findByName(ConstTest.CATEGORY_NAME_TEST);
        verify(categoryEntityMapper, times(ConstValidation.ONE)).toDomain(categoryEntity);
    }

    @Test
    void testGetCategoriesByNames_notFoundInRepository() {
        // Arrange
        Category category = new Category(ConstTest.CATEGORY_NAME_TEST, ConstTest.CATEGORY_DESCRIPTION_TEST);
        CategoryEntity newCategoryEntity = new CategoryEntity();
        newCategoryEntity.setName(ConstTest.CATEGORY_NAME_TEST);
        newCategoryEntity.setDescription(ConstTest.CATEGORY_DESCRIPTION_TEST);

        when(categoryRepository.findByName(ConstTest.CATEGORY_NAME_TEST)).thenReturn(Optional.empty());
        when(categoryEntityMapper.toEntity(category)).thenReturn(newCategoryEntity);
        when(categoryRepository.save(newCategoryEntity)).thenReturn(newCategoryEntity);
        when(categoryEntityMapper.toDomain(newCategoryEntity)).thenReturn(category);

        // Act
        List<Category> result = categoryJpaAdapter.getCategoriesByNames(List.of(category));

        // Assert
        assertEquals(ConstValidation.ONE, result.size());
        assertEquals(category, result.get(ConstValidation.ZERO));
        verify(categoryRepository, times(ConstValidation.ONE)).findByName(ConstTest.CATEGORY_NAME_TEST);
        verify(categoryEntityMapper, times(ConstValidation.ONE)).toEntity(category);
        verify(categoryRepository, times(ConstValidation.ONE)).save(newCategoryEntity);
        verify(categoryEntityMapper, times(ConstValidation.ONE)).toDomain(newCategoryEntity);
    }

}