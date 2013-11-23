var module = angular.module('shop.controllers', []);
module.controller('ItemsController', function ItemsController($scope, ItemsService, BuyItemsRequestFactory) {

	$scope.items = [];
	
	onStartup();
	
	$scope.buyItems = function() {
		$scope.errorMessage = "";
		ItemsService.buyItems(createItemsBuyRequest()).then(function (response) {
			$scope.items = response.items;
			$scope.shoppings = response.userShoppings;
		}, function (errorResponse) {
			if (errorResponse.data.message) {
				$scope.errorMessage = errorResponse.data.message;
			} else {
				handleFatalServerError();
			}
		});
	};
	
	$scope.addToCart = function(item) {
		ItemsService.addToCart(item.itemId, item.quantityToBuy).then(function() {}, handleFatalServerError);
	};
	
    function onStartup() {
    	ItemsService.getShoppings().then(function (shoppings) {
    		$scope.shoppings = shoppings;
    	}, handleFatalServerError);
    	
    	
    	ItemsService.getAvailableItems().then(function (items) {
    		$scope.items = items;
    	}, handleFatalServerError);
    }
    
	function createItemsBuyRequest() {
		var buyItemsRequest = BuyItemsRequestFactory.newBuyItemsRequest();
		$scope.items.forEach(function (item, index) {
			buyItemsRequest.addItemToBuy(item.itemId, item.quantityToBuy);
		});
		return buyItemsRequest;
	}
	
	function handleFatalServerError() {
		$scope.errorMessage = "Unexpected service error occured. Please contact Administrator.";
	}
});
