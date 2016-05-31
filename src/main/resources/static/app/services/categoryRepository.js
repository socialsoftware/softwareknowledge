app.factory('categoryRepository', function($http) {
	return {
		getCategories : function() {
			var url = "/category";
			return $http.get(url);
		},
		getCategory : function(id) {
			var url = "/category/" + id;
			return $http.get(url);
		},
		createCategory : function(category) {
			var url = "/category";
			$http.post(url, category);
		}
	};
});



