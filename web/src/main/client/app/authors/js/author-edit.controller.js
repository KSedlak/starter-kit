angular.module('app.authors').controller('AuthorEditController', function ($scope, $modalInstance,editedAuthor) {
    'use strict';


    $scope.editedAuthor = editedAuthor;
    
 
    
    $scope.ok = function () {

        $modalInstance.close( $scope.editAuthor );
      };
    	
    	
});
