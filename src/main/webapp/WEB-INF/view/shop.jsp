<!doctype html>
<html ng-app="BlinkStore">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="resources/twitterbootstrap/css/bootstrap.css" rel="stylesheet" >
    <link href="resources/twitterbootstrap/css/bootstrap-theme.css" rel="stylesheet" >
    <link href="resources/css/page.css" rel="stylesheet" >
	<!-- script type="text/javascript" src="https://getfirebug.com/firebug-lite.js"></script-->
  	<script src="resources/js/jquery-2.0.3.min.js"></script>
  	<script type="text/javascript" src="resources/js/lib/angular.min.js"></script>
  	<script type="text/javascript" src="resources/js/model/model.js"></script>
  	<script type="text/javascript" src="resources/js/init/init.js"></script>
  	<script type="text/javascript" src="resources/js/controller/controllers.js"></script>
  	<script type="text/javascript" src="resources/js/service/services.js"></script>
    <script src="resources/twitterbootstrap/js/bootstrap.js"></script>

	<title>Online Store</title>
</head>
<body ng-controller="ItemsController">
	<div class="pageTitle">
		<h2>Online Store</h2>
	</div>
	<div class="pageBlock">
	    <div ng-include="'/BlinkStore/resources/view/stockAvailability.html'"></div>
	    <br>
	    <div ng-include="'/BlinkStore/resources/view/cart.html'"></div>
	    <br>
	    <div ng-include="'/BlinkStore/resources/view/buy.html'"></div>
    </div>
</body>
</html>