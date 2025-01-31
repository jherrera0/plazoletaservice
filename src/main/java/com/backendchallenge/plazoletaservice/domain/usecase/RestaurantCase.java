package com.backendchallenge.plazoletaservice.domain.usecase;

import com.backendchallenge.plazoletaservice.domain.api.IRestaurantServicePort;
import com.backendchallenge.plazoletaservice.domain.exceptions.restaurantexceptions.*;
import com.backendchallenge.plazoletaservice.domain.model.PageCustom;
import com.backendchallenge.plazoletaservice.domain.model.Restaurant;
import com.backendchallenge.plazoletaservice.domain.spi.IUserPersistencePort;
import com.backendchallenge.plazoletaservice.domain.spi.IRestaurantPersistencePort;
import com.backendchallenge.plazoletaservice.domain.until.ConstValidation;

import java.util.Objects;

public class RestaurantCase implements IRestaurantServicePort {
    private final IRestaurantPersistencePort restaurantPersistencePort;
    private final IUserPersistencePort userPersistencePort;

    public RestaurantCase(IRestaurantPersistencePort restaurantPersistencePort, IUserPersistencePort userPersistencePort) {
        this.restaurantPersistencePort = restaurantPersistencePort;
        this.userPersistencePort = userPersistencePort;
    }

    @Override
    public PageCustom<Restaurant> listRestaurants(Integer pageSize, String orderDirection, Integer currentPage) {
        if(pageSize == null || pageSize <= ConstValidation.ZERO){
            throw new RestaurantPageSizeInvalidException();
        }
        if(currentPage == null || currentPage < ConstValidation.ZERO){
            throw new RestaurantCurrentPageInvalidException();
        }
        if(orderDirection == null || orderDirection.isBlank()){
            throw new RestaurantOrderDirectionEmptyException();
        }
        if(!orderDirection.equals(ConstValidation.ASC) && !orderDirection.equals(ConstValidation.DESC)){
            throw new RestaurantOrderDirectionInvalidException();
        }
        PageCustom<Restaurant> result = restaurantPersistencePort.listRestaurants(pageSize, orderDirection,currentPage);
        if(Objects.equals(result.getCurrentPage(), ConstValidation.MINUS_ONE)){
            throw new RestaurantCurrentPageInvalidException();
        }
        return result;
    }

    @Override
    public void createRestaurant(Restaurant restaurant) {
        validatedRestaurantParams(restaurant);
        if (!userPersistencePort.findOwnerById(restaurant.getIdOwner())) {
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
