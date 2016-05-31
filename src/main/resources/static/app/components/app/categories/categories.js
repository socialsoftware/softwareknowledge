angular.module('categories', [])
		//.service('categoryRepository', categoryRepository)
		//service('categoryService', CategoryService)
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
			},
			{
				path : '/catForm',
				name : 'CategoryForm',
				component : 'categoryForm'
			}]
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
		})

		.component('categoryForm',{
			templateUrl : "app/components/app/categories/categoryForm.html",
			controller : CategoryFormComponent
		});

/*function CategoryService($q) {
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

}*/

function CategoryListComponent(categoryRepository) {
	var $ctrl = this;

	this.name = 1;
	this.catId = 1;

	this.$routerOnActivate = function() {
		categoryRepository.getCategories().then(function(categories) {
			$ctrl.categories = categories;
		});
	};

	this.isSelected = function(category) {
		return (category.catId == selectedId);
	};

	
	
	
}

function CategoryDetailComponent(categoryRepository) {
	var $ctrl = this;

	this.$routerOnActivate = function(next) {
		// Get the category identified by the route parameter
		var catId = next.params.id;
		categoryRepository.getCategory(catId).then(function(category) {
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

function CategoryFormComponent(categoryRepository){
	var $ctrl = this;
	
	this.name = $ctrl.name;
	this.catId =null;
	
	this.$routerOnActivate = function() {
		categoryRepository.getCategories().then(function(categories) {
			$ctrl.categories = categories;
		});
	}
	
	this.createCategory = function() {
		this.name = $ctrl.name;
		this.catId = null;
		//this.parentId = 
		categoryRepository.createCategory({
			"name" : this.name,
			"catId" : this.catId,
			"parentId" : this.parentId
		}).then(function(response) {
			categoryRepository.getCategories();
		}, function(response) {
			alert(response.data.type + '(' + response.data.value + ')');
		});	
	};
}