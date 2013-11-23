package com.blinkstore.model;

import java.util.Comparator;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class UserShopping {

	public static final Comparator<UserShopping> byNameComparator = new Comparator<UserShopping>(){
		public int compare(UserShopping o1, UserShopping o2) {
			return o1.getItemName().compareTo(o2.getItemName());
		}
	};
	
	private String itemName;
	private Long quantity;
	private Long itemId;
	
	public UserShopping() {
		
	}
	
	public UserShopping(Long quantity, Long itemId) {
		super();
		this.quantity = quantity;
		this.itemId = itemId;
	}
	
	public UserShopping(String itemName, Long quantity) {
		this.itemName = itemName;
		this.quantity = quantity;
	}


	public UserShopping(String itemName, Long quantity, Long itemId) {
		super();
		this.itemName = itemName;
		this.quantity = quantity;
		this.itemId = itemId;
	}

	public int hashCode() {
		HashCodeBuilder builder = new HashCodeBuilder();
		return builder.append(itemName).append(quantity).append(itemId).toHashCode();
	}
	
	public boolean equals(Object o) {
		if (o == null) return false;
		if (o instanceof UserShopping) {
			UserShopping obj = (UserShopping) o;
			EqualsBuilder builder = new EqualsBuilder();
			return builder.append(itemName, obj.itemName).append(quantity, obj.quantity).append(itemId, obj.itemId).isEquals();
		}
		return false;
	}
	
	public void increaseQuantity(Long buyQuantity) {
		quantity += buyQuantity;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}
	
	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public String toString() {
		return String.format("itemId:%s itemName:%s quantity:%s", itemId, itemName, quantity); 
	}
}
