package com.blinkstore.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blinkstore.controllers.ItemBuyRequest;
import com.blinkstore.controllers.ItemsBuyRequest;
import com.blinkstore.dao.IStockDao;
import com.blinkstore.dao.IUserShoppingDao;
import com.blinkstore.model.Item;
import com.blinkstore.model.UserShopping;


@Service
public class StockService {

	private IStockDao stockDao;
	private IUserShoppingDao userShoppingDao;
	
	private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
	private Lock writeLock = readWriteLock.writeLock();
	private Lock readLock = readWriteLock.readLock();
	
	@Autowired
	public StockService(IStockDao stockDao, IUserShoppingDao userShoppingDao) {
		this.stockDao = stockDao;
		this.userShoppingDao = userShoppingDao;
	}
	
	public List<Item> getCurrentStock() {
		List<Item> currentStock = Collections.emptyList();
		readLock.lock();
		try {
			currentStock = stockDao.getCurrentStock();
		} finally {
			readLock.unlock();
		}
		return currentStock;
	}
		
	public void buyItems(ItemsBuyRequest itemsToBuy) {
		writeLock.lock();
		try {
			Map<Long, Item> itemsById = stockDao.getItemsById(itemsToBuy.extractItemsId());
			List<Item> updatedItems = decreaseItemsAvailability(itemsById, itemsToBuy.getItemsToBuy());
			stockDao.updateItems(updatedItems);
			
			Map<Long, UserShopping> currentShoppings = userShoppingDao.getUserShopping();
			List<UserShopping> updatedShoppings = increaseShoppingQuantity(currentShoppings, itemsToBuy.getItemsToBuy());
			userShoppingDao.updateShoppings(updatedShoppings);
		} finally {
			writeLock.unlock();
		}
	}
	
	private List<Item> decreaseItemsAvailability(Map<Long, Item> itemsOnStock, List<ItemBuyRequest> itemsToBuy) {
		List<Item> updatedItems = new ArrayList<>();
		for (ItemBuyRequest itemToBuy : itemsToBuy) {
			Item item = itemsOnStock.get(itemToBuy.getItemId());
			item.decreaseAvailableAmount(itemToBuy.getQuantityToBuy());
			updatedItems.add(item);
		}
		return updatedItems;
	}
	
	private List<UserShopping> increaseShoppingQuantity(Map<Long, UserShopping> currentShoppings, List<ItemBuyRequest> itemsToBuy) {
		List<UserShopping> updatedShoppings = new ArrayList<>();
		for (ItemBuyRequest itemToBuy : itemsToBuy) {
			if (currentShoppings.containsKey(itemToBuy.getItemId())) {
				UserShopping currentUserShopping = currentShoppings.get(itemToBuy.getItemId());
				currentUserShopping.increaseQuantity(itemToBuy.getQuantityToBuy());
				updatedShoppings.add(currentUserShopping);
			} else {
				UserShopping newShopping = new UserShopping();
				newShopping.setItemId(itemToBuy.getItemId());
				newShopping.setQuantity(itemToBuy.getQuantityToBuy());
				updatedShoppings.add(newShopping);
			}
		}
		return updatedShoppings;
	}
}
