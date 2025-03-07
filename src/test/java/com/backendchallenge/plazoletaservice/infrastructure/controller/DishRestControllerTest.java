package com.backendchallenge.plazoletaservice.infrastructure.controller;

import com.backendchallenge.plazoletaservice.application.http.dto.request.ChangeStatusRequest;
import com.backendchallenge.plazoletaservice.application.http.dto.request.CreateDishRequest;
import com.backendchallenge.plazoletaservice.application.http.dto.request.ListDishesRequest;
import com.backendchallenge.plazoletaservice.application.http.dto.request.UpdateDishRequest;
import com.backendchallenge.plazoletaservice.application.http.dto.response.DishResponse;
import com.backendchallenge.plazoletaservice.application.http.dto.response.PageResponse;
import com.backendchallenge.plazoletaservice.application.http.handler.interfaces.IDishHandler;
import com.backendchallenge.plazoletaservice.domain.until.ConstJwt;
import com.backendchallenge.plazoletaservice.domain.until.ConstRoute;
import com.backendchallenge.plazoletaservice.domain.until.ConstTest;
import com.backendchallenge.plazoletaservice.domain.until.TokenHolder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class DishRestControllerTest {

    private MockMvc mockMvc;
    private IDishHandler dishHandler;
    private MockedStatic<TokenHolder> mockedTokenHolder;

    @BeforeEach
    void setUp() {
        dishHandler = Mockito.mock(IDishHandler.class);
        DishRestController dishRestController = new DishRestController(dishHandler);
        mockMvc = MockMvcBuilders.standaloneSetup(dishRestController).build();
        mockedTokenHolder = mockStatic(TokenHolder.class);
        mockedTokenHolder.when(TokenHolder::getToken).thenReturn(ConstTest.VALID_TOKEN);
    }

    @AfterEach
    void tearDown() {
        mockedTokenHolder.close();
    }

    @Test
    void createDish_shouldReturn201WhenRequestIsValid() throws Exception {
        doNothing().when(dishHandler).createDish(Mockito.any(CreateDishRequest.class));

        mockMvc.perform(post(ConstRoute.DISH + ConstRoute.CREATE_DISH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header(ConstJwt.HEADER_STRING, ConstJwt.BEARER+ConstJwt.SPLITERSTRING + ConstTest.VALID_TOKEN)
                        .content(String.format("""
                                        {
                                            "idRestaurant": %d,
                                            "name": "%s",
                                            "price": %d,
                                            "description": "%s",
                                            "urlImage": "%s",
                                            "categoryName": "%s",
                                            "categoryDescription": "%s"
                                        }
                                        """,
                                ConstTest.ID_TEST,
                                ConstTest.DISH_NAME_TEST,
                                ConstTest.DISH_PRICE_TEST,
                                ConstTest.DISH_DESCRIPTION_TEST,
                                ConstTest.DISH_URL_IMAGE_TEST,
                                ConstTest.CATEGORY_NAME_TEST,
                                ConstTest.CATEGORY_DESCRIPTION_TEST))
                        .param(ConstTest.OWNER_NAME_LABEL, ConstTest.OWNER_ID_TEST))
                .andExpect(status().isCreated());
    }

    @Test
    void updateDish_withValidRequest_shouldReturnStatusCreated() throws Exception {
        UpdateDishRequest request = new UpdateDishRequest();
        request.setDishId(ConstTest.ID_TEST);
        request.setPriceUpdate(ConstTest.DISH_PRICE_TEST);
        request.setDescriptionUpdate(ConstTest.DISH_DESCRIPTION_TEST);

        doNothing().when(dishHandler).updateDish(request);

        mockMvc.perform(put(ConstRoute.DISH + ConstRoute.UPDATE_DISH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(String.format("""
                                        {
                                            "dishId": %d,
                                            "priceUpdate": %d,
                                            "descriptionUpdate": "%s"
                                        }
                                        """,
                                ConstTest.ID_TEST,
                                ConstTest.DISH_PRICE_TEST,
                                ConstTest.DISH_DESCRIPTION_TEST)))
                .andExpect(status().isCreated());
    }
    @Test
    void changeDishStatus_withValidRequest_shouldReturnStatusCreated() throws Exception {
        ChangeStatusRequest request = new ChangeStatusRequest();
        request.setDishId(ConstTest.ID_TEST);
        request.setStatus(ConstTest.DISH_STATUS_TEST);

        doNothing().when(dishHandler).changeDishStatus(request);

        mockMvc.perform(put(ConstRoute.DISH + ConstRoute.CHANGE_DISH_STATUS)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(String.format("""
                                    {
                                        "dishId": %d,
                                        "status": %b
                                    }
                                    """,
                                ConstTest.ID_TEST,
                                ConstTest.DISH_STATUS_TEST)))
                .andExpect(status().isCreated());
    }
    @Test
    void listDishes_shouldReturn200WhenRequestIsValid() throws Exception {
        ListDishesRequest request = new ListDishesRequest();
        request.setIdRestaurant(ConstTest.ID_TEST);
        request.setCurrentPage(ConstTest.CURRENT_PAGE_TEST);
        request.setPageSize(ConstTest.PAGE_SIZE_TEST);
        request.setFilterBy(ConstTest.FILTER_BY_TEST);
        request.setOrderDirection(ConstTest.ORDER_DIRECTION_TEST);

        PageResponse<DishResponse> response = new PageResponse<>();
        when(dishHandler.getDishesByRestaurant(request)).thenReturn(response);

        mockMvc.perform(get(ConstRoute.DISH + ConstRoute.LIST_DISHES)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                            {
                                "idRestaurant": %d,
                                "currentPage": %d,
                                "pageSize": %d,
                                "filterBy": "%s",
                                "orderDirection": "%s"
                            }
                            """.formatted(ConstTest.ID_TEST, ConstTest.CURRENT_PAGE_TEST, ConstTest.PAGE_SIZE_TEST,
                                ConstTest.FILTER_BY_TEST,
                                ConstTest.ORDER_DIRECTION_TEST)))
                .andExpect(status().isOk());
    }
}