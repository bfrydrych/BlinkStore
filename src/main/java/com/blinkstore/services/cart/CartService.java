package com.blinkstore.services.cart;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

@Service
@Scope(proxyMode=ScopedProxyMode.TARGET_CLASS, value="session")
public class CartService {

	private UserCart cart = new UserCart();
	
	public void addItem(Long itemId, Integer quantity) {
		cart.update(itemId, quantity);
	}

	public Object getCart() {
		throw new UnsupportedOperationException();
	}
}
