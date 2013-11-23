package com.blinkstore.controllers.validators;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.blinkstore.controllers.ItemBuyRequest;
import com.blinkstore.dao.IStockDao;
import com.blinkstore.model.Item;


@Component
public class EnoughItemsOnStockValidator implements ConstraintValidator<EnoughItemsOnStock, List<ItemBuyRequest>>{

	private IStockDao stockDao;
	
	@Autowired
	public EnoughItemsOnStockValidator(IStockDao stockDao) {
		this.stockDao = stockDao;
	}
	
	public void initialize(EnoughItemsOnStock annotation) {
		
	}

	public boolean isValid(List<ItemBuyRequest> itemsToBuy,
			ConstraintValidatorContext context) {
		
		Map<Long, Item> currentStock = stockDao.getItemsById(extractItemIdsFromRequest(itemsToBuy));
		boolean enoughItemsAvailable = validateItemsAvailability(itemsToBuy, currentStock);
		return enoughItemsAvailable;
	}
	
	private List<Long> extractItemIdsFromRequest(List<ItemBuyRequest> itemsToBuy) {
		List<Long> ids = new ArrayList<>();
		for (ItemBuyRequest itemBuyRequest : itemsToBuy) {
			ids.add(itemBuyRequest.getItemId());
		}
		return ids;
	}
	
	private boolean validateItemsAvailability(List<ItemBuyRequest> itemsToBuy, Map<Long, Item> currentStock) {
		
		for (ItemBuyRequest itemBuyRequest : itemsToBuy) {
			Item item = currentStock.get(itemBuyRequest.getItemId());
			if (item == null) return false;
			
			if (!item.isOrderRealisationPossible(itemBuyRequest)) {
				return false;
			}
		}
		return true;
	}
}
