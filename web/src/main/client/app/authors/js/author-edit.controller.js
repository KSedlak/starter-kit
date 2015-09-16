angular.module('app.authors').controller('AuthorEditController', function ($scope, $modalInstance,editedAuthor) {
    'use strict';


    $scope.editedAuthor = editedAuthor;
    
    $scope.newName=editedAuthor.firstName;
    $scope.newLastName=editedAuthor.lastName;
    
    $scope.ok = function () {
    	$scope.editedAuthor.firstName= $scope.newName;
    	$scope.editedAuthor.lastName= $scope.newLastName;
        $modalInstance.close( $scope.editedAuthor );
      };
    	
    	
});
