package com.backendchallenge.plazoletaservice.infrastructure.controller;

import com.backendchallenge.plazoletaservice.application.http.dto.request.CreateRestaurantRequest;
import com.backendchallenge.plazoletaservice.application.http.dto.request.ListRestaurantsRequest;
import com.backendchallenge.plazoletaservice.application.http.dto.response.PageResponse;
import com.backendchallenge.plazoletaservice.application.http.dto.response.RestaurantCustomResponse;
import com.backendchallenge.plazoletaservice.application.http.handler.interfaces.IRestaurantHandler;
import com.backendchallenge.plazoletaservice.domain.until.ConstRoute;
import com.backendchallenge.plazoletaservice.domain.until.ConstTest;
import com.backendchallenge.plazoletaservice.domain.until.ConstValidation;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class RestaurantRestControllerTest {

    private MockMvc mockMvc;

    @Mock
    private IRestaurantHandler restaurantHandler;

    @InjectMocks
    private RestaurantRestController restaurantRestController;

    private AutoCloseable closeable;
    @BeforeEach
    void setUp() {
      closeable = MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(restaurantRestController).build();
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }


    @Test
    void createRestaurant_withValidRequest_shouldReturnStatusOk() throws Exception {
        CreateRestaurantRequest request = new CreateRestaurantRequest();
        request.setIdOwner(ConstTest.ID_TEST);
        request.setName(ConstTest.NAME_TEST);
        request.setNit(ConstTest.NIT_TEST);
        request.setAddress(ConstTest.ADDRESS_TEST);
        request.setPhone(ConstTest.PHONE_TEST);
        request.setUrlLogo(ConstTest.URL_LOGO_TEST);

        doNothing().when(restaurantHandler).createRestaurant(request);

        mockMvc.perform(post(ConstRoute.RESTAURANT + ConstRoute.CREATE_RESTAURANT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"ownerId\":\"" + ConstTest.ID_TEST + "\", \"name\":\"Valid Name\", \"nit\":\"Valid NIT\", \"address\":\"Valid Address\", \"phone\":\"Valid Phone\", \"urlLogo\":\"http://valid.url/logo.png\"}"))
                .andExpect(status().isOk());
    }

    @Test
    void listRestaurants_withValidRequest_shouldReturnStatusOk() throws Exception {
        ListRestaurantsRequest request = new ListRestaurantsRequest();
        request.setCurrentPage(ConstTest.CURRENT_PAGE_TEST);
        request.setLimitForPage(ConstTest.LIMIT_FOR_PAGE_TEST);
        request.setOrderDirection(ConstValidation.ASC);

        PageResponse<RestaurantCustomResponse> response = new PageResponse<>();
        when(restaurantHandler.listRestaurants(request)).thenReturn(response);

        String requestBody = String.format("{\"currentPage\":%d, \"limitForPage\":%d, \"orderDirection\":\"%s\"}",
                ConstTest.CURRENT_PAGE_TEST, ConstTest.LIMIT_FOR_PAGE_TEST, ConstValidation.ASC);

        mockMvc.perform(get(ConstRoute.RESTAURANT + ConstRoute.LIST_RESTAURANTS)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk());
    }
}
