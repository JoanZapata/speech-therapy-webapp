'use strict';

// Declare app level module which depends on filters, and services
angular.module('myApp', ['ngCookies', 'ngResource', 'myApp.filters', 'myApp.services', 'myApp.directives']).
  factory('Activities', function($resource, $cookies){
    return $resource('/activities/:id', {id: '', key: $cookies.key}, 
      { 
          'update' : { method:'PUT'},
          'save' : { method: 'POST' }
      });
  }).
  config(['$routeProvider', function($routeProvider) {
    $routeProvider.when('/', {templateUrl: 'partials/login.html', controller: LoginCtrl});
    $routeProvider.when('/list', {templateUrl: 'partials/list.html', controller: ListCtrl});
    $routeProvider.when('/list/:activity', {templateUrl: 'partials/activity.html', controller: ActivityCtrl});
    $routeProvider.otherwise({redirectTo: '/'});
  }]);