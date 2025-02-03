package com.backendchallenge.plazoletaservice.domain.model;

import com.backendchallenge.plazoletaservice.domain.until.ConstTest;
import com.backendchallenge.plazoletaservice.domain.until.ConstValidation;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderedDishTest {

    @Test
    void orderedDishInitializationWithAllFields() {
        OrderedDish orderedDish = new OrderedDish(ConstTest.ID_TEST, ConstTest.ID_TEST, ConstValidation.TWO);

        assertEquals(ConstTest.ID_TEST, orderedDish.getId());
        assertEquals(ConstTest.ID_TEST, orderedDish.getIdDish());
        assertEquals(ConstValidation.TWO, orderedDish.getQuantity());
    }

    @Test
    void orderedDishInitializationWithNoFields() {
        OrderedDish orderedDish = new OrderedDish();

        assertNull(orderedDish.getId());
        assertNull(orderedDish.getIdDish());
        assertNull(orderedDish.getQuantity());
    }

    @Test
    void setAndGetId() {
        OrderedDish orderedDish = new OrderedDish();
        orderedDish.setId(ConstTest.ID_TEST);

        assertEquals(ConstTest.ID_TEST, orderedDish.getId());
    }

    @Test
    void setAndGetIdDish() {
        OrderedDish orderedDish = new OrderedDish();
        orderedDish.setIdDish(ConstTest.ID_TEST);

        assertEquals(ConstTest.ID_TEST, orderedDish.getIdDish());
    }

    @Test
    void setAndGetQuantity() {
        OrderedDish orderedDish = new OrderedDish();
        orderedDish.setQuantity(ConstValidation.TWO);

        assertEquals(ConstValidation.TWO, orderedDish.getQuantity());
    }

    @Test
    void setQuantityToNull() {
        OrderedDish orderedDish = new OrderedDish();
        orderedDish.setQuantity(null);

        assertNull(orderedDish.getQuantity());
    }
}