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



function CategoryListComponent(categoryRepository) {
	var $ctrl = this;

	this.$routerOnActivate = function() {
		categoryRepository.getCategories().then(function(categories) {
			$ctrl.categories = categories.data;
		});
	};

	this.isSelected = function(category) {
		return (category.catId == selectedId);
	};
	
}

function CategoryDetailComponent(categoryRepository) {
	var $ctrl = this;

	this.$routerOnActivate = function(next) {
		
		var catId = next.params.id;
		categoryRepository.getCategory(catId).then(function(category) {
			$ctrl.category = category.data;
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
	
	this.$routerOnActivate = function() {
		categoryRepository.getCategories().then(function(response) {
			$ctrl.categories = response.data;
		});
	}
	
	this.createCategory = function() {
		categoryRepository.createCategory({
			"name" : this.name,
			"parentId" : this.selectedCategory == null ? 0 : this.selectedCategory.catId
		}).then(function(response) {
			categoryRepository.getCategories().then(function(response) {
				$ctrl.categories = response.data;
			});
		}, function(response) {
			alert(response.data.type + '(' + response.data.value + ')');
		});	
	};
}