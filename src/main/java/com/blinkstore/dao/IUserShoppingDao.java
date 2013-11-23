package com.blinkstore.dao;

import java.util.List;
import java.util.Map;

import com.blinkstore.model.UserShopping;


public interface IUserShoppingDao {

	Map<Long, UserShopping> getUserShopping();

	void updateShoppings(List<UserShopping> itemBuyRequest);
}
