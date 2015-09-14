angular.module('app.authors').controller('AuthorAddController', function ($scope, $modalInstance, author) {
    'use strict';

    $scope.title = 'title';
    $scope.author = author;
    
 
    
    $scope.ok = function () {

        $modalInstance.close();
      };
    	
    	
});
