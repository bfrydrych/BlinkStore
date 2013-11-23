package com.blinkstore.controllers;

import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.blinkstore.model.Item;
import com.blinkstore.model.UserShopping;

public class ItemBuyResponse {

	private List<Item> items;
	private List<UserShopping> userShoppings;
	
	
	public ItemBuyResponse() {
		super();
	}

	public ItemBuyResponse(List<Item> items, List<UserShopping> userShoppings) {
		super();
		this.items = items;
		this.userShoppings = userShoppings;
	}
	
	public int hashCode() {
		HashCodeBuilder builder = new HashCodeBuilder();
		return builder.append(items).append(userShoppings).toHashCode();
	}
	
	public boolean equals(Object o) {
		if(o == null) return false;
		if (o instanceof ItemBuyResponse) {
			EqualsBuilder builder = new EqualsBuilder();
			ItemBuyResponse obj = (ItemBuyResponse) o;
			return builder.append(items, obj.items).append(userShoppings, obj.userShoppings).isEquals();
		}
		return false;
	}
	
	public List<Item> getItems() {
		return items;
	}
	public void setItems(List<Item> items) {
		this.items = items;
	}
	public List<UserShopping> getUserShoppings() {
		return userShoppings;
	}
	public void setUserShoppings(List<UserShopping> userShoppings) {
		this.userShoppings = userShoppings;
	}
	
	public String toString() {
		return String.format("items:%s shoppings:%s", items, userShoppings);
	}
}
