package com.backendchallenge.plazoletaservice.domain.spi;

public interface IUserPersistencePort {
    boolean findOwnerById(Long ownerId);
}
