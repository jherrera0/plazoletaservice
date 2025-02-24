package com.backendchallenge.plazoletaservice.domain.model;

import com.backendchallenge.plazoletaservice.domain.until.ConstTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RestaurantTest {
    @Test
    void constructor_setsAllFieldsCorrectly() {
        Restaurant restaurant = new Restaurant(
                ConstTest.ID_TEST,
                ConstTest.NAME_TEST,
                ConstTest.NIT_TEST,
                ConstTest.ADDRESS_TEST,
                ConstTest.PHONE_TEST,
                ConstTest.URL_LOGO_TEST,
                ConstTest.ID_TEST
        );

        assertEquals(ConstTest.ID_TEST, restaurant.getId());
        assertEquals(ConstTest.NAME_TEST, restaurant.getName());
        assertEquals(ConstTest.NIT_TEST, restaurant.getNit());
        assertEquals(ConstTest.ADDRESS_TEST, restaurant.getAddress());
        assertEquals(ConstTest.PHONE_TEST, restaurant.getPhone());
        assertEquals(ConstTest.URL_LOGO_TEST, restaurant.getUrlLogo());
        assertEquals(ConstTest.ID_TEST, restaurant.getIdOwner());
    }

    @Test
    void setName_setsNameCorrectly() {
        Restaurant restaurant = new Restaurant();
        restaurant.setName(ConstTest.NAME_TEST);
        assertEquals(ConstTest.NAME_TEST, restaurant.getName());
    }

    @Test
    void setNit_setsNitCorrectly() {
        Restaurant restaurant = new Restaurant();
        restaurant.setNit(ConstTest.NIT_TEST);
        assertEquals(ConstTest.NIT_TEST, restaurant.getNit());
    }

    @Test
    void setAddress_setsAddressCorrectly() {
        Restaurant restaurant = new Restaurant();
        restaurant.setAddress(ConstTest.ADDRESS_TEST);
        assertEquals(ConstTest.ADDRESS_TEST, restaurant.getAddress());
    }

    @Test
    void setPhone_setsPhoneCorrectly() {
        Restaurant restaurant = new Restaurant();
        restaurant.setPhone(ConstTest.PHONE_TEST);
        assertEquals(ConstTest.PHONE_TEST, restaurant.getPhone());
    }

    @Test
    void setUrlLogo_setsUrlLogoCorrectly() {
        Restaurant restaurant = new Restaurant();
        restaurant.setUrlLogo(ConstTest.URL_LOGO_TEST);
        assertEquals(ConstTest.URL_LOGO_TEST, restaurant.getUrlLogo());
    }

    @Test
    void setIdOwner_setsIdOwnerCorrectly() {
        Restaurant restaurant = new Restaurant();
        restaurant.setIdOwner(ConstTest.ID_TEST);
        assertEquals(ConstTest.ID_TEST, restaurant.getIdOwner());
    }

    @Test
    void getId_returnsIdCorrectly() {
        Restaurant restaurant = new Restaurant();
        restaurant.setId(ConstTest.ID_TEST);
        assertEquals(ConstTest.ID_TEST, restaurant.getId());
    }
}