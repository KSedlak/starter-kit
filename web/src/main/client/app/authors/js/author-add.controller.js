angular.module('app.authors').controller(
		'AuthorAddController',
		function($scope, $modalInstance, author, $window, authorService) {
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
