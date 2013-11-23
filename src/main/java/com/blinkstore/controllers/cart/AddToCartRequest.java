package com.blinkstore.controllers.cart;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class AddToCartRequest {

	private Long itemId;
	private Integer quantity;
	
	public int hashCode() {
		HashCodeBuilder builder = new HashCodeBuilder();
		return builder.append(itemId).append(quantity).toHashCode();
	}
	
	public boolean equals(Object o) {
		if(o == null) return false;
		if (o instanceof AddToCartRequest) {
			AddToCartRequest obj = (AddToCartRequest)o;
			EqualsBuilder builder = new EqualsBuilder();
			return builder.append(itemId, obj.itemId).append(quantity, obj.quantity).isEquals();
		}
		return false;
	}
	
	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
	public String toString() {
		ToStringBuilder toString = new ToStringBuilder(this);
		return toString.append(itemId).append(quantity).build();
	}
}
