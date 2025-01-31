package com.backendchallenge.plazoletaservice.domain.api;

import com.backendchallenge.plazoletaservice.domain.model.Dish;

public interface IDishServicePort {
    void createDish(Dish dish);
    void updateDish(Long idDish, String descriptionUpdate, Integer priceUpdate);
    void changeDishStatus(Long dishId, Boolean status);
}
