var app = angular.module("app", ['ngRoute']);

app.config(function($routeProvider, $locationProvider){
	$routeProvider
	.when("/", {
		templateUrl : 'templates/main.html',
		controller  : 'currentReadings'

	})	
	.when("/regionalReadings/:regionName", {
		templateUrl : 'templates/regionalReadings.html',
		controller  : 'regionalReadings'
	})
	.otherwise({
        redirectTo: 'templates/main.html'
    });
	$locationProvider.html5Mode(true);
});

