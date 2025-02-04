package com.backendchallenge.plazoletaservice.infrastructure.configuration;

import com.backendchallenge.plazoletaservice.application.feign.IFeignUserClient;
import com.backendchallenge.plazoletaservice.application.feign.INotificationFeignClient;
import com.backendchallenge.plazoletaservice.application.jpa.adapter.*;
import com.backendchallenge.plazoletaservice.application.jpa.mapper.*;
import com.backendchallenge.plazoletaservice.application.jpa.repository.*;
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
    private final IOrderedDishRepository orderedDishRepository;
    private final JwtService jwtService;
    private final IRestaurantsWorkersRepository restaurantsWorkersRepository;
    private final IOrderedDishEntityMapper orderedDishEntityMapper;
    private final INotificationFeignClient notificationFeignClient;

    @Bean
    public IUserPersistencePort userPersistencePort() {
        return new IUserJpaAdapter(feignUserClient, restaurantsWorkersRepository);
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
        return new RestaurantCase(restaurantPersistencePort(), userPersistencePort(), jwtPersistencePort());
    }

    @Bean
    INotificationPersistencePort notificationPersistencePort() {
        return new NotificationJpaAdapter(notificationFeignClient);
    }

    @Bean
    public IOrderServicePort orderServicePort() {
        return new OrderCase(orderPersistencePort(), restaurantPersistencePort(), dishPersistencePort(),
                jwtPersistencePort(),userPersistencePort(),notificationPersistencePort());
    }

    @Bean
    public IOrderPersistencePort orderPersistencePort() {
        return new OrderJpaAdapter(orderRepository,restaurantRepository,orderEntityMapper,dishRepository, orderedDishRepository,orderedDishEntityMapper);
    }
}
