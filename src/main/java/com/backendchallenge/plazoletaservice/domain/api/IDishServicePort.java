package com.backendchallenge.plazoletaservice.domain.api;

import com.backendchallenge.plazoletaservice.domain.model.Dish;

public interface IDishServicePort {
    void createDish(Dish dish, Long idOwner);
    void updateDish(Long idDish, String descriptionUpdate, Integer priceUpdate);
}
