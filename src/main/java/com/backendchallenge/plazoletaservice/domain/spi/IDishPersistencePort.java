package com.backendchallenge.plazoletaservice.domain.spi;

import com.backendchallenge.plazoletaservice.domain.model.Dish;

public interface IDishPersistencePort {
    Boolean createDish(Dish dish);
}
