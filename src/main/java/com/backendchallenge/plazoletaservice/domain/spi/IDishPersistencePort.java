package com.backendchallenge.plazoletaservice.domain.spi;

import com.backendchallenge.plazoletaservice.domain.model.Dish;
import com.backendchallenge.plazoletaservice.domain.model.PageCustom;

public interface IDishPersistencePort {
    Boolean createDish(Dish dish);
    boolean findDishById(Long idDish);
    void updateDish(Long idDish, String descriptionUpdate, Integer priceUpdate);
    Long getRestaurantIdByDishId(Long idDish);
    Dish getDishById(Long dishId);
    void changeDishStatus(Dish dish, Long idOwner);
    PageCustom<Dish> getDishesByRestaurant(Long restaurantId, Integer currentPage, Integer pageSize, String filterBy, String orderDirection);

}
