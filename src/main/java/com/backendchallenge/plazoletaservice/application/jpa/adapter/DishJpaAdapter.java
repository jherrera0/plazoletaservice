package com.backendchallenge.plazoletaservice.application.jpa.adapter;

import com.backendchallenge.plazoletaservice.application.jpa.entity.DishEntity;
import com.backendchallenge.plazoletaservice.application.jpa.entity.RestaurantEntity;
import com.backendchallenge.plazoletaservice.application.jpa.mapper.IDishEntityMapper;
import com.backendchallenge.plazoletaservice.application.jpa.repository.IDishRepository;
import com.backendchallenge.plazoletaservice.application.jpa.repository.IRestaurantRepository;
import com.backendchallenge.plazoletaservice.domain.model.Dish;
import com.backendchallenge.plazoletaservice.domain.spi.IDishPersistencePort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DishJpaAdapter implements IDishPersistencePort {
    private final IDishRepository dishRepository;
    private final IRestaurantRepository restaurantRepository;
    private final IDishEntityMapper dishEntityMapper;
    @Override
    public Boolean createDish(Dish dish) {
        DishEntity dishEntity = dishEntityMapper.toEntity(dish);
        dishEntity.setRestaurant(restaurantRepository.findById(dish.getIdRestaurant()).orElse(new RestaurantEntity()));
        if(dishEntity.getRestaurant().getId() != null) {
            dishRepository.save(dishEntity);
            return true;
        }
        return false;
    }

    @Override
    public boolean findDishById(Long idDish) {
        return dishRepository.existsById(idDish);
    }

    @Override
    public void updateDish(Long idDish, String descriptionUpdate, Integer priceUpdate) {
        DishEntity dishEntity = dishRepository.findById(idDish).orElse(new DishEntity());
        if(dishEntity.getId() != null) {
            dishEntity.setDescription(descriptionUpdate);
            dishEntity.setPrice(priceUpdate);
            dishRepository.save(dishEntity);
        }
    }
}
