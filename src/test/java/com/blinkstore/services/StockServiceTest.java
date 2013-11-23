package com.blinkstore.services;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.blinkstore.controllers.ItemBuyRequest;
import com.blinkstore.controllers.ItemsBuyRequest;
import com.blinkstore.dao.IStockDao;
import com.blinkstore.dao.IUserShoppingDao;
import com.blinkstore.model.Item;
import com.blinkstore.model.UserShopping;
import com.blinkstore.services.StockService;


public class StockServiceTest {

	private StockService stockService;
	private IStockDao stockDaoMock;
	private IUserShoppingDao userShoppingDao;
	
	@Before
	public void before() {
		stockDaoMock = mock(IStockDao.class);
		userShoppingDao = mock(IUserShoppingDao.class);
		stockService = new StockService(stockDaoMock, userShoppingDao);
	}
	
	@Test
	public void shouldUpdateStock() {
		when(stockDaoMock.getItemsById(getItemsIds())).thenReturn(createCurrentItems());
		
		ItemsBuyRequest itemsToBuy = createItemsToBuy();
		stockService.buyItems(itemsToBuy);
		
		verify(stockDaoMock, times(1)).updateItems(createExpectedItemsToBuy());
	}
	
	@Test
	public void shouldCreateUserShoppingsWhenEmptyCart() {
		when(stockDaoMock.getItemsById(getItemsIds())).thenReturn(createCurrentItems());
		when(userShoppingDao.getUserShopping()).thenReturn(createEmptyCurrentShoppings());
		
		ItemsBuyRequest itemsToBuy = createItemsToBuy();
		stockService.buyItems(itemsToBuy);
		
		verify(userShoppingDao, times(1)).updateShoppings(createExpectedUserShoppings());
	}
	
	@Test
	public void shouldIncreaseUserShoppings() {
		when(stockDaoMock.getItemsById(getItemsIds())).thenReturn(createCurrentItems());
		when(userShoppingDao.getUserShopping()).thenReturn(createCurrentShoppings());
		
		ItemsBuyRequest itemsToBuy = createItemsToBuy();
		stockService.buyItems(itemsToBuy);
		
		verify(userShoppingDao, times(1)).updateShoppings(createExpectedIncreasedUserShoppings());
	}
	
	private List<UserShopping> createExpectedUserShoppings() {
		List<UserShopping> result = new ArrayList<>();
		result.add(new UserShopping(1L, 1L));
		result.add(new UserShopping(1L, 2L));
		return result;
	}
	
	private List<UserShopping> createExpectedIncreasedUserShoppings() {
		List<UserShopping> result = new ArrayList<>();
		result.add(new UserShopping("item1", 2L, 1L));
		result.add(new UserShopping("item2", 2L, 2L));
		return result;
	}

	private List<Item> createExpectedItemsToBuy() {
		List<Item> result = new ArrayList<>();
		result.add(new Item("item1", 1L, 9L));
		result.add(new Item("item2", 2L, 19L));
		return result;
	}
	
	private Map<Long, Item> createCurrentItems() {
		Map<Long, Item> result = new HashMap<>();
		result.put(1L, new Item("item1", 1L, 10L));
		result.put(2L, new Item("item2", 2L, 20L));
		return result;
	}
	
	private Map<Long, UserShopping> createEmptyCurrentShoppings() {
		return new HashMap<>();
	}
	
	private Map<Long, UserShopping> createCurrentShoppings() {
		Map<Long, UserShopping> result = new HashMap<>();
		result.put(1L, new UserShopping("item1", 1L, 1L));
		result.put(2L, new UserShopping("item2", 1L, 2L));
		return result;
	}

	private List<Long> getItemsIds() {
		return Arrays.asList(1L, 2L);
	}

	private ItemsBuyRequest createItemsToBuy() {
		List<ItemBuyRequest> result = new ArrayList<>();
		ItemBuyRequest item1 = new ItemBuyRequest();
		item1.setItemId(1L);
		item1.setQuantityToBuy(1L);
		result.add(item1);
		
		ItemBuyRequest item2 = new ItemBuyRequest();
		item2.setItemId(2L);
		item2.setQuantityToBuy(1L);
		result.add(item2);
		
		ItemsBuyRequest request = new ItemsBuyRequest();
		request.setItemsToBuy(result);
		
		return request;
	}
}
