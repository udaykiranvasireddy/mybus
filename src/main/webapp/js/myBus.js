'use strict';

/* App Module */

var myBus = angular.module('myBus', [
  'ngRoute',
  'ngAnimate',
  'ngTouch',
  'mgcrea.ngStrap',
  'ngTable',
  'ui.bootstrap',
  'unsavedChanges',
  'angularSpinner', 
  'myBus.citiesModules',
  'myBus.expensesModules',
  'myBus.boardingPointModule'
  /*,
  'myBus.homeModule',
  'myBus.conditionModule',
  'myBus.procedureModule',
  'myBus.neighborhoodsModule',
  'myBus.beaconModule',
  'myBus.businessModule',
  'myBus.classificationModule',
  'myBus.apiDocsModule',
  'myBus.accountModule',
  'myBus.userModule'*/
]);


myBus.config(['$routeProvider',
  function ($routeProvider) {
    console.log("configuring routes");
    $routeProvider.
      when('/home', {
        templateUrl: 'partials/home.tpl.html',
        controller: 'HomeController'
      }).
        when('/cities', {
          templateUrl: 'partials/cities-list.tpl.html',
          controller: 'CitiesController'
        }).
        when('/expenses', {
          templateUrl: 'partials/expenses-list.tpl.html',
          controller: 'ExpensesController'
        }).
        when('/cities/:id', {
          templateUrl: 'partials/boardingpoints-list.tpl.html',
          controller: 'BoardingPointsListController'
        }).
      when('/beacons', {
        templateUrl: 'partials/beacons.tpl.html',
        controller: 'BeaconsController'
      }).
      when('/beacons/:id', {
        templateUrl: 'partials/beacon-details.tpl.html',
        controller: 'BeaconController'
      }).
      when('/beacons-new', {
        templateUrl: 'partials/beacon-details.tpl.html',
        controller: 'BeaconAddController'
      }).
      when('/businesses', {
        templateUrl: 'partials/businesses.tpl.html',
        controller: 'BusinessesController'
      }).
      when('/resolveBusinesses', {
          templateUrl: 'partials/resolveBusinesses.tpl.html',
          controller: 'BusinessesController'
      }).
      when('/businesses-missing-fields', {
        templateUrl: 'partials/businesses.tpl.html',
        controller: 'BusinessesController'
      }).
      when('/businesses/:id', {
        templateUrl: 'partials/business-details.tpl.html',
        controller: 'BusinessController'
      }).
      when('/resolveBusiness/:id', {
          templateUrl: 'partials/resolveBusiness-details.tpl.html',
          controller: 'BusinessResolveController'
      }).
      when('/businesses-new', {
        templateUrl: 'partials/business-details.tpl.html',
        controller: 'BusinessAddController'
      }).
      when('/businesses-import-google', {
        templateUrl: 'partials/businesses-import-google.tpl.html',
        controller: 'BusinessImportController'
      }).
      when('/categories', {
        templateUrl: 'partials/categories.tpl.html',
        controller: 'CategoriesController'
      }).
      when('/categories/:id', {
        templateUrl: 'partials/categoryTypes.tpl.html',
        controller: 'CategoryTypesController'
      }).
      when('/categoriessub/:categoryId/:categoryTypeId', {
        templateUrl: 'partials/categorySubTypes.tpl.html',
        controller: 'CategorySubTypesController'
      }).


      // BEGIN: rokketmed additions ===========================================
      when('/conditions', {
        templateUrl: 'partials/conditions.tpl.html',
        controller: 'ConditionsController'
      }).
      when('/conditions/:id', {
        templateUrl: 'partials/condition-details.tpl.html',
        controller: 'ConditionController'
      }).
      when('/conditions-new', {
        templateUrl: 'partials/condition-details.tpl.html',
        controller: 'ConditionAddController'
      }).

      when('/procedures', {
        templateUrl: 'partials/procedures.tpl.html',
        controller: 'ProceduresController'
      }).
      when('/procedures/:id', {
        templateUrl: 'partials/procedure-details.tpl.html',
        controller: 'ProcedureController'
      }).
      when('/procedures-new', {
        templateUrl: 'partials/procedure-details.tpl.html',
        controller: 'ProcedureAddController'
      }).
      // END: rokketmed additions =============================================

      when('/users', {
        templateUrl: 'partials/users.tpl.html',
        controller: 'UsersController'
      }).
      when('/user', {
        templateUrl: 'partials/user-details.tpl.html',
        controller: 'UserEditController'
      }).
      when('/users-new', {
        templateUrl: 'partials/user-details.tpl.html',
        controller: 'UserAddController'
      }).
      when('/docs', {
        templateUrl: 'partials/api-docs.tpl.html',
        controller: 'APIDocsController'
      }).
      when('/account', {
        templateUrl: 'partials/account.tpl.html',
        controller: 'AccountController'
      })
        .otherwise({
        redirectTo: '/'
      });
  }]);



myBus.run(function ($rootScope, $location, appConfigManager, userManager) {
  appConfigManager.fetchAppSettings(function (err, cfg) {
    $rootScope.appConfigManager = appConfigManager;
  }, true);
  userManager.getCurrentUser(function (err) {
    if (!err) {
      userManager.getGroupsForCurrentUser();
    }
  });
  $rootScope.userManager = userManager;
  $rootScope.poiSearchText = '';
  $rootScope.searchPOIs = function () {
    $location.url('/businesses?name=' + $rootScope.poiSearchText);
  };
  //categoriesManager.reloadCategoryData();
  //classificationsManager.reloadClassificationData();
  //citiesAndNeighborhoodsManager.fetchAllCityAndNeighborhoodData();
});
