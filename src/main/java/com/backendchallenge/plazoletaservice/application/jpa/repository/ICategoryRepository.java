package com.backendchallenge.plazoletaservice.application.jpa.repository;

import com.backendchallenge.plazoletaservice.application.jpa.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface ICategoryRepository extends JpaRepository<CategoryEntity, Long> {
    Optional<CategoryEntity> findByName(String name);
}
