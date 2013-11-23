package com.blinkstore.controllers.validators;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.blinkstore.controllers.ItemBuyRequest;
import com.blinkstore.controllers.validators.EnoughItemsOnStockValidator;
import com.blinkstore.dao.IStockDao;
import com.blinkstore.model.Item;
import com.blinkstore.storage.StockStorage;


public class EnoughItemsOnStockValidatorTest {

	private EnoughItemsOnStockValidator validator;
	private IStockDao stockDaoMock;
	
	@Before
	public void before() {
		stockDaoMock = mock(IStockDao.class);
		validator = new EnoughItemsOnStockValidator(stockDaoMock);
	}
	
	@Test
	public void shouldPassValidationWhenBuyLessThanIsOnStock() {
		List<Long> ids = Arrays.asList(1L, 2L);
		when(stockDaoMock.getItemsById(ids)).thenReturn(createCurrentStock());
		
		List<ItemBuyRequest> itemsToBuyRequest = createItemsToBuy(2L);
		boolean actualValid = validator.isValid(itemsToBuyRequest, null);
		
		assertThat(actualValid, is(true));
	}
	
	@Test
	public void shouldFailValidationWhenAttemptToBuyMoreThanOnStock() {
		List<Long> ids = Arrays.asList(1L, 2L);
		when(stockDaoMock.getItemsById(ids)).thenReturn(createCurrentStock());
		
		List<ItemBuyRequest> itemsToBuyRequest = createItemsToBuy(20L);
		boolean actualValid = validator.isValid(itemsToBuyRequest, null);
		
		assertThat(actualValid, is(false));
	}
	
	@Test
	public void shouldPassValidationWhenAttemptToBuyEqualAsOnStock() {
		List<Long> ids = Arrays.asList(1L, 2L);
		when(stockDaoMock.getItemsById(ids)).thenReturn(createCurrentStock());
		
		List<ItemBuyRequest> itemsToBuyRequest = createItemsToBuy(10L, 20L);
		boolean actualValid = validator.isValid(itemsToBuyRequest, null);
		
		assertThat(actualValid, is(true));
	}
	
	@Test
	public void shouldPassValidationWhenNoProductsRequested() {
		List<Long> ids = Arrays.asList(1L, 2L);
		when(stockDaoMock.getItemsById(ids)).thenReturn(createCurrentStock());
		
		List<ItemBuyRequest> itemsToBuyRequest = new ArrayList<>();
		boolean actualValid = validator.isValid(itemsToBuyRequest, null);
		
		assertThat(actualValid, is(true));
	}
	
	private List<ItemBuyRequest> createItemsToBuy(Long quantity) {
		return createItemsToBuy(quantity, quantity);
	}
	
	private List<ItemBuyRequest> createItemsToBuy(Long quantity1, Long quantity2) {
		List<ItemBuyRequest> result = new ArrayList<>();
		ItemBuyRequest item1 = new ItemBuyRequest();
		item1.setItemId(1L);
		item1.setQuantityToBuy(quantity1);
		result.add(item1);
		
		ItemBuyRequest item2 = new ItemBuyRequest();
		item2.setItemId(2L);
		item2.setQuantityToBuy(quantity2);
		result.add(item2);
		
		return result;
	}
	
	private Map<Long, Item> createCurrentStock() {
		Map<Long, Item> itemsAvailability = new HashMap<>();;
		itemsAvailability.put(1L, new Item("item1",1L, 10L));
		itemsAvailability.put(2L, new Item("item2",2L, 20L));
		StockStorage stockStorage = new StockStorage();
		stockStorage.setItemsAvailability(itemsAvailability);
		return stockStorage.getItemsAvailability();
	}
}
