package com.backendchallenge.plazoletaservice.application.jpa.adapter;

import com.backendchallenge.plazoletaservice.application.feign.IFeignUserClient;
import com.backendchallenge.plazoletaservice.application.jpa.entity.RestaurantWorkersEntity;
import com.backendchallenge.plazoletaservice.application.jpa.repository.IRestaurantsWorkersRepository;
import com.backendchallenge.plazoletaservice.domain.spi.IUserPersistencePort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class IUserJpaAdapter implements IUserPersistencePort {
    private final IFeignUserClient feignUserClient;
    private final IRestaurantsWorkersRepository restaurantsWorkersRepository;
    @Override
    public boolean findOwnerById(Long ownerId) {
        return feignUserClient.findOwnerById(ownerId);
    }

    @Override
    public boolean findEmployeeByIds(Long userId, Long restaurantId) {
        return restaurantsWorkersRepository.existsByEmployedIdAndRestaurantId(userId, restaurantId);
    }

    @Override
    public void createEmployee(Long userId, Long restaurantId) {
        RestaurantWorkersEntity restaurantWorkersEntity = new RestaurantWorkersEntity();
        restaurantWorkersEntity.setEmployedId(userId);
        restaurantWorkersEntity.setRestaurantId(restaurantId);
        restaurantsWorkersRepository.save(restaurantWorkersEntity);
    }

    @Override
    public String getPhone(Long userId) {
        return feignUserClient.getPhoneById(userId);
    }
}
