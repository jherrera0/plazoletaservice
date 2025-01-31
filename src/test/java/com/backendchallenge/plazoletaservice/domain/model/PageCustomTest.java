package com.backendchallenge.plazoletaservice.domain.model;

import static org.junit.jupiter.api.Assertions.*;

import com.backendchallenge.plazoletaservice.domain.until.ConstTest;
import com.backendchallenge.plazoletaservice.domain.until.ConstValidation;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

class PageCustomTest {

    @Test
    void testPageCustomConstructorAndGetters() {
        List<String> items = Arrays.asList(ConstTest.FIELD1, ConstTest.FIELD2);
        PageCustom<String> pageCustom = new PageCustom<>(ConstTest.CURRENT_PAGE_TEST, ConstTest.PAGE_SIZE_TEST,
                ConstTest.TOTAL_PAGES, items);

        assertEquals(ConstTest.CURRENT_PAGE_TEST, pageCustom.getCurrentPage());
        assertEquals(ConstTest.PAGE_SIZE_TEST, pageCustom.getPageSize());
        assertEquals(ConstTest.TOTAL_PAGES, pageCustom.getTotalPages());
        assertEquals(items, pageCustom.getItems());
    }

    @Test
    void testPageCustomSetters() {
        List<String> items = Arrays.asList(ConstTest.FIELD1, ConstTest.FIELD2);
        PageCustom<String> pageCustom = new PageCustom<>(ConstTest.CURRENT_PAGE_TEST, ConstTest.PAGE_SIZE_TEST,
                ConstTest.TOTAL_PAGES, items);

        pageCustom.setCurrentPage(ConstTest.CURRENT_PAGE_TEST + ConstValidation.TWO);
        pageCustom.setPageSize(ConstTest.PAGE_SIZE_TEST + ConstValidation.TWO);
        pageCustom.setTotalPages(ConstTest.TOTAL_PAGES + ConstValidation.TWO);
        pageCustom.setItems(List.of(ConstTest.FIELD1));

        assertEquals(ConstTest.CURRENT_PAGE_TEST + ConstValidation.TWO, pageCustom.getCurrentPage());
        assertEquals(ConstTest.PAGE_SIZE_TEST + ConstValidation.TWO, pageCustom.getPageSize());
        assertEquals(ConstTest.TOTAL_PAGES + ConstValidation.TWO, pageCustom.getTotalPages());
        assertEquals(List.of(ConstTest.FIELD1), pageCustom.getItems());
    }

}