package com.backendchallenge.plazoletaservice.domain.api;

import com.backendchallenge.plazoletaservice.domain.model.Dish;
import com.backendchallenge.plazoletaservice.domain.model.PageCustom;

public interface IDishServicePort {
    void createDish(Dish dish);
    void updateDish(Long idDish, String descriptionUpdate, Integer priceUpdate);
    void changeDishStatus(Long dishId, Boolean status);
    PageCustom<Dish> getDishesByRestaurant(Long restaurantId, Integer currentPage, Integer pageSize, String filterBy,String orderDirection);
}
