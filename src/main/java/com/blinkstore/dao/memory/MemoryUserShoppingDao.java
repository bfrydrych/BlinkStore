package com.blinkstore.dao.memory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.blinkstore.dao.IStockDao;
import com.blinkstore.dao.IUserShoppingDao;
import com.blinkstore.model.Item;
import com.blinkstore.model.UserShopping;
import com.blinkstore.storage.UserShoppingStorage;


@Repository
public class MemoryUserShoppingDao implements IUserShoppingDao{

	private UserShoppingStorage userShoppingStorage;
	
	private IStockDao stockDao;
	
	
	@Autowired
	public MemoryUserShoppingDao(IStockDao stockDao) {
		this.userShoppingStorage = new UserShoppingStorage();
		this.stockDao = stockDao;
	}

	public Map<Long, UserShopping> getUserShopping() {
		Map<Long, UserShopping> result = new HashMap<>();
		
		Map<Long, Long> allUserShopping = userShoppingStorage.getAll();
		for (Map.Entry<Long, Long> entry : allUserShopping.entrySet()) {
			Item item = stockDao.getItem(entry.getKey());
			result.put(item.getItemId(), new UserShopping(item.getName(), entry.getValue(), item.getItemId()));
		}
		
		return result;
	}

	public void updateShoppings(List<UserShopping> userShoppings) {
		Map<Long, Long> allUserShopping = userShoppingStorage.getAll();
		for (UserShopping userShopping : userShoppings) {
			if (userShopping.getQuantity() != 0) {
				allUserShopping.put(userShopping.getItemId(), userShopping.getQuantity());
			}
		}
	}
}
