angular.module('sources', [])
		.component('sources', {
			template : '<h2>Sources</h2><ng-outlet></ng-outlet>',
			$routeConfig : [ {
				path : '/',
				name : 'SourceList',
				component : 'sourceList',
				useAsDefault : true
			}, {
				path : '/:id',
				name : 'SourceDetail',
				component : 'sourceDetail'
			},
			{
				path : '/sourceForm',
				name : 'SourceForm',
				component : 'sourceForm'
			}]
		})

		.component('sourceList', {
			templateUrl : "app/components/app/sources/sourceList.html",
			controller : SourceListComponent
		})

		.component('sourceDetail', {
			templateUrl : "app/components/app/sources/sourceDetail.html",
			bindings : {
				$router : '<'
			},
			controller : SourceDetailComponent
		})
		
		.component('sourceForm', {
			templateUrl : "app/components/app/sources/sourceForm.html",
			controller : SourceFormComponent
		});


function SourceListComponent(sourceRepository) {
	var $ctrl = this;

	this.$routerOnActivate = function() {
		sourceRepository.getSources().then(function(sources) {
			$ctrl.sources = sources.data;
		});
	};

	this.isSelected = function(source) {
		return (source.sourceId == selectedId);
	};	
	
}

function SourceDetailComponent(sourceRepository) {
	var $ctrl = this;

	this.$routerOnActivate = function(next) {
		
		var sourceId = next.params.id;
		sourceRepository.getSource(sourceId).then(function(source) {
			$ctrl.source = source.data;
		});
	};

	this.gotoSources = function() {
		var sourceSId = this.source && this.source.sourceId;
		this.$router.navigate([ 'SourceList', {
			sourceId : sourceSId
		} ]);
	};
}

function SourceFormComponent(sourceRepository,categoryRepository){
	var $ctrl = this;

	this.$routerOnActivate = function() {
		
		sourceRepository.getSources().then(function(response) {
			$ctrl.sources = response.data;
		});
	
		categoryRepository.getCategories().then(function(response) {
			$ctrl.categories = response.data;
		});
		
	};

	
	this.createSource = function() {
		sourceRepository.createSource({
			"name" : this.name,
			"author" : this.author,
			"link" : this.link,
			"parentId" : this.selectedCategory.catId,
			"subId" : this.categoryChild.catId
		}).then(function(response) {
			categoryRepository.getSubCategories(this.selectedCategory.catId).then(function(response){
				$ctrl.subCat = response.data;
			});
			
			sourceRepository.getSources().then(function(response) {
				$ctrl.sources = response.data;
			});
		}, function(response) {
			alert(response.data.type + '(' + response.data.value + ')');
		});	
	};
}