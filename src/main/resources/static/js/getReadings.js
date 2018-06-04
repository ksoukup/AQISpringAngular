app.controller('regionalReadings', ['$scope', '$http', '$routeParams', 
	function regionalReadings($scope, $http, $routeParams){
	$scope.region=$routeParams.regionName;
	$scope.myOrder='time';
	var url = 'http://localhost:8088/regionalReading?regionName='+ $scope.region;
 	console.log(url);

 	$http.get(url).then(function(response){
	  		$scope.readings = response.data;
	  	});
	  
	 $scope.myOrderBy=function(x){
		 $scope.myOrder=x;
	 };
}]);

app.controller('currentReadings', ['$scope', '$http',
	function currentReadings($scope, $http){
	var url = 'http://localhost:8088/currentReadings';
	console.log(url);
 	$http.get(url).then(function(response){
  		$scope.regions = response.data;
  		console.log(response.data.length);
  	});

}]);