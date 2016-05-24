var categories = angular.module('categories', [])

.component('categories', {
	template : '<h2>Categories</h2><ng-outlet></ng-outlet>',
	$routeConfig : [ 
	{
		path : '/',
		name : 'CategoryList',
		component : 'categoryList',
		useAsDefault : true
	},
	{
		path : '/:id',
		name : 'CategoryDetail',
		component : 'categoryDetail',
	}
	
	]
})

.component('categoryList', {
	templateUrl : "app/components/app/categories/categoryList.html",
	controller : CategoryListComponent
})

.component('categoryDetail', {
	templateUrl : "app/components/app/categories/categoryDetail.html",
	controller : CategoryDetailComponent
})


function CategoryService($q){
	var categoriesPromise = $q.when([ {
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
	
	this.getCategory = function(id) {
		return categoriesPromise.then(function(categories){
			for(var i =0; i<categories.lenght;i++) {
				if(categories[i].id == id) return categories[i];
			}
		})
	};
	
}

function CategoryListComponent(categoryService) {
	var $ctrl = this;

	this.$routerOnActivate = function(next) {
		categoryService.getCategories().then(function(categories) {
			$ctrl.categories = categories;
		});
	};

}

function CategoryDetailComponent(categoryService){
	var $ctrl = this;
	
	this.$routerOnActivate = function(next, previous){
		var id = next.params.id;
		  return categoryService.getCategory(id).then(function(categories) {
		    $ctrl.category = category;
		  });
	};
	
	this.gotoCategory = function() {
		  this.$router.navigate(['CategoryList']);
		};
}
