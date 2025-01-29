package com.backendchallenge.plazoletaservice.infrastructure.configuration;

import com.backendchallenge.plazoletaservice.application.feign.IFeignUserClient;
import com.backendchallenge.plazoletaservice.application.jpa.adapter.IUserJpaAdapter;
import com.backendchallenge.plazoletaservice.application.jpa.adapter.RestaurantJpaAdapter;
import com.backendchallenge.plazoletaservice.application.jpa.mapper.IRestaurantEntityMapper;
import com.backendchallenge.plazoletaservice.application.jpa.repository.IRestaurantRepository;
import com.backendchallenge.plazoletaservice.domain.api.IRestaurantServicePort;
import com.backendchallenge.plazoletaservice.domain.spi.IRestaurantPersistencePort;
import com.backendchallenge.plazoletaservice.domain.spi.IUserPersistencePort;
import com.backendchallenge.plazoletaservice.domain.usecase.RestaurantCase;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {
    private final IFeignUserClient feignUserClient;
    private final IRestaurantEntityMapper restaurantEntityMapper;
    private final IRestaurantRepository restaurantRepository;

    @Bean
    public IUserPersistencePort userPersistencePort() {
        return new IUserJpaAdapter(feignUserClient);
    }

    @Bean
    public IRestaurantPersistencePort restaurantPersistencePort() {
        return new RestaurantJpaAdapter(restaurantEntityMapper,
                restaurantRepository);
    }

    @Bean
    public IRestaurantServicePort restaurantServicePort() {
        return new RestaurantCase(restaurantPersistencePort(), userPersistencePort());
    }
}
