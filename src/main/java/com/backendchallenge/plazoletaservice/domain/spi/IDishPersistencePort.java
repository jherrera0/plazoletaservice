package com.backendchallenge.plazoletaservice.domain.spi;

import com.backendchallenge.plazoletaservice.domain.model.Dish;

public interface IDishPersistencePort {
    Boolean createDish(Dish dish);
    boolean findDishById(Long idDish);

    void updateDish(Long idDish, String descriptionUpdate, Integer priceUpdate);

    Long getRestaurantIdByDishId(Long idDish);
}
