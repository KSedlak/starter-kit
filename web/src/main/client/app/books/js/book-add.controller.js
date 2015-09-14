angular.module('app.books').controller('BookAddController', function ($scope, $modal) {
    'use strict';

    $scope.title = 'title';
    
    
    
    $scope.addAuthor= function () {
    var modalInstance = $modal.open({
    	  templateUrl: 'authors/html/add-author.html',
          controller:'AuthorAddController', //This must be a referance, not a string
        size: 'sm'

    });
}

});
