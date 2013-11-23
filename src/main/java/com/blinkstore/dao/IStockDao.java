package com.blinkstore.dao;

import java.util.List;
import java.util.Map;

import com.blinkstore.model.Item;


public interface IStockDao {
	List<Item> getCurrentStock();
	void updateItems(List<Item> itemsToUpdate);
	Item getItem(Long id);
	Map<Long, Item> getItemsById(List<Long> itemsId);
}
