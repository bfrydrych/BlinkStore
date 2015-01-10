var factories = angular.module('BlinkStore.factories', []);

factories.factory('ItemFactory', function ItemFactory() {
	return {
		newItem: function(name, available, itemId) {
			var Item = {};
			Item.name = name;
			Item.available = available;
			Item.itemId = itemId;
			Item.quantityToBuy = 0;
			return Item;
		}
	};
});

factories.factory('BuyItemsRequestFactory', function BuyItemsRequestFactory() {
	return {
		newBuyItemsRequest: function() {
			var BuyItemsRequest = {};
			BuyItemsRequest.itemsToBuy = [];
			
			BuyItemsRequest.addItemToBuy = function(itemId, quantityToBuy) {
				var itemIdAndQuantity = {};
				itemIdAndQuantity.itemId = itemId;
				itemIdAndQuantity.quantityToBuy = quantityToBuy;
				this.itemsToBuy.push(itemIdAndQuantity);
			};
			return BuyItemsRequest;
		}
	};
});

