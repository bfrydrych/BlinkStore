package com.blinkstore.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blinkstore.dao.IUserShoppingDao;
import com.blinkstore.model.UserShopping;


@Service
public class UserShoppingService {

	private IUserShoppingDao userShoppingDao;
	
	@Autowired
	public UserShoppingService(IUserShoppingDao userShoppingDao) {
		this.userShoppingDao = userShoppingDao;
	}
	
	
	public List<UserShopping> getUserShoppings() {
		List<UserShopping> userShoppings = new ArrayList(userShoppingDao.getUserShopping().values());
		Collections.sort(userShoppings, UserShopping.byNameComparator);
		return userShoppings;
	}
}
