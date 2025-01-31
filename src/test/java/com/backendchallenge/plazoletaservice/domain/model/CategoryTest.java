package com.backendchallenge.plazoletaservice.domain.model;

import static org.junit.jupiter.api.Assertions.*;

import com.backendchallenge.plazoletaservice.domain.until.ConstTest;
import org.junit.jupiter.api.Test;

class CategoryTest {

    @Test
    void testCategory() {
        Category category = new Category();
        category.setId(ConstTest.ID_TEST);
        category.setName(ConstTest.CATEGORY_NAME_TEST);
        category.setDescription(ConstTest.CATEGORY_DESCRIPTION_TEST);
        assertEquals(ConstTest.ID_TEST, category.getId());
        assertEquals(ConstTest.CATEGORY_NAME_TEST, category.getName());
        assertEquals(ConstTest.CATEGORY_DESCRIPTION_TEST, category.getDescription());
    }
}