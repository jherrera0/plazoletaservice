package com.backendchallenge.plazoletaservice.application.jpa.entity;

import com.backendchallenge.plazoletaservice.domain.until.ConstTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RestaurantsWorkerIdTest {

    @Test
    void equals_returnsTrueForSameObject() {
        RestaurantsWorkerId id = new RestaurantsWorkerId(ConstTest.ID_TEST, ConstTest.ID_TEST);
        assertEquals(id, id);
    }

    @Test
    void equals_returnsTrueForEqualObjects() {
        RestaurantsWorkerId id1 = new RestaurantsWorkerId(ConstTest.ID_TEST, ConstTest.ID_TEST);
        RestaurantsWorkerId id2 = new RestaurantsWorkerId(ConstTest.ID_TEST, ConstTest.ID_TEST);
        assertEquals(id1, id2);
    }

    @Test
    void equals_returnsFalseForDifferentObjects() {
        RestaurantsWorkerId id1 = new RestaurantsWorkerId(ConstTest.ID_TEST, ConstTest.ID_TEST);
        RestaurantsWorkerId id2 = new RestaurantsWorkerId(2L, 2L);
        assertNotEquals(id1, id2);
    }

    @Test
    void equals_returnsFalseForNull() {
        RestaurantsWorkerId id = new RestaurantsWorkerId(ConstTest.ID_TEST, ConstTest.ID_TEST);
        assertNotEquals(null, id);
    }

    @Test
    void equals_returnsFalseForDifferentClass() {
        RestaurantsWorkerId id = new RestaurantsWorkerId(ConstTest.ID_TEST, ConstTest.ID_TEST);
        Object obj = new Object();
        assertNotEquals(id, obj);
    }

    @Test
    void hashCode_returnsSameHashCodeForEqualObjects() {
        RestaurantsWorkerId id1 = new RestaurantsWorkerId(ConstTest.ID_TEST, ConstTest.ID_TEST);
        RestaurantsWorkerId id2 = new RestaurantsWorkerId(ConstTest.ID_TEST, ConstTest.ID_TEST);
        assertEquals(id1.hashCode(), id2.hashCode());
    }

    @Test
    void hashCode_returnsDifferentHashCodeForDifferentObjects() {
        RestaurantsWorkerId id1 = new RestaurantsWorkerId(ConstTest.ID_TEST, ConstTest.ID_TEST);
        RestaurantsWorkerId id2 = new RestaurantsWorkerId(ConstTest.ID_2_TEST, ConstTest.ID_2_TEST);
        assertNotEquals(id1.hashCode(), id2.hashCode());
    }
}