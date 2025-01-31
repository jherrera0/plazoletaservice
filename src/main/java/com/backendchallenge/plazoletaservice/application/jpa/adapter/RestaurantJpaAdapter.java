package com.backendchallenge.plazoletaservice.application.jpa.adapter;

import com.backendchallenge.plazoletaservice.application.jpa.entity.RestaurantEntity;
import com.backendchallenge.plazoletaservice.application.jpa.mapper.IRestaurantEntityMapper;
import com.backendchallenge.plazoletaservice.application.jpa.repository.IRestaurantRepository;
import com.backendchallenge.plazoletaservice.domain.model.PageCustom;
import com.backendchallenge.plazoletaservice.domain.model.Restaurant;
import com.backendchallenge.plazoletaservice.domain.spi.IRestaurantPersistencePort;
import com.backendchallenge.plazoletaservice.domain.until.ConstValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;


@RequiredArgsConstructor
public class RestaurantJpaAdapter implements IRestaurantPersistencePort {
    private final IRestaurantEntityMapper restaurantEntityMapper;
    private final IRestaurantRepository restaurantRepository;
    @Override
    public void createRestaurant(Restaurant restaurant) {
        RestaurantEntity restaurantEntity = restaurantEntityMapper.toEntity(restaurant);
        restaurantRepository.save(restaurantEntity);
    }

    @Override
    public boolean existsRestaurantByIdAndOwner(Long idRestaurant, Long idOwner) {
        return restaurantRepository.existsByIdAndIdOwner(idRestaurant, idOwner);
    }

    @Override
    public PageCustom<Restaurant> listRestaurants(Integer pageSize, String sortDirection, Integer currentPage) {
        Pageable pageable = PageRequest.of(currentPage, pageSize, Sort.by(Sort.Direction.fromString(sortDirection), ConstValidation.NAME));
        Page<RestaurantEntity> restaurantEntityPage = restaurantRepository.findAll(pageable);
        if(restaurantEntityPage.getTotalPages()>=restaurantEntityPage.getNumber()){
            return new PageCustom<>(restaurantEntityPage.getNumber(),restaurantEntityPage.getSize(),restaurantEntityPage.getTotalPages(),restaurantEntityMapper.toModelList(restaurantEntityPage.getContent()));
        }
        return new PageCustom<>(ConstValidation.MINUS_ONE,null,null,null);
    }
}
