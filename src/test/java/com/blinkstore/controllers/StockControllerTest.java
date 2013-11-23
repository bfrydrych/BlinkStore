package com.blinkstore.controllers;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.blinkstore.controllers.ItemBuyRequest;
import com.blinkstore.controllers.ItemBuyResponse;
import com.blinkstore.controllers.ItemsBuyRequest;
import com.blinkstore.controllers.StockController;
import com.blinkstore.model.Item;
import com.blinkstore.model.UserShopping;
import com.blinkstore.services.StockService;
import com.blinkstore.services.UserShoppingService;


public class StockControllerTest {

	private StockController stockController;
	private StockService stockServiceMock;
	private UserShoppingService userShoppingServiceMock;
	
	@Before
	public void before() {
		stockServiceMock = mock(StockService.class);
		userShoppingServiceMock = mock(UserShoppingService.class);
		stockController = new StockController(stockServiceMock, userShoppingServiceMock);
	}
	
	@Test
	public void shouldReturnCurrentStock() {
		
		when(stockServiceMock.getCurrentStock()).thenReturn(createCurrentStock());
		List<Item> actualStock = stockController.getStock();
		List<Item> expectedStock = createCurrentStock();
		
		assertThat(actualStock, is(expectedStock));
	}
	
	@Test
	public void shouldCallBuyItemsInService() {
		ItemsBuyRequest itemsToBuy = createItemsToBuyRequest();
		stockController.buyItems(itemsToBuy);
		
		verify(stockServiceMock, times(1)).buyItems(itemsToBuy);
	}
	
	@Test
	public void shouldReturnCurrentStockAndShoppingsIfRequestIsNull() {
		ItemsBuyRequest itemsToBuy = null;
		stockController.buyItems(itemsToBuy);
		
		verify(stockServiceMock, times(1)).getCurrentStock();
		verify(userShoppingServiceMock, times(1)).getUserShoppings();
		verify(stockServiceMock, never()).buyItems(Mockito.any(ItemsBuyRequest.class));
	}
	
	@Test
	public void shouldReturnCurrentStockAndShoppingsIfRequestContainsNullableList() {
		ItemsBuyRequest itemsToBuy = new ItemsBuyRequest();
		stockController.buyItems(itemsToBuy);
		
		verify(stockServiceMock, times(1)).getCurrentStock();
		verify(userShoppingServiceMock, times(1)).getUserShoppings();
		verify(stockServiceMock, never()).buyItems(Mockito.any(ItemsBuyRequest.class));
	}
	
	@Test
	public void shouldReturnCurrentStockAndShoppingsIfRequestContainsEmptyList() {
		ItemsBuyRequest itemsToBuy = new ItemsBuyRequest();
		itemsToBuy.setItemsToBuy(new ArrayList<ItemBuyRequest>());
		stockController.buyItems(itemsToBuy);
		
		verify(stockServiceMock, times(1)).getCurrentStock();
		verify(userShoppingServiceMock, times(1)).getUserShoppings();
		verify(stockServiceMock, times(1)).buyItems(itemsToBuy);
	}
	
	@Test
	public void shouldReturnCurrentStockAndUserShoppingsAfterBuyRequest() {
		when(stockServiceMock.getCurrentStock()).thenReturn(createCurrentStock());
		when(userShoppingServiceMock.getUserShoppings()).thenReturn(createUserShoppings());
		
		ItemsBuyRequest itemsToBuy = createItemsToBuyRequest();
		ItemBuyResponse actualResponse = stockController.buyItems(itemsToBuy);
		
		ItemBuyResponse expectedResponse = new ItemBuyResponse(createCurrentStock(), createUserShoppings());
		assertThat(actualResponse, is(expectedResponse));
	}

	private List<UserShopping> createUserShoppings() {
		List<UserShopping> result = new ArrayList<>();
		result.add(new UserShopping("item1", 1L));
		result.add(new UserShopping("item2", 1L));
		return result;
	}
	
	private ItemsBuyRequest createItemsToBuyRequest() {
		ItemsBuyRequest itemsToBuy = new ItemsBuyRequest();
		itemsToBuy.setItemsToBuy(createItemsToBuy());
		return itemsToBuy;
	}
	
	private List<ItemBuyRequest> createItemsToBuy() {
		List<ItemBuyRequest> result = new ArrayList<>();
		ItemBuyRequest item1 = new ItemBuyRequest();
		item1.setItemId(1L);
		item1.setQuantityToBuy(1L);
		result.add(item1);
		
		ItemBuyRequest item2 = new ItemBuyRequest();
		item2.setItemId(2L);
		item2.setQuantityToBuy(1L);
		result.add(item2);
		
		return result;
	}
	
	private List<Item> createCurrentStock() {
		List<Item> items = new ArrayList<>();
		
		Item item1 = new Item("item1", 1L, 10L);
		items.add(item1);
		
		Item item2 = new Item("item2", 2L, 20L);
		items.add(item2);
		
		return items;
	}
}
