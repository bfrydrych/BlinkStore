package com.blinkstore.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.blinkstore.model.UserShopping;
import com.blinkstore.services.UserShoppingService;



@Controller
public class UserShoppingController {
	
	private UserShoppingService userShoppingService;
	
	@Autowired
	public UserShoppingController(UserShoppingService userShoppingService) {
		this.userShoppingService = userShoppingService;
	}
	
	@RequestMapping(value="resources/api/user/shopping", method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<UserShopping> getUserShopping() {
		return userShoppingService.getUserShoppings();
	}
}
