package com.blinkstore.controllers;

import java.util.List;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.blinkstore.model.Item;
import com.blinkstore.model.UserShopping;
import com.blinkstore.services.StockService;
import com.blinkstore.services.UserShoppingService;


@Controller
public class StockController {

	private StockService stockService;
	private UserShoppingService userShoppingService;
	
	private Logger log = Logger.getLogger(StockController.class);
	
	@Autowired
	public StockController(StockService stockService, UserShoppingService userShoppingService) {
		this.stockService = stockService;
		this.userShoppingService = userShoppingService;
	}
	
	
	@RequestMapping(value = "/resources/api/stock", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Item> getStock() {
		return stockService.getCurrentStock();
	}
	
	@RequestMapping(value = "/resources/api/stock", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ItemBuyResponse buyItems(@Valid @RequestBody ItemsBuyRequest itemsToBuy) {
		if (itemsToBuy != null && itemsToBuy.getItemsToBuy() != null) {
			stockService.buyItems(itemsToBuy);
		} else {
			log.error("Request was null");
		}
		List<UserShopping> userShoppings = userShoppingService.getUserShoppings();
		List<Item> currentStock = stockService.getCurrentStock();
		return new ItemBuyResponse(currentStock, userShoppings);
	}
}

