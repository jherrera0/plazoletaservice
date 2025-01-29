package com.backendchallenge.plazoletaservice.application.jpa.adapter;

import com.backendchallenge.plazoletaservice.application.feign.IFeignUserClient;
import com.backendchallenge.plazoletaservice.domain.spi.IUserPersistencePort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class IUserJpaAdapter implements IUserPersistencePort {
    private final IFeignUserClient feignUserClient;
    @Override
    public boolean findOwnerById(Long ownerId) {
        return feignUserClient.findOwnerById(ownerId);
    }
}
