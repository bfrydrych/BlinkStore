package com.blinkstore.storage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.blinkstore.model.Item;


public class StockStorage {

	private Map<Long, Item> itemsAvailability = new ConcurrentHashMap<>();
	
	public StockStorage() {
		itemsAvailability.put(1L, new Item("item1",1L, 10L));
		itemsAvailability.put(2L, new Item("item2",2L, 20L));
	}
	
	public Item getItem(Long itemId) {
		return itemsAvailability.get(itemId);
	}

	public Map<Long, Item> getItemsAvailability() {
		return itemsAvailability;
	}

	public void setItemsAvailability(Map<Long, Item> itemsAvailability) {
		this.itemsAvailability = itemsAvailability;
	}

	public List<Item> getItemsAndQuantity() {
		return new ArrayList(itemsAvailability.values());
	}

	public Map<Long, Item> getAllById(List<Long> itemsId) {
		Map<Long, Item> result = new HashMap<>();
		for (Long id : itemsId) {
			result.put(id, itemsAvailability.get(id));
		}
		return result;
	}


	public void putAll(List<Item> updatedItems) {
		for (Item item : updatedItems) {
			itemsAvailability.put(item.getItemId(), item);
		}
	}
}
