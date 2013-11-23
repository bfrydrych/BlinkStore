package com.blinkstore.services.dao.memory;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.blinkstore.dao.memory.MemoryStockDao;
import com.blinkstore.model.Item;


public class MemoryStockDaoTest {


	private MemoryStockDao memoryStockDao;
	
	@Before
	public void before() {
		memoryStockDao = new MemoryStockDao();
	}
	
	@Test
	public void shouldUpdateItems() {
		List<Item> itemsToUpdate = createItemsToUpdate();
		
		memoryStockDao.updateItems(itemsToUpdate);
		
		Map<Long, Item> actualUpdatedItems = memoryStockDao.getItemsById(Arrays.asList(1L, 2L));
		Map<Long, Item> expectedUpdatedItems = createExpectedUpdatedItems();
		
		assertThat(actualUpdatedItems, is(expectedUpdatedItems));
	}
	
	@Test
	public void shouldReturnCurrentStock() {
		List<Item> itemsToUpdate = createItemsToUpdate();
		
		memoryStockDao.updateItems(itemsToUpdate);
		List<Item> actualStock = memoryStockDao.getCurrentStock();
		
		List<Item> expectedStock = createExpectedStock();
		
		assertThat(actualStock, is(expectedStock));
	}

	private List<Item> createExpectedStock() {
		List<Item> result = new ArrayList<>();
		result.add(new Item("item1", 1L, 15L));
		result.add(new Item("item2", 2L, 15L));
		return result;
	}

	private Map<Long, Item> createExpectedUpdatedItems() {
		Map<Long, Item> result = new HashMap<>();
		result.put(1L, new Item("item1", 1L, 15L));
		result.put(2L, new Item("item2", 2L, 15L));
		
		return result;
	}

	private List<Item> createItemsToUpdate() {
		List<Item> result = new ArrayList<>();
		result.add(new Item("item1", 1L, 15L));
		result.add(new Item("item2", 2L, 15L));
		
		return result;
	}
}
