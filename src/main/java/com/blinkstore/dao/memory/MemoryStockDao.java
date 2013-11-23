package com.blinkstore.dao.memory;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.blinkstore.dao.IStockDao;
import com.blinkstore.model.Item;
import com.blinkstore.storage.StockStorage;


@Repository
public class MemoryStockDao implements IStockDao {

	private StockStorage stock;
	
	public MemoryStockDao() {
		stock = new StockStorage();
	}
	
	public List<Item> getCurrentStock() {
		List<Item> itemsAndQuantity = stock.getItemsAndQuantity();
		Collections.sort(itemsAndQuantity, Item.byNameComparator);
		return itemsAndQuantity;
	}

	@Override
	public void updateItems(List<Item> updatedItems) {
		this.stock.putAll(updatedItems);
	}

	@Override
	public Item getItem(Long itemId) {
		return stock.getItem(itemId);
	}

	@Override
	public Map<Long, Item> getItemsById(List<Long> itemsId) {
		return stock.getAllById(itemsId);
	}
}
