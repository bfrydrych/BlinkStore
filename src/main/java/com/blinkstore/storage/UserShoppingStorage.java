package com.blinkstore.storage;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public class UserShoppingStorage {
	//itemId, quantity
	private Map<Long, Long> userShopping = new ConcurrentHashMap<>();
	
	
	public Map<Long, Long> getAll() {
		return userShopping;
	}
}
