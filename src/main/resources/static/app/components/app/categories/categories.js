var categories = angular.module('categories', [])

.component('categories', {
	template : '<h2>Categories</h2><ng-outlet></ng-outlet>',
	$routeConfig : [ {
		path : '/',
		name : 'CategoryList',
		component : 'categoryList',
		useAsDefault : true
	} ]
})

.component('categoryList', {
	templateUrl : "app/components/app/categories/categoryList.html",
	controller : CategoryListComponent
})

function CategoryListComponent() {
	var $ctrl = this;

	this.$routerOnActivate = function(next) {
		service.getCategories().then(function(heroes) {
			$ctrl.categories = categories;
		});
	};

	this.service = function($q) {
		var heroesPromise = $q.when([ {
			id : 11,
			name : 'Requirements'
		}, {
			id : 12,
			name : 'Test'
		}, {
			id : 13,
			name : 'Refactor'
		}, {
			id : 14,
			name : 'Design'
		} ]);

		this.getCategories = function() {
			return categoriesPromise;
		};

	}

}
