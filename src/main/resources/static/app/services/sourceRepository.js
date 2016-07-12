app.factory('sourceRepository', function($http) {
	return {
		getSources : function() {
			var url = "/source";
			return $http.get(url);
		},
		getSource : function(id) {
			var url = "/source/" + id;
			return $http.get(url);
		},
		createSource : function(source) {
			var url = "/source";
			return $http.post(url, source);
		},
		removeSource : function(id) {
			var url = "/source/" + id;
			return $http.delete(url);
		}
	};
});
