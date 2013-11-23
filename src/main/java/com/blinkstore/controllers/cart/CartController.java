package com.blinkstore.controllers.cart;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.blinkstore.services.cart.CartService;

@Controller
public class CartController {

	private static final Logger log = Logger.getLogger(CartController.class);
	
	private CartService cartService;
	
	@Autowired
	public CartController(CartService cartService) {
		this.cartService = cartService;
	}
	
	@RequestMapping(value = "/resources/api/cart", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String addToCart(@RequestBody AddToCartRequest addToCartRequest) {
		cartService.addItem(addToCartRequest.getItemId(), addToCartRequest.getQuantity());
		log.trace("Cart has been updated");
		return "OK";
	}
	
	@RequestMapping(value = "/resources/api/cart", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object getCart() {
		return cartService.getCart();
	}
}
