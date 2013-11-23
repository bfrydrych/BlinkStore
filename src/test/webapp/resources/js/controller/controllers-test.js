describe('ItemController Test', function() {
  
  var $scope = null;
  var q = null;
  var factory = null;

  beforeEach(angular.mock.module('shop.controllers'));
  beforeEach(angular.mock.module('shop.factories'));
  
  beforeEach(inject(function($rootScope, $q, BuyItemsRequestFactory) {
      $scope = $rootScope.$new();
      q = $q;
      factory = BuyItemsRequestFactory;
  }));
  
  
  it('Should initialize current shoppings and available items', inject(function($controller) {
      $controller('ItemsController', {
          $scope: $scope,
          ItemsService: createWorkingService(),
          BuyItemsRequestFactory: factory
      });
      $scope.$apply();
      expect($scope.shoppings).toEqual(createShopping(0));
      expect($scope.items).toEqual(createItems(0));
      expect($scope.errorMessage).toBeUndefined();
  }));
  
  it('Should set error message when service fail', inject(function($controller) {
      $controller('ItemsController', {
          $scope: $scope,
          ItemsService: createFailingService(),
          BuyItemsRequestFactory: factory
      });
      $scope.$apply();
      expect($scope.errorMessage).toMatch('');
      expect($scope.shoppings).toBeUndefined();
      expect($scope.items).toEqual(jasmine.any(Array));
  }));
  
  it('Should buy items and actualize model', inject(function($controller) {
      $controller('ItemsController', {
          $scope: $scope,
          ItemsService: createWorkingService(),
          BuyItemsRequestFactory: factory
      });
      $scope.$apply();
      $scope.items = createItems(1);
      $scope.buyItems();
      $scope.$apply();
      expect($scope.errorMessage).toEqual('');
      expect($scope.shoppings).toEqual(createShopping(1));
      expect($scope.items).toEqual(createItems(0));
  }));
  
  it('Should contruct correct buy items request', inject(function($controller) {
	  var service = createWorkingService();
      $controller('ItemsController', {
          $scope: $scope,
          ItemsService: service,
          BuyItemsRequestFactory: factory
      });
      $scope.$apply();
      $scope.items = createItems(1);
      
      var actualRequest = null;
      spyOn(service, "buyItems").andCallFake(function (request) {
    	  actualRequest = request;
    	  return {
    		  then: function() {}
    	  };
      });
      $scope.buyItems();
      
      var expectedRequest = {
    		  itemsToBuy: [{itemId:1, quantityToBuy:1}]
      };
      
      expect(actualRequest.itemsToBuy).toEqual(expectedRequest.itemsToBuy);
  }));
  
  it('Should display error when server return error on buy items attempt', inject(function($controller) {
      $controller('ItemsController', {
          $scope: $scope,
          ItemsService: createFailingService("error"),
          BuyItemsRequestFactory: factory
      });
      $scope.$apply();
      $scope.items = createItems(1);
      $scope.buyItems();
      $scope.$apply();
      expect($scope.errorMessage).toEqual('error');
      expect($scope.shoppings).toBeUndefined();
      expect($scope.items).toEqual(jasmine.any(Array));
  }));
  
  it('Should display error when server fail on buy items attempt', inject(function($controller) {
      $controller('ItemsController', {
          $scope: $scope,
          ItemsService: createFailingService(),
          BuyItemsRequestFactory: factory
      });
      $scope.$apply();
      $scope.items = createItems(1);
      $scope.buyItems();
      $scope.$apply();
      expect($scope.errorMessage).toEqual('Unexpected service error occured. Please contact Administrator.');
      expect($scope.shoppings).toBeUndefined();
      expect($scope.items).toEqual(jasmine.any(Array));
  }));
  
  function createWorkingService() {
	  var service = {};
	  service.getShoppings = function () {
		  var deffered1 = q.defer();
		  deffered1.resolve(createShopping(0));
		  return deffered1.promise;
	  };
	  service.getAvailableItems = function () {
		  var deffered2 = q.defer();
		  deffered2.resolve(createItems(0));
		  return deffered2.promise;
	  };
	  service.buyItems = function () {
		  var fakeResponse = {};
		  fakeResponse.items = createItems(0);
		  fakeResponse.userShoppings = createShopping(1);
		  
		  var deffered = q.defer();
		  deffered.resolve(fakeResponse);
		  return deffered.promise;
	  };
	  return service;
  }
  
  function createFailingService(errorMessage) {
	  var service = {};
	  service.getShoppings = function () {
		  deffered1 = q.defer();
		  deffered1.reject();
		  return deffered1.promise;
	  };
	  service.getAvailableItems = function () {
		  deffered2 = q.defer();
		  deffered2.reject();
		  return deffered2.promise;
	  }; 
	  service.buyItems = function () {
		  var deffered = q.defer();
		  deffered.reject({
			  data: {
				  message: errorMessage || ""
			  }
		  });
		  return deffered.promise;
	  };
	  return service;
  };
  
  function createItems(quantityToBuy) {
	  var fakeItems = [];
	  fakeItems.push({
		  itemId: 1,
		  quantityToBuy: quantityToBuy,
		  name: "item1",
		  available: 10
	  });
	  return fakeItems;
  };
  
  function createShopping(quantity) {
	  return {
		itemName: "item1",
		quantity: quantity,
		itemId: 1
	  };
  }
  
});