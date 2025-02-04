package com.backendchallenge.plazoletaservice.application.jpa.adapter;

import com.backendchallenge.plazoletaservice.application.feign.IFeignUserClient;
import com.backendchallenge.plazoletaservice.application.jpa.entity.RestaurantWorkersEntity;
import com.backendchallenge.plazoletaservice.application.jpa.repository.IRestaurantsWorkersRepository;
import com.backendchallenge.plazoletaservice.domain.until.ConstTest;
import com.backendchallenge.plazoletaservice.domain.until.ConstValidation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class IUserJpaAdapterTest {

    private IFeignUserClient feignUserClient;
    private IUserJpaAdapter userJpaAdapter;
    private IRestaurantsWorkersRepository restaurantsWorkersRepository;

    @BeforeEach
    void setUp() {
        feignUserClient = mock(IFeignUserClient.class);
        restaurantsWorkersRepository = mock(IRestaurantsWorkersRepository.class);
        userJpaAdapter = new IUserJpaAdapter(feignUserClient, restaurantsWorkersRepository);
    }
    @Test
    void findOwnerById_returnsTrueWhenOwnerExists() {
        when(feignUserClient.findOwnerById(ConstTest.ID_TEST)).thenReturn(true);

        boolean result = userJpaAdapter.findOwnerById(ConstTest.ID_TEST);

        assertTrue(result);
        verify(feignUserClient, times(ConstValidation.ONE)).findOwnerById(ConstTest.ID_TEST);
    }

    @Test
    void findOwnerById_returnsFalseWhenOwnerDoesNotExist() {
        when(feignUserClient.findOwnerById(ConstTest.ID_TEST)).thenReturn(false);

        boolean result = userJpaAdapter.findOwnerById(ConstTest.ID_TEST);

        assertFalse(result);
        verify(feignUserClient, times(ConstValidation.ONE)).findOwnerById(ConstTest.ID_TEST);
    }

    @Test
    void findOwnerById_throwsExceptionWhenFeignClientFails() {
        when(feignUserClient.findOwnerById(ConstTest.ID_TEST)).thenThrow(new RuntimeException("Feign client error"));

        assertThrows(RuntimeException.class, () -> userJpaAdapter.findOwnerById(ConstTest.ID_TEST));
        verify(feignUserClient, times(ConstValidation.ONE)).findOwnerById(ConstTest.ID_TEST);
    }
    @Test
    void findEmployeeByIds_returnsTrueWhenEmployeeExists() {
        when(restaurantsWorkersRepository.existsByEmployedIdAndRestaurantId(ConstTest.ID_TEST, ConstTest.ID_TEST))
                .thenReturn(true);

        boolean result = userJpaAdapter.findEmployeeByIds(ConstTest.ID_TEST, ConstTest.ID_TEST);

        assertTrue(result);
        verify(restaurantsWorkersRepository, times(ConstValidation.ONE)).
                existsByEmployedIdAndRestaurantId(ConstTest.ID_TEST, ConstTest.ID_TEST);
    }

    @Test
    void findEmployeeByIds_returnsFalseWhenEmployeeDoesNotExist() {
        when(restaurantsWorkersRepository.existsByEmployedIdAndRestaurantId(ConstTest.ID_TEST, ConstTest.ID_TEST)).
                thenReturn(false);

        boolean result = userJpaAdapter.findEmployeeByIds(ConstTest.ID_TEST, ConstTest.ID_TEST);

        assertFalse(result);
        verify(restaurantsWorkersRepository, times(ConstValidation.ONE)).
                existsByEmployedIdAndRestaurantId(ConstTest.ID_TEST, ConstTest.ID_TEST);
    }

    @Test
    void createEmployee_savesEmployeeSuccessfully() {
        Long userId = ConstTest.ID_TEST;
        Long restaurantId = ConstTest.ID_TEST;
        RestaurantWorkersEntity restaurantWorkersEntity = new RestaurantWorkersEntity();
        restaurantWorkersEntity.setEmployedId(userId);
        restaurantWorkersEntity.setRestaurantId(restaurantId);

        userJpaAdapter.createEmployee(userId, restaurantId);

        verify(restaurantsWorkersRepository, times(ConstValidation.ONE)).save(any(RestaurantWorkersEntity.class));
    }
    @Test
    void getPhone_returnsPhoneNumberWhenUserExists() {
        when(feignUserClient.getPhoneById(ConstTest.ID_TEST)).thenReturn(ConstTest.PHONE_TEST);

        String phoneNumber = userJpaAdapter.getPhone(ConstTest.ID_TEST);

        assertEquals(ConstTest.PHONE_TEST, phoneNumber);
        verify(feignUserClient, times(ConstValidation.ONE)).getPhoneById(ConstTest.ID_TEST);
    }
}