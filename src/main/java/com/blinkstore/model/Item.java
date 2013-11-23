package com.blinkstore.model;

import java.util.Comparator;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.blinkstore.controllers.ItemBuyRequest;

public class Item {

	public static final Comparator<Item> byNameComparator = new Comparator<Item>(){
		public int compare(Item o1, Item o2) {
			return o1.getName().compareTo(o2.getName());
		}
	};
	
	private String name;
	private Long itemId;
	private Long available;
	
	public Item() {
		
	}
	
	public Item(String name, Long id, Long available) {
		this.name = name;
		this.itemId = id;
		this.available = available;
	}
	
	public boolean isOrderRealisationPossible(ItemBuyRequest buyRequest) {
		long availableAfterRealisation = available - buyRequest.getQuantityToBuy();
		boolean isOrderRealisationPossible = availableAfterRealisation >= 0;
		return isOrderRealisationPossible;
	}
	
	public void decreaseAvailableAmount(Long quantity) {
		available -= quantity;
	}

	public boolean equals(Object obj) {
	   if (obj == null) { return false; }
	   if (obj == this) { return true; }
	   if (obj.getClass() != getClass()) {
	     return false;
	   }
	   Item rhs = (Item) obj;
	   return new EqualsBuilder().append(name, rhs.name)
	                 			 .append(itemId, rhs.itemId)
	                 			 .append(available, rhs.available)
	                 			 .isEquals();
	}
	
	public int hashCode() {
		return new HashCodeBuilder(17, 37).append(name)
								       .append(itemId)
								       .append(available)
								       .toHashCode();
	}
	
	public void setItemId(Long id) {
		this.itemId = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
	public Long getItemId() {
		return itemId;
	}
	
	
	
	public Long getAvailable() {
		return available;
	}

	public void setAvailable(Long available) {
		this.available = available;
	}

	public String toString() {
		return String.format("[Item: %s:%s]", name, itemId);
	}
}
