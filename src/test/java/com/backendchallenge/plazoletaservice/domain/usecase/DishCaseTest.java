package com.backendchallenge.plazoletaservice.domain.usecase;

import com.backendchallenge.plazoletaservice.domain.api.IDishServicePort;
import com.backendchallenge.plazoletaservice.domain.exceptions.dishexceptions.*;
import com.backendchallenge.plazoletaservice.domain.exceptions.restaurantexceptions.OwnerNotFoundException;
import com.backendchallenge.plazoletaservice.domain.exceptions.restaurantexceptions.RestaurantNotFoundException;
import com.backendchallenge.plazoletaservice.domain.model.Category;
import com.backendchallenge.plazoletaservice.domain.model.Dish;
import com.backendchallenge.plazoletaservice.domain.model.PageCustom;
import com.backendchallenge.plazoletaservice.domain.spi.ICategoryPersistencePort;
import com.backendchallenge.plazoletaservice.domain.spi.IDishPersistencePort;
import com.backendchallenge.plazoletaservice.domain.spi.IJwtPersistencePort;
import com.backendchallenge.plazoletaservice.domain.spi.IRestaurantPersistencePort;
import com.backendchallenge.plazoletaservice.domain.spi.IUserPersistencePort;
import com.backendchallenge.plazoletaservice.domain.until.ConstTest;
import com.backendchallenge.plazoletaservice.domain.until.ConstValidation;
import com.backendchallenge.plazoletaservice.domain.until.TokenHolder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DishCaseTest {

    private IDishPersistencePort dishPersistencePort;
    private IRestaurantPersistencePort restaurantPersistencePort;
    private IUserPersistencePort userPersistencePort;
    private IDishServicePort dishServicePort;
    private ICategoryPersistencePort categoryPersistencePort;
    private IJwtPersistencePort jwtPersistencePort;
    private MockedStatic<TokenHolder> mockedTokenHolder;

    @BeforeEach
    void setUp() {
        dishPersistencePort = mock(IDishPersistencePort.class);
        restaurantPersistencePort = mock(IRestaurantPersistencePort.class);
        userPersistencePort = mock(IUserPersistencePort.class);
        categoryPersistencePort = mock(ICategoryPersistencePort.class);

        jwtPersistencePort = mock(IJwtPersistencePort.class);
        dishServicePort = new DishCase(dishPersistencePort, restaurantPersistencePort,
                userPersistencePort,categoryPersistencePort, jwtPersistencePort);

        mockedTokenHolder = mockStatic(TokenHolder.class);
        mockedTokenHolder.when(TokenHolder::getToken).thenReturn(ConstTest.VALID_TOKEN);
    }

    @AfterEach
    void tearDown() {
        mockedTokenHolder.close();
    }
    @Test
    void createDish_Success() {
        Dish dish = new Dish();
        dish.setIdRestaurant(ConstTest.ID_TEST);
        dish.setName(ConstTest.DISH_NAME_TEST);
        dish.setPrice(ConstTest.DISH_PRICE_TEST);
        dish.setDescription(ConstTest.DISH_DESCRIPTION_TEST);
        dish.setUrlImage(ConstTest.DISH_URL_IMAGE_TEST);
        dish.setCategories(List.of(new Category(ConstTest.ID_TEST,ConstTest.CATEGORY_NAME_TEST, ConstTest.CATEGORY_DESCRIPTION_TEST)));
        when(jwtPersistencePort.getUserId(ConstTest.VALID_TOKEN)).thenReturn(ConstTest.ID_TEST);
        when(userPersistencePort.findOwnerById(ConstTest.ID_TEST)).thenReturn(true);
        when(restaurantPersistencePort.existsRestaurantByIdAndOwner(ConstTest.ID_TEST, ConstTest.ID_TEST)).thenReturn(true);
        when(dishPersistencePort.createDish(dish)).thenReturn(true);

        dishServicePort.createDish(dish);

        verify(dishPersistencePort, times(1)).createDish(dish);
    }

    @Test
    void createDish_OwnerNotFound() {
        Dish dish = new Dish();
        dish.setIdRestaurant(ConstTest.ID_TEST);

        when(userPersistencePort.findOwnerById(ConstTest.ID_TEST)).thenReturn(false);

        assertThrows(OwnerNotFoundException.class, () -> dishServicePort.createDish(dish));
    }

    @Test
    void createDish_RestaurantNotFound() {
        Dish dish = new Dish();
        dish.setIdRestaurant(ConstTest.ID_TEST);
        when(jwtPersistencePort.getUserId(ConstTest.VALID_TOKEN)).thenReturn(ConstTest.ID_TEST);
        when(userPersistencePort.findOwnerById(ConstTest.ID_TEST)).thenReturn(true);
        when(restaurantPersistencePort.existsRestaurantByIdAndOwner(ConstTest.ID_TEST, ConstTest.ID_TEST)).thenReturn(false);

        assertThrows(RestaurantNotFoundException.class, () -> dishServicePort.createDish(dish));
    }

    @Test
    void createDish_DishNameEmpty() {
        Dish dish = new Dish();
        dish.setIdRestaurant(ConstTest.ID_TEST);
        dish.setName(ConstTest.DISH_NAME_EMPTY);

        when(jwtPersistencePort.getUserId(ConstTest.VALID_TOKEN)).thenReturn(ConstTest.ID_TEST);
        when(userPersistencePort.findOwnerById(ConstTest.ID_TEST)).thenReturn(true);
        when(restaurantPersistencePort.existsRestaurantByIdAndOwner(ConstTest.ID_TEST, ConstTest.ID_TEST)).thenReturn(true);

        assertThrows(DishNameEmptyException.class, () -> dishServicePort.createDish(dish));
    }

    @Test
    void createDish_DishPriceEmpty() {
        Dish dish = new Dish();
        dish.setIdRestaurant(ConstTest.ID_TEST);
        dish.setName(ConstTest.DISH_NAME_TEST);
        dish.setPrice(ConstTest.DISH_PRICE_NULL);
        when(jwtPersistencePort.getUserId(ConstTest.VALID_TOKEN)).thenReturn(ConstTest.ID_TEST);
        when(userPersistencePort.findOwnerById(ConstTest.ID_TEST)).thenReturn(true);
        when(restaurantPersistencePort.existsRestaurantByIdAndOwner(ConstTest.ID_TEST, ConstTest.ID_TEST)).thenReturn(true);

        assertThrows(DishPriceEmptyException.class, () -> dishServicePort.createDish(dish));
    }

    @Test
    void createDish_DishDescriptionEmpty() {
        Dish dish = new Dish();
        dish.setIdRestaurant(ConstTest.ID_TEST);
        dish.setName(ConstTest.DISH_NAME_TEST);
        dish.setPrice(ConstTest.DISH_PRICE_TEST);
        dish.setDescription(ConstTest.DISH_DESCRIPTION_EMPTY);
        when(jwtPersistencePort.getUserId(ConstTest.VALID_TOKEN)).thenReturn(ConstTest.ID_TEST);
        when(userPersistencePort.findOwnerById(ConstTest.ID_TEST)).thenReturn(true);
        when(restaurantPersistencePort.existsRestaurantByIdAndOwner(ConstTest.ID_TEST, ConstTest.ID_TEST)).thenReturn(true);

        assertThrows(DishDescriptionEmptyException.class, () -> dishServicePort.createDish(dish));
    }

    @Test
    void createDish_DishUrlImageEmpty() {
        Dish dish = new Dish();
        dish.setIdRestaurant(ConstTest.ID_TEST);
        dish.setName(ConstTest.DISH_NAME_TEST);
        dish.setPrice(ConstTest.DISH_PRICE_TEST);
        dish.setDescription(ConstTest.DISH_DESCRIPTION_TEST);
        dish.setUrlImage(ConstTest.DISH_URL_IMAGE_EMPTY);
        when(jwtPersistencePort.getUserId(ConstTest.VALID_TOKEN)).thenReturn(ConstTest.ID_TEST);
        when(userPersistencePort.findOwnerById(ConstTest.ID_TEST)).thenReturn(true);
        when(restaurantPersistencePort.existsRestaurantByIdAndOwner(ConstTest.ID_TEST, ConstTest.ID_TEST)).thenReturn(true);

        assertThrows(DishUrlImageEmptyException.class, () -> dishServicePort.createDish(dish));
    }

    @Test
    void createDish_DishCategoryEmpty() {
        Dish dish = new Dish();
        dish.setIdRestaurant(ConstTest.ID_TEST);
        dish.setName(ConstTest.DISH_NAME_TEST);
        dish.setPrice(ConstTest.DISH_PRICE_TEST);
        dish.setDescription(ConstTest.DISH_DESCRIPTION_TEST);
        dish.setUrlImage(ConstTest.DISH_URL_IMAGE_TEST);
        dish.setCategories(null);
        when(jwtPersistencePort.getUserId(ConstTest.VALID_TOKEN)).thenReturn(ConstTest.ID_TEST);
        when(userPersistencePort.findOwnerById(ConstTest.ID_TEST)).thenReturn(true);
        when(restaurantPersistencePort.existsRestaurantByIdAndOwner(ConstTest.ID_TEST, ConstTest.ID_TEST)).thenReturn(true);

        assertThrows(DishCategoryEmptyException.class, () -> dishServicePort.createDish(dish));
    }

    @Test
    void createDish_DishPriceInvalidValue() {
        Dish dish = new Dish();
        dish.setIdRestaurant(ConstTest.ID_TEST);
        dish.setName(ConstTest.DISH_NAME_TEST);
        dish.setPrice(ConstTest.DISH_PRICE_INVALID);
        dish.setDescription(ConstTest.DISH_DESCRIPTION_TEST);
        dish.setUrlImage(ConstTest.DISH_URL_IMAGE_TEST);
        dish.setCategories(ConstTest.CATEGORIES_TEST);
        when(jwtPersistencePort.getUserId(ConstTest.VALID_TOKEN)).thenReturn(ConstTest.ID_TEST);
        when(userPersistencePort.findOwnerById(ConstTest.ID_TEST)).thenReturn(true);
        when(restaurantPersistencePort.existsRestaurantByIdAndOwner(ConstTest.ID_TEST, ConstTest.ID_TEST)).thenReturn(true);

        assertThrows(DishPriceInvalidValueException.class, () -> dishServicePort.createDish(dish));
    }

    @Test
    void createDish_RestaurantNotFound_DishPersistenceFailure() {
        Dish dish = new Dish();
        dish.setIdRestaurant(ConstTest.ID_TEST);
        dish.setName(ConstTest.DISH_NAME_TEST);
        dish.setPrice(ConstTest.DISH_PRICE_TEST);
        dish.setDescription(ConstTest.DISH_DESCRIPTION_TEST);
        dish.setUrlImage(ConstTest.DISH_URL_IMAGE_TEST);
        dish.setCategories(ConstTest.CATEGORIES_TEST);
        when(jwtPersistencePort.getUserId(ConstTest.VALID_TOKEN)).thenReturn(ConstTest.ID_TEST);
        when(userPersistencePort.findOwnerById(ConstTest.ID_TEST)).thenReturn(true);
        when(restaurantPersistencePort.existsRestaurantByIdAndOwner(ConstTest.ID_TEST, ConstTest.ID_TEST)).thenReturn(true);
        when(dishPersistencePort.createDish(dish)).thenReturn(false);

        assertThrows(RestaurantNotFoundException.class, () -> dishServicePort.createDish(dish));
    }

    @Test
    void updateDish_withValidParams_shouldSucceed() {
        when(dishPersistencePort.findDishById(ConstTest.ID_TEST)).thenReturn(true);
        when(dishPersistencePort.getRestaurantIdByDishId(ConstTest.ID_TEST)).thenReturn(ConstTest.ID_TEST);
        when(restaurantPersistencePort.existsRestaurantByIdAndOwner(ConstTest.ID_TEST, ConstTest.ID_TEST)).thenReturn(true);
        when(jwtPersistencePort.getUserId(ConstTest.VALID_TOKEN)).thenReturn(ConstTest.ID_TEST);
        assertDoesNotThrow(() -> dishServicePort.updateDish(ConstTest.ID_TEST, ConstTest.DISH_DESCRIPTION_TEST, ConstTest.DISH_PRICE_TEST));
    }

    @Test
    void updateDish_withInvalidDishId_shouldThrowDishNotFoundException() {
        when(dishPersistencePort.findDishById(ConstTest.ID_TEST)).thenReturn(false);
        when(jwtPersistencePort.getUserId(ConstTest.VALID_TOKEN)).thenReturn(ConstTest.ID_TEST);
        assertThrows(DishNotFoundException.class, () -> dishServicePort.updateDish(ConstTest.ID_TEST, ConstTest.DISH_DESCRIPTION_TEST, ConstTest.DISH_PRICE_TEST));
    }

    @Test
    void updateDish_withEmptyDescription_shouldThrowDishDescriptionUpdateEmptyException() {
        when(dishPersistencePort.findDishById(ConstTest.ID_TEST)).thenReturn(true);
        when(dishPersistencePort.getRestaurantIdByDishId(ConstTest.ID_TEST)).thenReturn(ConstTest.ID_TEST);
        when(restaurantPersistencePort.existsRestaurantByIdAndOwner(ConstTest.ID_TEST, ConstTest.ID_TEST)).thenReturn(true);
        when(jwtPersistencePort.getUserId(ConstTest.VALID_TOKEN)).thenReturn(ConstTest.ID_TEST);
        assertThrows(DishDescriptionUpdateEmptyException.class, () -> dishServicePort.updateDish(ConstTest.ID_TEST, ConstTest.DISH_DESCRIPTION_EMPTY, ConstTest.DISH_PRICE_TEST));
    }

    @Test
    void updateDish_withNullPrice_shouldThrowDishPriceUpdateEmptyException() {
        when(dishPersistencePort.findDishById(ConstTest.ID_TEST)).thenReturn(true);
        when(dishPersistencePort.getRestaurantIdByDishId(ConstTest.ID_TEST)).thenReturn(ConstTest.ID_TEST);
        when(restaurantPersistencePort.existsRestaurantByIdAndOwner(ConstTest.ID_TEST, ConstTest.ID_TEST)).thenReturn(true);
        when(jwtPersistencePort.getUserId(ConstTest.VALID_TOKEN)).thenReturn(ConstTest.ID_TEST);
        assertThrows(DishPriceUpdateEmptyException.class, () -> dishServicePort.updateDish(ConstTest.ID_TEST, ConstTest.DISH_DESCRIPTION_TEST, ConstTest.DISH_PRICE_NULL));
    }

    @Test
    void updateDish_withInvalidRestaurantId_shouldThrowRestaurantNotFoundException() {
        when(dishPersistencePort.findDishById(ConstTest.ID_TEST)).thenReturn(true);
        when(dishPersistencePort.getRestaurantIdByDishId(ConstTest.ID_TEST)).thenReturn(0L);
        when(jwtPersistencePort.getUserId(ConstTest.VALID_TOKEN)).thenReturn(ConstTest.ID_TEST);
        assertThrows(DishNotFoundException.class, () -> dishServicePort.updateDish(ConstTest.ID_TEST, ConstTest.DISH_DESCRIPTION_TEST, ConstTest.DISH_PRICE_TEST));
    }

    @Test
    void updateDish_withInvalidRestaurantOwner_shouldThrowRestaurantNotFoundException() {
        when(dishPersistencePort.findDishById(ConstTest.ID_TEST)).thenReturn(true);
        when(dishPersistencePort.getRestaurantIdByDishId(ConstTest.ID_TEST)).thenReturn(ConstTest.ID_TEST);
        when(restaurantPersistencePort.existsRestaurantByIdAndOwner(ConstTest.ID_TEST, ConstTest.ID_TEST)).thenReturn(false);
        when(jwtPersistencePort.getUserId(ConstTest.VALID_TOKEN)).thenReturn(ConstTest.ID_TEST);
        assertThrows(RestaurantNotFoundException.class, () -> dishServicePort.updateDish(ConstTest.ID_TEST, ConstTest.DISH_DESCRIPTION_TEST, ConstTest.DISH_PRICE_TEST));
    }
    @Test
    void changeDishStatus_withValidParams_shouldSucceed() {
        when(dishPersistencePort.findDishById(ConstTest.ID_TEST)).thenReturn(true);
        when(dishPersistencePort.getRestaurantIdByDishId(ConstTest.ID_TEST)).thenReturn(ConstTest.ID_TEST);
        when(restaurantPersistencePort.existsRestaurantByIdAndOwner(ConstTest.ID_TEST, ConstTest.ID_TEST)).thenReturn(true);
        when(jwtPersistencePort.getUserId(ConstTest.VALID_TOKEN)).thenReturn(ConstTest.ID_TEST);
        Dish dish = new Dish();
        when(dishPersistencePort.getDishById(ConstTest.ID_TEST)).thenReturn(dish);

        assertDoesNotThrow(() -> dishServicePort.changeDishStatus(ConstTest.ID_TEST, true));
        verify(dishPersistencePort, times(1)).changeDishStatus(dish, ConstTest.ID_TEST);
    }

    @Test
    void changeDishStatus_withInvalidDishId_shouldThrowDishNotFoundException() {
        when(dishPersistencePort.findDishById(ConstTest.ID_TEST)).thenReturn(false);
        when(jwtPersistencePort.getUserId(ConstTest.VALID_TOKEN)).thenReturn(ConstTest.ID_TEST);

        assertThrows(DishNotFoundException.class, () -> dishServicePort.changeDishStatus(ConstTest.ID_TEST, true));
    }

    @Test
    void changeDishStatus_withInvalidRestaurantId_shouldThrowRestaurantNotFoundException() {
        when(dishPersistencePort.findDishById(ConstTest.ID_TEST)).thenReturn(true);
        when(dishPersistencePort.getRestaurantIdByDishId(ConstTest.ID_TEST)).thenReturn(0L);
        when(jwtPersistencePort.getUserId(ConstTest.VALID_TOKEN)).thenReturn(ConstTest.ID_TEST);

        assertThrows(RestaurantNotFoundException.class, () -> dishServicePort.changeDishStatus(ConstTest.ID_TEST, true));
    }

    @Test
    void changeDishStatus_withInvalidRestaurantOwner_shouldThrowRestaurantNotFoundException() {
        when(dishPersistencePort.findDishById(ConstTest.ID_TEST)).thenReturn(true);
        when(dishPersistencePort.getRestaurantIdByDishId(ConstTest.ID_TEST)).thenReturn(ConstTest.ID_TEST);
        when(restaurantPersistencePort.existsRestaurantByIdAndOwner(ConstTest.ID_TEST, ConstTest.ID_TEST)).thenReturn(false);
        when(jwtPersistencePort.getUserId(ConstTest.VALID_TOKEN)).thenReturn(ConstTest.ID_TEST);

        assertThrows(RestaurantNotFoundException.class, () -> dishServicePort.changeDishStatus(ConstTest.ID_TEST, true));
    }

    @Test
    void getDishesByRestaurant_withValidParameters_shouldReturnDishes() {
        PageCustom<Dish> mockPage = new PageCustom<>(
                ConstTest.CURRENT_PAGE_TEST, ConstTest.PAGE_SIZE_TEST, ConstTest.TOTAL_PAGES,
                List.of(new Dish(ConstTest.ID_TEST, ConstTest.ID_TEST,
                ConstTest.DISH_NAME_TEST, ConstTest.DISH_PRICE_TEST,
                ConstTest.DISH_DESCRIPTION_TEST, ConstTest.DISH_URL_IMAGE_TEST,
                        ConstTest.CATEGORIES_TEST)));

        when(restaurantPersistencePort.existsRestaurantById(ConstTest.ID_TEST)).thenReturn(true);
        when(dishPersistencePort.getDishesByRestaurant(ConstTest.ID_TEST, ConstTest.CURRENT_PAGE_TEST,
                ConstTest.PAGE_SIZE_TEST, ConstTest.FILTER_BY_TEST, ConstTest.ORDER_DIRECTION_TEST))
                .thenReturn(mockPage);

        PageCustom<Dish> result = dishServicePort.getDishesByRestaurant(ConstTest.ID_TEST, ConstTest.CURRENT_PAGE_TEST,
                ConstTest.PAGE_SIZE_TEST, ConstTest.FILTER_BY_TEST, ConstTest.ORDER_DIRECTION_TEST);

        verify(restaurantPersistencePort, times(ConstValidation.ONE)).existsRestaurantById(ConstTest.ID_TEST);
        verify(dishPersistencePort, times(ConstValidation.ONE)).getDishesByRestaurant(ConstTest.ID_TEST,
                ConstTest.CURRENT_PAGE_TEST, ConstTest.PAGE_SIZE_TEST, ConstTest.FILTER_BY_TEST,
                ConstTest.ORDER_DIRECTION_TEST);
        assertEquals(mockPage, result);
    }

    @Test
    void getDishesByRestaurant_withBlankOrderDirection_shouldThrowDishesOrderDirectionEmptyException() {
        when(restaurantPersistencePort.existsRestaurantById(ConstTest.ID_TEST)).thenReturn(true);

        assertThrows(DishesOrderDirectionEmptyException.class, () -> dishServicePort.
                getDishesByRestaurant(ConstTest.ID_TEST, ConstTest.CURRENT_PAGE_TEST,
                        ConstTest.PAGE_SIZE_TEST, null, ConstTest.EMPTY_TEST));
        verify(restaurantPersistencePort, times(ConstValidation.ONE)).existsRestaurantById(ConstTest.ID_TEST);
    }

    @Test
    void getDishesByRestaurant_withValidParams_shouldReturnPageCustom() {
        PageCustom<Dish> mockPage = new PageCustom<>(
                ConstTest.CURRENT_PAGE_TEST, ConstTest.PAGE_SIZE_TEST, ConstTest.TOTAL_PAGES,
                List.of(new Dish(ConstTest.ID_TEST, ConstTest.ID_TEST,
                        ConstTest.DISH_NAME_TEST, ConstTest.DISH_PRICE_TEST,
                        ConstTest.DISH_DESCRIPTION_TEST, ConstTest.DISH_URL_IMAGE_TEST,
                        ConstTest.CATEGORIES_TEST)));
        when(restaurantPersistencePort.existsRestaurantById(ConstTest.ID_TEST)).thenReturn(true);
        when(dishPersistencePort.getDishesByRestaurant(ConstTest.ID_TEST, ConstTest.CURRENT_PAGE_TEST,
                ConstTest.PAGE_SIZE_TEST, ConstTest.FILTER_BY_TEST, ConstTest.ORDER_DIRECTION_TEST))
                .thenReturn(mockPage);

        PageCustom<Dish> result = dishServicePort.getDishesByRestaurant(ConstTest.ID_TEST, ConstTest.CURRENT_PAGE_TEST,
                ConstTest.PAGE_SIZE_TEST, ConstTest.FILTER_BY_TEST, ConstTest.ORDER_DIRECTION_TEST);

        assertNotNull(result);
        assertEquals(mockPage, result);
        verify(restaurantPersistencePort, times(1)).existsRestaurantById(ConstTest.ID_TEST);
        verify(dishPersistencePort, times(1)).getDishesByRestaurant(ConstTest.ID_TEST, ConstTest.CURRENT_PAGE_TEST,
                ConstTest.PAGE_SIZE_TEST, ConstTest.FILTER_BY_TEST, ConstTest.ORDER_DIRECTION_TEST);
    }

    @Test
    void getDishesByRestaurant_withInvalidRestaurantId_shouldThrowRestaurantNotFoundException() {
        when(restaurantPersistencePort.existsRestaurantById(ConstTest.ID_TEST)).thenReturn(false);

        assertThrows(RestaurantNotFoundException.class, () -> dishServicePort.getDishesByRestaurant(ConstTest.ID_TEST,
                ConstTest.CURRENT_PAGE_TEST, ConstTest.PAGE_SIZE_TEST, ConstTest.FILTER_BY_TEST,
                ConstTest.ORDER_DIRECTION_TEST));
        verify(restaurantPersistencePort, times(1)).existsRestaurantById(ConstTest.ID_TEST);
        verifyNoInteractions(dishPersistencePort);
    }

    @Test
    void getDishesByRestaurant_withInvalidPageSize_shouldThrowDishesPageSizeInvalidException() {
        when(restaurantPersistencePort.existsRestaurantById(ConstTest.ID_TEST)).thenReturn(true);

        assertThrows(DishesPageSizeInvalidException.class, () -> dishServicePort.getDishesByRestaurant(ConstTest.ID_TEST,
                ConstTest.CURRENT_PAGE_TEST, ConstTest.INVALID_PAGE_SIZE_TEST, ConstTest.FILTER_BY_TEST,
                ConstTest.ORDER_DIRECTION_TEST));
        verify(restaurantPersistencePort, times(1)).existsRestaurantById(ConstTest.ID_TEST);
    }

    @Test
    void getDishesByRestaurant_withInvalidCurrentPage_shouldThrowDishesCurrentPageInvalidException() {
        when(restaurantPersistencePort.existsRestaurantById(ConstTest.ID_TEST)).thenReturn(true);

        assertThrows(DishesCurrentPageInvalidException.class, () -> dishServicePort.getDishesByRestaurant(ConstTest.ID_TEST,
                null, ConstTest.PAGE_SIZE_TEST, ConstTest.FILTER_BY_TEST,
                ConstTest.ORDER_DIRECTION_TEST));
        verify(restaurantPersistencePort, times(1)).existsRestaurantById(ConstTest.ID_TEST);
    }

    @Test
    void getDishesByRestaurant_withMinusOneCurrentPageInResult_shouldThrowDishesCurrentPageInvalidException() {
        PageCustom<Dish> mockPage = new PageCustom<>(ConstValidation.MINUS_ONE, ConstTest.PAGE_SIZE_TEST,
                ConstTest.TOTAL_PAGES, List.of(new Dish(ConstTest.ID_TEST, ConstTest.ID_TEST,
                ConstTest.DISH_NAME_TEST, ConstTest.DISH_PRICE_TEST, ConstTest.DISH_DESCRIPTION_TEST,
                ConstTest.DISH_URL_IMAGE_TEST, ConstTest.CATEGORIES_TEST)));

        when(restaurantPersistencePort.existsRestaurantById(ConstTest.ID_TEST)).thenReturn(true);
        when(dishPersistencePort.getDishesByRestaurant(ConstTest.ID_TEST, ConstTest.CURRENT_PAGE_TEST,
                ConstTest.PAGE_SIZE_TEST, ConstTest.FILTER_BY_TEST, ConstTest.ORDER_DIRECTION_TEST))
                .thenReturn(mockPage);

        assertThrows(DishesCurrentPageInvalidException.class, () -> dishServicePort.getDishesByRestaurant(ConstTest.ID_TEST,
                ConstTest.CURRENT_PAGE_TEST, ConstTest.PAGE_SIZE_TEST, ConstTest.FILTER_BY_TEST,
                ConstTest.ORDER_DIRECTION_TEST));
        verify(restaurantPersistencePort, times(1)).existsRestaurantById(ConstTest.ID_TEST);
        verify(dishPersistencePort, times(1)).getDishesByRestaurant(ConstTest.ID_TEST, ConstTest.CURRENT_PAGE_TEST,
                ConstTest.PAGE_SIZE_TEST, ConstTest.FILTER_BY_TEST, ConstTest.ORDER_DIRECTION_TEST);
    }

    @Test
    void getDishesByRestaurant_withInvalidOrderDirection_shouldThrowDishesOrderDirectionInvalidException() {
        when(restaurantPersistencePort.existsRestaurantById(ConstTest.ID_TEST)).thenReturn(true);

        assertThrows(DishesOrderDirectionInvalidException.class, () -> dishServicePort.
                getDishesByRestaurant(ConstTest.ID_TEST, ConstTest.CURRENT_PAGE_TEST,
                        ConstTest.PAGE_SIZE_TEST, null, ConstTest.ORDER_DIRECTION_INVALID));
        verify(restaurantPersistencePort, times(ConstValidation.ONE)).existsRestaurantById(ConstTest.ID_TEST);
    }
}