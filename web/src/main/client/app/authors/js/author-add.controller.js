angular.module('app.authors').controller(
		'AuthorAddController',
		function($scope, $modalInstance) {
			'use strict';

			$scope.title = 'title';
			$scope.author = {
				id : '',
				firstName : '',
				lastName : ''
			};

			$scope.ok = function() {			
				$modalInstance.close($scope.author);
	
			};

		});
