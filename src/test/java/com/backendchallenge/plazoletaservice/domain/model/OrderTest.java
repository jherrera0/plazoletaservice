package com.backendchallenge.plazoletaservice.domain.model;

import com.backendchallenge.plazoletaservice.domain.until.ConstTest;
import com.backendchallenge.plazoletaservice.domain.until.ConstValidation;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

    @Test
    void orderInitializationWithAllFields() {
        Order order = new Order(ConstTest.ID_TEST, ConstTest.ID_TEST, LocalDate.now(), ConstValidation.PENDING, ConstTest.ID_TEST, ConstTest.ID_TEST, Collections.emptyList());

        assertEquals(ConstTest.ID_TEST, order.getId());
        assertEquals(ConstTest.ID_TEST, order.getIdClient());
        assertEquals(LocalDate.now(), order.getDate());
        assertEquals(ConstValidation.PENDING, order.getStatus());
        assertEquals(ConstTest.ID_TEST, order.getIdEmployee());
        assertEquals(ConstTest.ID_TEST, order.getIdRestaurant());
        assertTrue(order.getDishes().isEmpty());
    }

    @Test
    void orderInitializationWithNoFields() {
        Order order = new Order();

        assertNull(order.getId());
        assertNull(order.getIdClient());
        assertNull(order.getDate());
        assertNull(order.getStatus());
        assertNull(order.getIdEmployee());
        assertNull(order.getIdRestaurant());
        assertNull(order.getDishes());
    }

    @Test
    void setAndGetId() {
        Order order = new Order();
        order.setId(ConstTest.ID_TEST);

        assertEquals(ConstTest.ID_TEST, order.getId());
    }

    @Test
    void setAndGetIdClient() {
        Order order = new Order();
        order.setIdClient(ConstTest.ID_TEST);

        assertEquals(ConstTest.ID_TEST, order.getIdClient());
    }

    @Test
    void setAndGetDate() {
        Order order = new Order();
        LocalDate date = LocalDate.now();
        order.setDate(date);

        assertEquals(date, order.getDate());
    }

    @Test
    void setAndGetStatus() {
        Order order = new Order();
        order.setStatus(ConstValidation.PENDING);

        assertEquals(ConstValidation.PENDING, order.getStatus());
    }

    @Test
    void setAndGetIdEmployee() {
        Order order = new Order();
        order.setIdEmployee(ConstTest.ID_TEST);

        assertEquals(ConstTest.ID_TEST, order.getIdEmployee());
    }

    @Test
    void setAndGetIdRestaurant() {
        Order order = new Order();
        order.setIdRestaurant(ConstTest.ID_TEST);

        assertEquals(ConstTest.ID_TEST, order.getIdRestaurant());
    }

    @Test
    void setAndGetDishes() {
        Order order = new Order();
        order.setDishes(Collections.emptyList());

        assertTrue(order.getDishes().isEmpty());
    }
}