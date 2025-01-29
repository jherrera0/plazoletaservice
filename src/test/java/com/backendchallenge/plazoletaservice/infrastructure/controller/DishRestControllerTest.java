package com.backendchallenge.plazoletaservice.infrastructure.controller;

import com.backendchallenge.plazoletaservice.application.http.dto.CreateDishRequest;
import com.backendchallenge.plazoletaservice.application.http.handler.interfaces.IDishHandler;
import com.backendchallenge.plazoletaservice.domain.until.ConstRoute;
import com.backendchallenge.plazoletaservice.domain.until.ConstTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class DishRestControllerTest {

    private MockMvc mockMvc;
    private IDishHandler dishHandler;

    @BeforeEach
    void setUp() {
        dishHandler = Mockito.mock(IDishHandler.class);
        DishRestController dishRestController = new DishRestController(dishHandler);
        mockMvc = MockMvcBuilders.standaloneSetup(dishRestController).build();
    }

    @Test
    void createDish_shouldReturn201WhenRequestIsValid() throws Exception {
        doNothing().when(dishHandler).createDish(Mockito.any(CreateDishRequest.class), Mockito.anyLong());

        mockMvc.perform(post(ConstRoute.DISH + ConstRoute.CREATE_DISH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(String.format("""
                                {
                                    "idRestaurant": %d,
                                    "name": "%s",
                                    "price": %d,
                                    "description": "%s",
                                    "urlImage": "%s",
                                    "category": "%s"
                                }
                                """,
                                ConstTest.ID_TEST,
                                ConstTest.DISH_NAME_TEST,
                                ConstTest.DISH_PRICE_TEST,
                                ConstTest.DISH_DESCRIPTION_TEST,
                                ConstTest.DISH_URL_IMAGE_TEST,
                                ConstTest.DISH_CATEGORY_TEST))
                        .param(ConstTest.OWNER_NAME_LABEL, ConstTest.OWNER_ID_TEST))
                .andExpect(status().isCreated());
    }
}