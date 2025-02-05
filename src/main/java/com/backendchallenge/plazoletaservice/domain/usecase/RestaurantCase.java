package com.backendchallenge.plazoletaservice.domain.usecase;

import com.backendchallenge.plazoletaservice.domain.api.IRestaurantServicePort;
import com.backendchallenge.plazoletaservice.domain.exceptions.employeeexcepcion.EmployeeAlreadyExist;
import com.backendchallenge.plazoletaservice.domain.exceptions.employeeexcepcion.RestaurantIdEmptyException;
import com.backendchallenge.plazoletaservice.domain.exceptions.employeeexcepcion.RestaurantUserIdEmptyException;
import com.backendchallenge.plazoletaservice.domain.exceptions.restaurantexceptions.*;
import com.backendchallenge.plazoletaservice.domain.model.PageCustom;
import com.backendchallenge.plazoletaservice.domain.model.Restaurant;
import com.backendchallenge.plazoletaservice.domain.spi.IJwtPersistencePort;
import com.backendchallenge.plazoletaservice.domain.spi.IUserPersistencePort;
import com.backendchallenge.plazoletaservice.domain.spi.IRestaurantPersistencePort;
import com.backendchallenge.plazoletaservice.domain.until.ConstJwt;
import com.backendchallenge.plazoletaservice.domain.until.ConstValidation;
import com.backendchallenge.plazoletaservice.domain.until.TokenHolder;

import java.util.Objects;

public class RestaurantCase implements IRestaurantServicePort {
    private final IRestaurantPersistencePort restaurantPersistencePort;
    private final IUserPersistencePort userPersistencePort;
    private final IJwtPersistencePort jwtPersistencePort;

    public RestaurantCase(IRestaurantPersistencePort restaurantPersistencePort, IUserPersistencePort userPersistencePort,
                           IJwtPersistencePort jwtPersistencePort) {
        this.restaurantPersistencePort = restaurantPersistencePort;
        this.userPersistencePort = userPersistencePort;
        this.jwtPersistencePort = jwtPersistencePort;
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
        PageCustom<Restaurant> restaurantPage = restaurantPersistencePort.listRestaurants(pageSize, orderDirection,currentPage);
        if(Objects.equals(restaurantPage.getCurrentPage(), ConstValidation.MINUS_ONE)){
            throw new RestaurantCurrentPageInvalidException();
        }
        return restaurantPage;
    }

    @Override
    public void createRestaurant(Restaurant restaurant) {
        validatedRestaurantParams(restaurant);
        if (!userPersistencePort.findOwnerById(restaurant.getIdOwner())) {
            throw new OwnerNotFoundException();
        }
        restaurantPersistencePort.createRestaurant(restaurant);
    }

    @Override
    public void createEmployee(Long userId, Long restaurantId) {
        String token = TokenHolder.getTokenValue().substring(ConstJwt.LINESTRING_INDEX);
        Long ownerId = jwtPersistencePort.getUserId(token);
        validateEmployeeParams(userId, restaurantId, ownerId);
        userPersistencePort.createEmployee(userId, restaurantId);
    }

    private void validateEmployeeParams(Long userId, Long restaurantId, Long ownerId) {
        if (userId == null || userId <= ConstValidation.ZERO) {
            throw new RestaurantUserIdEmptyException();
        }
        if (restaurantId == null|| restaurantId <= ConstValidation.ZERO) {
            throw new RestaurantIdEmptyException();
        }
        if (userPersistencePort.findEmployeeByIds(userId, restaurantId)) {
            throw new EmployeeAlreadyExist();
        }
        if (!restaurantPersistencePort.existsRestaurantByIdAndOwner(restaurantId, ownerId)) {
            throw new RestaurantNotFoundException();
        }
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
