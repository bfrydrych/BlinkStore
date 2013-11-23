package com.blinkstore.controllers;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class ItemBuyRequest {

	private Long itemId;
	private Long quantityToBuy;
	
	public int hashCode() {
		HashCodeBuilder builder = new HashCodeBuilder();
		return builder.append(itemId).append(quantityToBuy).toHashCode();
	}
	
	public boolean equals(Object o) {
		if(o == null) return false;
		if (o instanceof ItemBuyRequest) {
			ItemBuyRequest obj = (ItemBuyRequest)o;
			EqualsBuilder builder = new EqualsBuilder();
			return builder.append(itemId, obj.itemId).append(quantityToBuy, obj.quantityToBuy).isEquals();
		}
		return false;
	}
	
	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public Long getQuantityToBuy() {
		return quantityToBuy;
	}

	public void setQuantityToBuy(Long quantity) {
		this.quantityToBuy = quantity;
	}
	
	public String toString() {
		ToStringBuilder toString = new ToStringBuilder(this);
		return toString.append(itemId).append(quantityToBuy).build();
	}
	
}
