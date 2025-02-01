package com.backendchallenge.plazoletaservice.application.jpa.adapter;

import com.backendchallenge.plazoletaservice.application.jpa.entity.DishEntity;
import com.backendchallenge.plazoletaservice.application.jpa.entity.RestaurantEntity;
import com.backendchallenge.plazoletaservice.application.jpa.mapper.IDishEntityMapper;
import com.backendchallenge.plazoletaservice.application.jpa.repository.IDishRepository;
import com.backendchallenge.plazoletaservice.application.jpa.repository.IRestaurantRepository;
import com.backendchallenge.plazoletaservice.domain.model.Dish;
import com.backendchallenge.plazoletaservice.domain.model.PageCustom;
import com.backendchallenge.plazoletaservice.domain.spi.IDishPersistencePort;
import com.backendchallenge.plazoletaservice.domain.until.ConstValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Arrays;

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

    @Override
    public Long getRestaurantIdByDishId(Long idDish) {
        DishEntity dishEntity = dishRepository.findById(idDish).orElse(new DishEntity());
        if(dishEntity.getId() != null) {
            return dishEntity.getRestaurant().getId();
        }
        return ConstValidation.ZERO.longValue();
    }

    @Override
    public Dish getDishById(Long dishId) {
        return dishEntityMapper.toDomain(dishRepository.findById(dishId).orElse(new DishEntity()));
    }

    @Override
    public void changeDishStatus(Dish dish,Long idOwner) {
        DishEntity dishEntity = dishEntityMapper.toEntity(dish);
        dishEntity.setRestaurant(restaurantRepository.findById(dish.getIdRestaurant()).orElse(new RestaurantEntity()));
        dishRepository.save(dishEntity);
    }

    @Override
    public PageCustom<Dish> getDishesByRestaurant(Long restaurantId, Integer currentPage, Integer pageSize, String filterBy, String orderDirection) {
        Pageable pageable = PageRequest.of(currentPage, pageSize, Sort.by(Sort.Direction.fromString(orderDirection),ConstValidation.NAME));
        Page<DishEntity> dishEntities = dishRepository.findAllDishesByRestaurantAndFilter(restaurantId, Arrays.stream(filterBy.split(ConstValidation.COMMA)).toList() ,pageable);
        if(dishEntities.getTotalPages()>=currentPage) {
            return new PageCustom<>(dishEntities.getNumber(),dishEntities.getSize(), dishEntities.getTotalPages(),dishEntityMapper.toDomainList(dishEntities.getContent()));
        }
        return new PageCustom<>(ConstValidation.MINUS_ONE,null,null,null);
    }
}
