package com.backendchallenge.plazoletaservice.application.http.handler;

import com.backendchallenge.plazoletaservice.application.http.dto.CreateDishRequest;
import com.backendchallenge.plazoletaservice.application.http.dto.UpdateDishRequest;
import com.backendchallenge.plazoletaservice.application.http.mapper.ICreateDishRequestMapper;
import com.backendchallenge.plazoletaservice.domain.api.IDishServicePort;
import com.backendchallenge.plazoletaservice.domain.model.Dish;
import com.backendchallenge.plazoletaservice.domain.until.ConstTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class DishHandlerTest {

    private IDishServicePort dishServicePort;
    private ICreateDishRequestMapper createDishRequestMapper;
    private DishHandler dishHandler;

    @BeforeEach
    void setUp() {
        dishServicePort = mock(IDishServicePort.class);
        createDishRequestMapper = mock(ICreateDishRequestMapper.class);
        dishHandler = new DishHandler(dishServicePort, createDishRequestMapper);
    }

    @Test
    void createDish_Success() {
        CreateDishRequest request = new CreateDishRequest();
        Dish dish = new Dish(ConstTest.ID_TEST, ConstTest.ID_TEST, ConstTest.DISH_NAME_TEST, ConstTest.DISH_PRICE_TEST, ConstTest.DISH_DESCRIPTION_TEST, ConstTest.DISH_URL_IMAGE_TEST, ConstTest.DISH_CATEGORY_TEST);

        when(createDishRequestMapper.toDomain(request)).thenReturn(dish);

        dishHandler.createDish(request);

        verify(dishServicePort, times(1)).createDish(dish);
    }
    @Test
    void updateDish_Success() {
        UpdateDishRequest request = new UpdateDishRequest();
        request.setDishId(ConstTest.ID_TEST);
        request.setDescriptionUpdate(ConstTest.DISH_DESCRIPTION_TEST);
        request.setPriceUpdate(ConstTest.DISH_PRICE_TEST);

        dishHandler.updateDish(request);

        verify(dishServicePort, times(1)).updateDish(ConstTest.ID_TEST, ConstTest.DISH_DESCRIPTION_TEST, ConstTest.DISH_PRICE_TEST);
    }
}