describe('ItemsService Test', function() {
  
  var http = null;

  beforeEach(angular.mock.module('shop.services'));
  beforeEach(angular.mock.module('shop.factories'));
  
  beforeEach(inject(function($httpBackend) {
      http = $httpBackend;
  }));
  
  
  it('Should return available items', inject(function(ItemsService) {
	  ItemsService.$http = http;
	  
	  http.when('GET', '/shop/resources/api/stock').respond([{"name":"item1","itemId":1,"available":10}]);
	  
	  var actualItems = null;
	  ItemsService.getAvailableItems().then(function (data) {
		  actualItems = data;
	  });
	  http.flush();
	  
      expect(actualItems.length).toEqual(1);
      expect(actualItems[0].name).toEqual("item1");
      expect(actualItems[0].itemId).toEqual(1);
      expect(actualItems[0].available).toEqual(10);
  }));
  
  it('Should return user shoppings', inject(function(ItemsService) {
	  ItemsService.$http = http;
	  
	  http.when('GET', '/shop/resources/api/user/shopping').respond([{"itemName":"item1","quantity":2,"itemId":1}]);
	  
	  var actualShoppings = null;
	  ItemsService.getShoppings().then(function (data) {
		  actualShoppings = data;
	  });
	  http.flush();
	  
      expect(actualShoppings.length).toEqual(1);
      expect(actualShoppings[0].itemName).toEqual("item1");
      expect(actualShoppings[0].itemId).toEqual(1);
      expect(actualShoppings[0].quantity).toEqual(2);
  }));
  
  it('Should buy items and return available items and user shopping', inject(function(ItemsService) {
	  ItemsService.$http = http;
	  
	  http.when('POST', '/shop/resources/api/stock').respond({"items":[{"name":"item1","itemId":1,"available":7}],"userShoppings":[{"itemName":"item1","quantity":3,"itemId":1}]});
	  
	  var actualResponse = null;
	  ItemsService.buyItems({}).then(function (data) {
		  actualResponse = data;
	  });
	  http.flush();
	  
      expect(actualResponse.items.length).toEqual(1);
      expect(actualResponse.items[0].name).toEqual("item1");
      expect(actualResponse.items[0].itemId).toEqual(1);
      expect(actualResponse.items[0].available).toEqual(7);
      
      expect(actualResponse.userShoppings.length).toEqual(1);
      expect(actualResponse.userShoppings[0].itemName).toEqual("item1");
      expect(actualResponse.userShoppings[0].itemId).toEqual(1);
      expect(actualResponse.userShoppings[0].quantity).toEqual(3);
  }));
});