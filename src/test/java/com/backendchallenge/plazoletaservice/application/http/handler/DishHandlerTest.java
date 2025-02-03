package com.backendchallenge.plazoletaservice.application.http.handler;

import com.backendchallenge.plazoletaservice.application.http.dto.request.ChangeStatusRequest;
import com.backendchallenge.plazoletaservice.application.http.dto.request.CreateDishRequest;
import com.backendchallenge.plazoletaservice.application.http.dto.request.ListDishesRequest;
import com.backendchallenge.plazoletaservice.application.http.dto.request.UpdateDishRequest;
import com.backendchallenge.plazoletaservice.application.http.dto.response.DishResponse;
import com.backendchallenge.plazoletaservice.application.http.dto.response.PageResponse;
import com.backendchallenge.plazoletaservice.application.http.mapper.ICreateDishRequestMapper;
import com.backendchallenge.plazoletaservice.application.http.mapper.IPageResponseMapper;
import com.backendchallenge.plazoletaservice.domain.api.IDishServicePort;
import com.backendchallenge.plazoletaservice.domain.model.Category;
import com.backendchallenge.plazoletaservice.domain.model.Dish;
import com.backendchallenge.plazoletaservice.domain.until.ConstTest;
import com.backendchallenge.plazoletaservice.domain.until.ConstValidation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class DishHandlerTest {

    private IDishServicePort dishServicePort;
    private ICreateDishRequestMapper createDishRequestMapper;
    private DishHandler dishHandler;
    private IPageResponseMapper pageResponseMapper;

    @BeforeEach
    void setUp() {
        dishServicePort = mock(IDishServicePort.class);
        createDishRequestMapper = mock(ICreateDishRequestMapper.class);
        pageResponseMapper = mock(IPageResponseMapper.class);
        dishHandler = new DishHandler(dishServicePort, createDishRequestMapper, pageResponseMapper);
    }

    @Test
    void createDish_Success() {
        CreateDishRequest request = new CreateDishRequest();
        Dish dish = new Dish(ConstTest.ID_TEST, ConstTest.ID_TEST,
                ConstTest.DISH_NAME_TEST, ConstTest.DISH_PRICE_TEST,
                ConstTest.DISH_DESCRIPTION_TEST, ConstTest.DISH_URL_IMAGE_TEST,
                List.of(new Category(ConstTest.ID_TEST,ConstTest.CATEGORY_NAME_TEST, ConstTest.CATEGORY_DESCRIPTION_TEST)));

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
    @Test
    void changeDishStatus_withValidRequest_shouldInvokeServicePort() {
        ChangeStatusRequest request = new ChangeStatusRequest();
        request.setDishId(ConstTest.ID_TEST);
        request.setStatus(ConstTest.DISH_STATUS_TEST);

        dishHandler.changeDishStatus(request);

        verify(dishServicePort, times(1)).changeDishStatus(ConstTest.ID_TEST, ConstTest.DISH_STATUS_TEST);
    }

    @Test
    void getDishesByRestaurant_Success() {
        ListDishesRequest request = new ListDishesRequest(
                ConstTest.ID_TEST,
                ConstTest.CURRENT_PAGE_TEST,
                ConstTest.PAGE_SIZE_TEST, ConstTest.FILTER_BY_TEST,
                ConstTest.ORDER_DIRECTION_TEST);
        PageResponse<DishResponse> pageResponse = new PageResponse<>();

        when(dishServicePort.getDishesByRestaurant(ConstTest.ID_TEST, ConstTest.CURRENT_PAGE_TEST,
                ConstTest.PAGE_SIZE_TEST,
                ConstTest.FILTER_BY_TEST,
                ConstTest.ORDER_DIRECTION_TEST
        )).thenReturn(null);
        when(pageResponseMapper.toPageResponseOfDishResponse(null)).thenReturn(pageResponse);

        PageResponse<DishResponse> result = dishHandler.getDishesByRestaurant(request);

        verify(dishServicePort, times(ConstValidation.ONE))
                .getDishesByRestaurant(ConstTest.ID_TEST, ConstTest.CURRENT_PAGE_TEST,
                        ConstTest.PAGE_SIZE_TEST,
                        ConstTest.FILTER_BY_TEST,
                        ConstTest.ORDER_DIRECTION_TEST);
        verify(pageResponseMapper, times(ConstValidation.ONE)).toPageResponseOfDishResponse(null);
        assertEquals(pageResponse, result);
    }
}