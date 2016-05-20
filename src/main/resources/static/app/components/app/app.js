angular.module('app', [ 'ngComponentRouter', 'categories' ])

.config(function($locationProvider) {
	$locationProvider.html5Mode(true);
})

.value('$routerRootComponent', 'app')

.component('app', {
	templateUrl : "app/components/app/app.html",
	$routeConfig : [ {
		path : '/categories/...',
		name : 'Categories',
		component : 'categories',
		useAsDefault : true
	}]
});
