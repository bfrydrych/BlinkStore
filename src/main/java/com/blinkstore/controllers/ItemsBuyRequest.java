package com.blinkstore.controllers;

import java.util.ArrayList;
import java.util.List;

import com.blinkstore.controllers.validators.EnoughItemsOnStock;


public class ItemsBuyRequest {

	@EnoughItemsOnStock
	private List<ItemBuyRequest> itemsToBuy;
	
	public List<Long> extractItemsId() {
		List<Long> result = new ArrayList<>();
		for (ItemBuyRequest itemBuyRequest : itemsToBuy) {
			result.add(itemBuyRequest.getItemId());
		}
		return result;
	}

	public List<ItemBuyRequest> getItemsToBuy() {
		return itemsToBuy;
	}

	public void setItemsToBuy(List<ItemBuyRequest> itemsToBuy) {
		this.itemsToBuy = itemsToBuy;
	}
}
