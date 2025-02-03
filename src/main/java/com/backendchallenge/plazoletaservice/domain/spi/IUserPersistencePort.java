package com.backendchallenge.plazoletaservice.domain.spi;

public interface IUserPersistencePort {
    boolean findOwnerById(Long ownerId);
    boolean findEmployeeByIds(Long userId,Long restaurantId);
    void createEmployee(Long userId, Long restaurantId);
}
