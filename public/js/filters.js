'use strict';

/* Filters */

angular.module('myApp.filters', [])
   .filter('interpolate', ['version', function(version) {
    return function(text) {
      return String(text).replace(/\%VERSION\%/mg, version);
    }
  }]);

angular.module('myApp.filters', [])
	.filter('cdate', function($filter) {
	  return function(input) {
	    return $filter('date')(input, "dd/MM/yyyy hh:mm");
	  };
});