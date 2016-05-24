angular.module('categories', []).service('categoryService', CategoryService)
		//.service('categoryRepository', categoryRepository)

		.component('categories', {
			template : '<h2>Categories</h2><ng-outlet></ng-outlet>',
			$routeConfig : [ {
				path : '/',
				name : 'CategoryList',
				component : 'categoryList',
				useAsDefault : true
			}, {
				path : '/:id',
				name : 'CategoryDetail',
				component : 'categoryDetail'
			} ]
		})

		.component('categoryList', {
			templateUrl : "app/components/app/categories/categoryList.html",
			controller : CategoryListComponent
		})

		.component('categoryDetail', {
			templateUrl : "app/components/app/categories/categoryDetail.html",
			bindings : {
				$router : '<'
			},
			controller : CategoryDetailComponent
		});

function CategoryService($q) {
	var categoriesPromise = $q.when([ {
		catId : 11,
		name : 'Requirements'
	}, {
		catId : 12,
		name : 'Test'
	}, {
		catId : 13,
		name : 'Refactor'
	}, {
		catId : 14,
		name : 'Design'
	} ]);

	this.getCategories = function() {
		return categoriesPromise;
	};

	this.getCategory = function(catId) {
		return categoriesPromise.then(function(categories) {
			for (var i = 0; i < categories.length; i++) {
				if (categories[i].catId == catId)
					return categories[i];
			}
		});
	};

}

function CategoryListComponent(categoryService, categoryRepository) {
	var $ctrl = this;

	this.name = 1;
	this.catId = 1;

	this.$routerOnActivate = function() {
		categoryService.getCategories().then(function(categories) {
			$ctrl.categories = categories;
		});
	};

	this.isSelected = function(category) {
		return (category.catId == selectedId);
	};

	this.createCategory = function() {
		this.name = this.name + 1;
		this.catId = this.catId + 1;
		categoryRepository.createCategory({
			"name" : this.name,
			"catId" : this.catId
		}).then(function(response) {
			alert("ok")
		}, function(response) {
			alert(response.data.type + '(' + response.data.value + ')');
		});	
	}
}

function CategoryDetailComponent(categoryService) {
	var $ctrl = this;

	this.$routerOnActivate = function(next) {
		// Get the category identified by the route parameter
		var catId = next.params.id;
		categoryService.getCategory(catId).then(function(category) {
			$ctrl.category = category;
		});
	};

	this.gotoCategories = function() {
		var categoryCatId = this.category && this.category.catId;
		this.$router.navigate([ 'CategoryList', {
			catId : categoryCatId
		} ]);
	};
}