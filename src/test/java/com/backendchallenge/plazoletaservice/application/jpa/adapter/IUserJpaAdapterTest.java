package com.backendchallenge.plazoletaservice.application.jpa.adapter;

import com.backendchallenge.plazoletaservice.application.feign.IFeignUserClient;
import com.backendchallenge.plazoletaservice.domain.until.ConstTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class IUserJpaAdapterTest {

    private IFeignUserClient feignUserClient;
    private IUserJpaAdapter userJpaAdapter;

    @BeforeEach
    void setUp() {
        feignUserClient = mock(IFeignUserClient.class);
        userJpaAdapter = new IUserJpaAdapter(feignUserClient);
    }

    @Test
    void findOwnerById_returnsTrueWhenOwnerExists() {
        when(feignUserClient.findOwnerById(ConstTest.ID_TEST)).thenReturn(true);

        boolean result = userJpaAdapter.findOwnerById(ConstTest.ID_TEST);

        assertTrue(result);
        verify(feignUserClient, times(1)).findOwnerById(ConstTest.ID_TEST);
    }

    @Test
    void findOwnerById_returnsFalseWhenOwnerDoesNotExist() {
        when(feignUserClient.findOwnerById(ConstTest.ID_TEST)).thenReturn(false);

        boolean result = userJpaAdapter.findOwnerById(ConstTest.ID_TEST);

        assertFalse(result);
        verify(feignUserClient, times(1)).findOwnerById(ConstTest.ID_TEST);
    }

    @Test
    void findOwnerById_throwsExceptionWhenFeignClientFails() {
        when(feignUserClient.findOwnerById(ConstTest.ID_TEST)).thenThrow(new RuntimeException("Feign client error"));

        assertThrows(RuntimeException.class, () -> userJpaAdapter.findOwnerById(ConstTest.ID_TEST));
        verify(feignUserClient, times(1)).findOwnerById(ConstTest.ID_TEST);
    }
}