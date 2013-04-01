'use strict';

function LoginCtrl($scope, $location, $cookies) {
	$scope.key = $cookies.key;
	$scope.submit = function(){
		$cookies.key = $scope.key;
		$location.path( "/list" );
	}
}

function ListCtrl($scope, $location, $cookies, Activities, $http) {
	var modal = $("#newActModal");
	$scope.key = $cookies.key;
	$scope.activities = Activities.query();
	$scope.currentActivity = { resources: [], type: "syllabe2image" };	
	$scope.click = function(activity) {
		$location.path("/list/"+activity.id);
	}
	$scope.delete = function(activity) {
		activity.$delete({id: activity.id});
	}
	$scope.add = function() {
		modal.modal("show");
	}
	$scope.addCurrentActivity = function() {
		Activities.save($scope.currentActivity, function(){
			$scope.activities = Activities.query();
		});
		modal.modal('hide');
	}
}

function ActivityCtrl($location, $http, $scope, $routeParams, $cookies, Activities){
	var modal = $("#newWordModal");
	$scope.activity = Activities.get({id: $routeParams.activity});

	$scope.back = function(){
		$location.path("/list");
	}

	$scope.addPage = function() {
		$scope.currentPage = {words: [
			{image: "", text: ""},
			{image: "", text: ""},
			{image: "", text: ""},
			{image: "", text: ""}
			]};
			modal.modal("show");
		}

		var isCorrectAnswerRegex = /.*\*.*\*.*/;
		$scope.isCorrectAnswer = function(text){
			return text.match(isCorrectAnswerRegex) != null;
		}

		$scope.delete = function(part) {
			$scope.activity.pages.splice($.inArray(part, $scope.activity.pages), 1);
			Activities.update({id: $scope.activity.id}, $scope.activity);
		}

		$scope.savePage = function() {
			if ($scope.activity.resources == undefined){
				$scope.activity.resources = [];
			}
			if ($scope.activity.pages == undefined){
				$scope.activity.pages = [];
			}
			for (var i=0; i < $scope.currentPage.words.length; i++){
				$scope.activity.resources.push($scope.currentPage.words[i].image);
			}
			$scope.activity.pages.push($scope.currentPage);
			Activities.update({id: $scope.activity.id}, $scope.activity);
		}

	}

