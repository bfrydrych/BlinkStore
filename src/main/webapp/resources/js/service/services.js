var services = angular.module('shop.services', []);
services.service('ItemsService', function ($http, ItemFactory) {
	this.getShoppings = function() {
		var promise = $http.get('/shop/resources/api/user/shopping').then(function(shoppingsFromServer) {
    		return shoppingsFromServer.data;
    	});
		return promise;
	};
	
	this.getAvailableItems = function() {
		var promise = $http.get('/shop/resources/api/stock').then(function(stockDataFromServer) {
    		return convertToItems(stockDataFromServer.data);
    	});
		return promise;
	};
	
	this.buyItems = function(itemsBuyRequest) {
		var promise = $http.post('/shop/resources/api/stock', itemsBuyRequest)
    	.then(function(response) {
    		var convertedResponse = {};
    		var items = convertToItems(response.data.items);
    		convertedResponse.items = items;
    		convertedResponse.userShoppings = response.data.userShoppings;
    		return convertedResponse;
    	}); 
		return promise;
	};
	
	this.addToCart = function(itemIdToAdd, quantityToAdd) {
		var promise = $http.post('/shop/resources/api/cart',  {
			itemId: itemIdToAdd,
			quantity: quantityToAdd
		});
		return promise;
	};
	
	this.getCart = function() {
		var promise = $http.get('/shop/resources/api/cart').then(function(response) {return response.data;});
		return promise;
	};
	
	function convertToItems(stockDataFromServer) {
		var items = [];
		stockDataFromServer.forEach(function(stockData, index) {
			items.push(ItemFactory.newItem(stockData.name, stockData.available, stockData.itemId));
		});
    	return items;
	}
});
