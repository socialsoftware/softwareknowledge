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
			return $http.post(url, category);
		},
		removeCategory : function(id) {
			var url = "/category/" + id;
			return $http.delete(url);
		},
		updateCategory : function(category){
			var url = "/category/";
			return $http.put(url,category);
		}
	};
});



