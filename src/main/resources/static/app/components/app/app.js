
var app = angular.module('app', [ 'ngComponentRouter', 'sources', 'categories'])

.config(function($locationProvider) {
	$locationProvider.html5Mode(true);
})

.value('$routerRootComponent', 'app')

.component('app', {
	templateUrl : "app/components/app/app.html",
	$routeConfig : [ 
	                 {path : '/categories/...', name : 'Categories', component : 'categories'},
	                 {path : '/sources/...', name : 'Sources', component : 'sources'}
	               ]
});
