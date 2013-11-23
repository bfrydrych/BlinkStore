package com.blinkstore.services.cart;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

public class UserCart {

	private static final Logger log = Logger.getLogger(UserCart.class);
	private static final int INITIAL_ITEM_QUANTITY = 1;
	
	//<ItemId, Quantity>
	private Map<Long, Integer> items = new HashMap<Long, Integer>();
	
	public void update(Long itemId, Integer quantity) {
		if (itemId == null) return;
		if (items.containsKey(itemId)) {
			increaseItemQuantityInCart(itemId, quantity);
		} else {
			items.put(itemId, INITIAL_ITEM_QUANTITY);
		}
		log.trace("Cart updated. Has " + items.size() + " distinct items");
	}

	private void increaseItemQuantityInCart(Long itemId, Integer quantity) {
		Integer currentQuantity = items.get(itemId);
		items.put(itemId, currentQuantity + quantity);
	}
}
