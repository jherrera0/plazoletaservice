package com.backendchallenge.plazoletaservice.domain.usecase;

import com.backendchallenge.plazoletaservice.domain.exceptions.restaurantexceptions.*;
import com.backendchallenge.plazoletaservice.domain.model.PageCustom;
import com.backendchallenge.plazoletaservice.domain.model.Restaurant;
import com.backendchallenge.plazoletaservice.domain.spi.IRestaurantPersistencePort;
import com.backendchallenge.plazoletaservice.domain.spi.IUserPersistencePort;
import com.backendchallenge.plazoletaservice.domain.until.ConstTest;
import com.backendchallenge.plazoletaservice.domain.until.ConstValidation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class RestaurantCaseTest {

    private IRestaurantPersistencePort restaurantPersistencePort;
    private IUserPersistencePort userPersistencePort;
    private RestaurantCase restaurantCase;

    @BeforeEach
    void setUp() {
        restaurantPersistencePort = mock(IRestaurantPersistencePort.class);
        userPersistencePort = mock(IUserPersistencePort.class);
        restaurantCase = new RestaurantCase(restaurantPersistencePort, userPersistencePort);
    }

    @Test
    void createRestaurant_createsRestaurantSuccessfully() {
        Restaurant restaurant = new Restaurant(
                ConstTest.ID_TEST,
                ConstTest.NAME_TEST,
                ConstTest.NIT_TEST,
                ConstTest.ADDRESS_TEST,
                ConstTest.PHONE_TEST,
                ConstTest.URL_LOGO_TEST,
                ConstTest.ID_TEST
        );

        when(userPersistencePort.findOwnerById(ConstTest.ID_TEST)).thenReturn(true);

        restaurantCase.createRestaurant(restaurant);

        verify(restaurantPersistencePort, times(1)).createRestaurant(restaurant);
    }

    @Test
    void createRestaurant_throwsOwnerNotFoundException() {
        Restaurant restaurant = new Restaurant(
                ConstTest.ID_TEST,
                ConstTest.NAME_TEST,
                ConstTest.NIT_TEST,
                ConstTest.ADDRESS_TEST,
                ConstTest.PHONE_TEST,
                ConstTest.URL_LOGO_TEST,
                ConstTest.ID_TEST
        );

        when(userPersistencePort.findOwnerById(ConstTest.ID_TEST)).thenReturn(false);

        assertThrows(OwnerNotFoundException.class, () -> restaurantCase.createRestaurant(restaurant));
    }

    @Test
    void createRestaurant_throwsRestaurantNameEmptyException() {
        Restaurant restaurant = new Restaurant(
                ConstTest.ID_TEST,
                ConstTest.EMPTY_TEST,
                ConstTest.NIT_TEST,
                ConstTest.ADDRESS_TEST,
                ConstTest.PHONE_TEST,
                ConstTest.URL_LOGO_TEST,
                ConstTest.ID_TEST
        );

        assertThrows(RestaurantNameEmptyException.class, () -> restaurantCase.createRestaurant(restaurant));
    }

    @Test
    void createRestaurant_throwsRestaurantNitEmptyException() {
        Restaurant restaurant = new Restaurant(
                ConstTest.ID_TEST,
                ConstTest.NAME_TEST,
                ConstTest.EMPTY_TEST,
                ConstTest.ADDRESS_TEST,
                ConstTest.PHONE_TEST,
                ConstTest.URL_LOGO_TEST,
                ConstTest.ID_TEST
        );

        assertThrows(RestaurantNitEmptyException.class, () -> restaurantCase.createRestaurant(restaurant));
    }

    @Test
    void createRestaurant_throwsRestaurantAddressEmptyException() {
        Restaurant restaurant = new Restaurant(
                ConstTest.ID_TEST,
                ConstTest.NAME_TEST,
                ConstTest.NIT_TEST,
                ConstTest.EMPTY_TEST,
                ConstTest.PHONE_TEST,
                ConstTest.URL_LOGO_TEST,
                ConstTest.ID_TEST
        );

        assertThrows(RestaurantAddressEmptyException.class, () -> restaurantCase.createRestaurant(restaurant));
    }

    @Test
    void createRestaurant_throwsRestaurantPhoneEmptyException() {
        Restaurant restaurant = new Restaurant(
                ConstTest.ID_TEST,
                ConstTest.NAME_TEST,
                ConstTest.NIT_TEST,
                ConstTest.ADDRESS_TEST,
                ConstTest.EMPTY_TEST,
                ConstTest.URL_LOGO_TEST,
                ConstTest.ID_TEST
        );

        assertThrows(RestaurantPhoneEmptyException.class, () -> restaurantCase.createRestaurant(restaurant));
    }

    @Test
    void createRestaurant_throwsRestaurantUrlLogoEmptyException() {
        Restaurant restaurant = new Restaurant(
                ConstTest.ID_TEST,
                ConstTest.NAME_TEST,
                ConstTest.NIT_TEST,
                ConstTest.ADDRESS_TEST,
                ConstTest.PHONE_TEST,
                ConstTest.EMPTY_TEST,
                ConstTest.ID_TEST
        );

        assertThrows(RestaurantUrlLogoEmptyException.class, () -> restaurantCase.createRestaurant(restaurant));
    }

    @Test
    void createRestaurant_throwsRestaurantIdOwnerEmptyException() {
        Restaurant restaurant = new Restaurant(
                ConstTest.ID_TEST,
                ConstTest.NAME_TEST,
                ConstTest.NIT_TEST,
                ConstTest.ADDRESS_TEST,
                ConstTest.PHONE_TEST,
                ConstTest.URL_LOGO_TEST,
                null
        );

        assertThrows(RestaurantIdOwnerEmptyException.class, () -> restaurantCase.createRestaurant(restaurant));
    }

    @Test
    void createRestaurant_throwsInvalidRestaurantNameFormatException() {
        Restaurant restaurant = new Restaurant(
                ConstTest.ID_TEST,
                ConstTest.INVALID_NAME_TEST,
                ConstTest.NIT_TEST,
                ConstTest.ADDRESS_TEST,
                ConstTest.PHONE_TEST,
                ConstTest.URL_LOGO_TEST,
                ConstTest.ID_TEST
        );

        assertThrows(InvalidRestaurantNameFormatException.class, () -> restaurantCase.createRestaurant(restaurant));
    }

    @Test
    void createRestaurant_throwsInvalidRestaurantPhoneFormatException() {
        Restaurant restaurant = new Restaurant(
                ConstTest.ID_TEST,
                ConstTest.NAME_TEST,
                ConstTest.NIT_TEST,
                ConstTest.ADDRESS_TEST,
                ConstTest.INVALID_PHONE_TEST,
                ConstTest.URL_LOGO_TEST,
                ConstTest.ID_TEST
        );

        assertThrows(InvalidRestaurantPhoneFormatException.class, () -> restaurantCase.createRestaurant(restaurant));
    }

    @Test
    void createRestaurant_throwsInvalidRestaurantDocumentFormatException() {
        Restaurant restaurant = new Restaurant(
                ConstTest.ID_TEST,
                ConstTest.NAME_TEST,
                ConstTest.INVALID_NIT_TEST,
                ConstTest.ADDRESS_TEST,
                ConstTest.PHONE_TEST,
                ConstTest.URL_LOGO_TEST,
                ConstTest.ID_TEST
        );

        assertThrows(InvalidRestaurantDocumentFormatException.class, () -> restaurantCase.createRestaurant(restaurant));
    }
    @Test
    void listRestaurants_withValidRequest_shouldReturnPageCustom() {
        int currentPage = ConstTest.CURRENT_PAGE_TEST;
        int pageSize = ConstTest.PAGE_SIZE_TEST;
        String sortDirection = ConstValidation.ASC;
        PageCustom<Restaurant> pageCustom = new PageCustom<>();
        when(restaurantPersistencePort.listRestaurants(pageSize, sortDirection, currentPage)).thenReturn(pageCustom);

        PageCustom<Restaurant> result = restaurantCase.listRestaurants(pageSize, sortDirection, currentPage);

        assertEquals(pageCustom, result);
    }

    @Test
    void listRestaurants_withInvalidPageSize_shouldThrowException() {
        int currentPage = ConstTest.CURRENT_PAGE_TEST;
        int pageSize = ConstTest.INVALID_PAGE_SIZE_TEST;
        String sortDirection = ConstValidation.ASC;

        assertThrows(RestaurantPageSizeInvalidException.class, () -> restaurantCase.listRestaurants(pageSize, sortDirection, currentPage));
    }

    @Test
    void listRestaurants_withInvalidCurrentPage_shouldThrowException() {
        int currentPage = ConstTest.INVALID_CURRENT_PAGE_TEST;
        int pageSize = ConstTest.PAGE_SIZE_TEST;
        String sortDirection = ConstValidation.ASC;

        assertThrows(RestaurantCurrentPageInvalidException.class, ()
                -> restaurantCase.listRestaurants(pageSize, sortDirection, currentPage));
    }

    @Test
    void listRestaurants_withEmptyOrderDirection_shouldThrowException() {
        int currentPage = ConstTest.CURRENT_PAGE_TEST;
        int pageSize = ConstTest.PAGE_SIZE_TEST;
        String sortDirection = ConstValidation.EMPTY;

        assertThrows(RestaurantOrderDirectionEmptyException.class, ()
                -> restaurantCase.listRestaurants(pageSize, sortDirection, currentPage));
    }

    @Test
    void listRestaurants_withInvalidOrderDirection_shouldThrowException() {
        int currentPage = ConstTest.CURRENT_PAGE_TEST;
        int pageSize = ConstTest.PAGE_SIZE_TEST;
        String sortDirection = ConstTest.INVALID_NAME_TEST;

        assertThrows(RestaurantOrderDirectionInvalidException.class, ()
                -> restaurantCase.listRestaurants(pageSize, sortDirection, currentPage));
    }

    @Test
    void listRestaurants_withOverMaxCurrentPage_shouldThrowException() {
        int currentPage = ConstTest.PAGE_SIZE_TEST_EXCEEDED;
        int pageSize = ConstTest.PAGE_SIZE_TEST;
        String sortDirection = ConstValidation.ASC;
        PageCustom<Restaurant> pageCustom = new PageCustom<>();
        pageCustom.setCurrentPage(-1);

        when(restaurantPersistencePort.listRestaurants(pageSize, sortDirection, currentPage)).thenReturn(pageCustom);

        assertThrows(RestaurantCurrentPageInvalidException.class, ()
                -> restaurantCase.listRestaurants(pageSize, sortDirection, currentPage));
    }
}