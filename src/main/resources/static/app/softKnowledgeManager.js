var app = angular.module('softKnowledgeManager', []);

app.config(function($routeProvider) {
	$routeProvider.when("/", {
		templateUrl : "views/home.html"
	});
});