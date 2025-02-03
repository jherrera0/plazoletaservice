package com.backendchallenge.plazoletaservice.infrastructure.configuration;

import com.backendchallenge.plazoletaservice.application.feign.IFeignUserClient;
import com.backendchallenge.plazoletaservice.application.jpa.adapter.*;
import com.backendchallenge.plazoletaservice.application.jpa.mapper.ICategoryEntityMapper;
import com.backendchallenge.plazoletaservice.application.jpa.mapper.IDishEntityMapper;
import com.backendchallenge.plazoletaservice.application.jpa.mapper.IOrderEntityMapper;
import com.backendchallenge.plazoletaservice.application.jpa.mapper.IRestaurantEntityMapper;
import com.backendchallenge.plazoletaservice.application.jpa.repository.ICategoryRepository;
import com.backendchallenge.plazoletaservice.application.jpa.repository.IDishRepository;
import com.backendchallenge.plazoletaservice.application.jpa.repository.IOrderRepository;
import com.backendchallenge.plazoletaservice.application.jpa.repository.IRestaurantRepository;
import com.backendchallenge.plazoletaservice.domain.api.IDishServicePort;
import com.backendchallenge.plazoletaservice.domain.api.IOrderServicePort;
import com.backendchallenge.plazoletaservice.domain.api.IRestaurantServicePort;
import com.backendchallenge.plazoletaservice.domain.spi.*;
import com.backendchallenge.plazoletaservice.domain.usecase.DishCase;
import com.backendchallenge.plazoletaservice.domain.usecase.OrderCase;
import com.backendchallenge.plazoletaservice.domain.usecase.RestaurantCase;
import com.backendchallenge.plazoletaservice.application.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {
    private final IFeignUserClient feignUserClient;
    private final IRestaurantEntityMapper restaurantEntityMapper;
    private final IRestaurantRepository restaurantRepository;
    private final IDishEntityMapper dishEntityMapper;
    private final IDishRepository dishRepository;
    private final ICategoryRepository categoryRepository;
    private final ICategoryEntityMapper categoryEntityMapper;
    private final IOrderEntityMapper orderEntityMapper;
    private final IOrderRepository orderRepository;
    private final JwtService jwtService;

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
    public IDishServicePort dishServicePort() {
        return new DishCase(dishPersistencePort(), restaurantPersistencePort(), userPersistencePort(),categoryPersistencePort(), jwtPersistencePort());
    }
    @Bean
    public ICategoryPersistencePort categoryPersistencePort() {
        return new CategoryJpaAdapter(categoryRepository,categoryEntityMapper);
    }

    @Bean
    public IJwtPersistencePort jwtPersistencePort() {
        return new JwtJpaAdapter(jwtService);
    }

    @Bean
    public IDishPersistencePort dishPersistencePort() {
        return new DishJpaAdapter(dishRepository,restaurantRepository,dishEntityMapper);
    }

    @Bean
    public IRestaurantServicePort restaurantServicePort() {
        return new RestaurantCase(restaurantPersistencePort(), userPersistencePort());
    }

    @Bean
    public IOrderServicePort orderServicePort() {
        return new OrderCase(orderPersistencePort(), restaurantPersistencePort(), dishPersistencePort(),
                jwtPersistencePort());
    }

    @Bean
    public IOrderPersistencePort orderPersistencePort() {
        return new OrderJpaAdapter(orderRepository,restaurantRepository,dishRepository, orderEntityMapper);
    }
}
