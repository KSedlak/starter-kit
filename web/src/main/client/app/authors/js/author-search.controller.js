angular.module('app.authors').controller('AuthorSearchController', function ($scope, $window, $location, authorService, Flash, $modal) {
    'use strict';
    
    
    $scope.authors=[];
    
    $scope.gridOptions = { data: 'authors' };


   

    $scope.initByFindingAll= function () {
        authorService.findAll().then(function (response) {
            angular.copy(response.data, $scope.authors);
        }, function () {
            Flash.create('danger', 'Wyjątek', 'custom-class');
        });
    };

   
    $scope.addAuthor= function () {
        $modal.open({
            templateUrl: 'authors/html/add-author.html',
            controller: 'AuthorAddController',
            size: 'lg'
        });
    };

});
