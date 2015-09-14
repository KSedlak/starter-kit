angular.module('app.books').controller('BookAddController', function ($scope, $modal, $window,isBookAdded,bookService,$modalInstance) {
    'use strict';
    
    $scope.title = 'title';
    var modalInstance;
    $scope.addedAuthors = [];
    $scope.author= {firstName: "", lastName: ""};
    $scope.book = {title:""};
    $scope.isBookAdded=isBookAdded;

    	
    
    
  
    
    $scope.addAuthor= function () {
    	modalInstance = $modal.open({
    	  templateUrl: 'authors/html/add-author.html',
          controller:'AuthorAddController', //This must be a referance, not a string
          		size: 'sm',
            resolve: {
            	author: function () {
                  return $scope.author;
                }
              }
     
    });
     
    	modalInstance.result.then(function (author) {
         $scope.addedAuthors.push({
         	firstName: author.firstName,
         	lastName: author.lastName
         });
         $scope.author.firstName = "";
         $scope.author.lastName = "";
       }       
       );
     
    };
    
   
    $scope.save = function () {
    	$modalInstance.close($scope.isBookAdded);

      };

      
    	

});
