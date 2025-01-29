package com.backendchallenge.plazoletaservice.domain.usecase;

import com.backendchallenge.plazoletaservice.domain.api.IRestaurantServicePort;
import com.backendchallenge.plazoletaservice.domain.exceptions.*;
import com.backendchallenge.plazoletaservice.domain.model.Restaurant;
import com.backendchallenge.plazoletaservice.domain.spi.IUserPersistencePort;
import com.backendchallenge.plazoletaservice.domain.spi.IRestaurantPersistencePort;
import com.backendchallenge.plazoletaservice.domain.until.ConstValidation;

public class RestaurantCase implements IRestaurantServicePort {
    private final IRestaurantPersistencePort restaurantPersistencePort;
    private final IUserPersistencePort feignUserPersistencePort;

    public RestaurantCase(IRestaurantPersistencePort restaurantPersistencePort, IUserPersistencePort feignUserPersistencePort) {
        this.restaurantPersistencePort = restaurantPersistencePort;
        this.feignUserPersistencePort = feignUserPersistencePort;
    }

    @Override
    public void CreateRestaurant(Restaurant restaurant) {
        validatedRestaurantParams(restaurant);
        if (!feignUserPersistencePort.findOwnerById(restaurant.getIdOwner())) {
            throw new OwnerNotFoundException();
        }
        restaurantPersistencePort.createRestaurant(restaurant);
    }

    private static void validatedRestaurantParams(Restaurant restaurant) {
        if (restaurant.getName() == null || restaurant.getName().isBlank()) {
            throw new RestaurantNameEmptyException();
        }
        if(restaurant.getNit() == null || restaurant.getNit().isBlank()){
            throw new RestaurantNitEmptyException();
        }
        if(restaurant.getAddress() == null || restaurant.getAddress().isBlank()){
            throw new RestaurantAddressEmptyException();
        }
        if (restaurant.getPhone() == null || restaurant.getPhone().isBlank()) {
            throw new RestaurantPhoneEmptyException();
        }
        if (restaurant.getUrlLogo() == null || restaurant.getUrlLogo().isBlank()) {
            throw new RestaurantUrlLogoEmptyException();
        }
        if (restaurant.getIdOwner() == null) {
            throw new RestaurantIdOwnerEmptyException();
        }
        nameFormatValidation(restaurant.getName());
        phoneFormatValidation(restaurant.getPhone());
        nitFormatValidation(restaurant.getNit());
    }

    private static void phoneFormatValidation(String phone) {
        if (!phone.matches(ConstValidation.PHONE_REGEX)) {
            throw new InvalidRestaurantPhoneFormatException();
        }
    }

    private static void nitFormatValidation(String nit) {
        if (!nit.matches(ConstValidation.NIT_REGEX)) {
            throw new InvalidRestaurantDocumentFormatException();
        }
    }

    private static void nameFormatValidation(String name) {
        if (!name.matches(ConstValidation.NAME_REGEX)) {
            throw new InvalidRestaurantNameFormatException();
        }
    }

}
