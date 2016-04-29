//'use strict';
/*global angular, _*/

var portalApp = angular.module('myBus');

portalApp.factory('busServiceManager', function ($rootScope, $http, $log, $window, $cacheFactory) {

	var services = {};

	return {
		fetchAllBusServices: function () {
			$log.debug("fetching services data ...");
			$http.get('/api/v1/services')
			.success(function (data) {
				services = data;
				$rootScope.$broadcast('servicesInitComplete');
				var cache = null;
				if($cacheFactory.get($rootScope.id)){
					cache = $cacheFactory.get($rootScope.id);
				}else{
					cache = $cacheFactory($rootScope.id);
				}
				angular.forEach(services, function(service, key) {
					cache.put(service.id, service);
				})

			})
			.error(function (error) {
				$log.debug("error retrieving services");
			});
		},

		getAllData: function () {
			return services;
		},

		refreshCache: function() {

		},

		getServices: function (callback) {
			$log.debug("fetching services data ...");
			$http.get('/api/v1/services')
			.success(function (data) {
				$rootScope.$broadcast('servicesCreateComplete');
				callback(data);
			})
			.error(function (error) {
				$log.debug("error retrieving services");
			});
		},

		getAllServices: function () {
			return services;
		},

		createService : function (service, callback) {
			$http.post('/api/v1/service', service)
			.success(function (data) {
				sweetAlert("success","Bus Service has created successfully","success");
				$rootScope.$broadcast('servicesCreateComplete');
				callback(data);
			})
			.error(function (err) {
				var errorMsg = "error adding new service info. " + (err && err.error ? err.error : '');
				$log.error(errorMsg);
				alert(errorMsg);
			});
		},
		updateService: function(service,callback) {
			$http.put('/api/v1/service',service).success(function (data) {
				callback(data);
				$rootScope.$broadcast('servicesCreateComplete');
			});
		},
		deleteService: function(id) {
			 swal({
	                title: "Are you sure?",
	                text: "Are you sure you want to delete this Bus Service?",
	                type: "warning",
	                showCancelButton: true,
	                closeOnConfirm: false,
	                confirmButtonText: "Yes, delete it!",
	                confirmButtonColor: "#ec6c62"},function(){
	                	
	                	$http.delete('/api/v1/service/'+id).success(function (data) {
	        				$rootScope.$broadcast('servicesDeleteComplete');
	        				swal("Deleted!", "Bus Service was successfully deleted!", "success");
	        			}).error(function () {
	                       swal("Oops", "We couldn't connect to the server!", "error");
	                    });
	                	
	            })
			
		}
	};
});


