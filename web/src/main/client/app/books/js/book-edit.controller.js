angular.module('app.books').controller('BookEditController', function ($scope, $modal, $window,isEdited,bookService,$modalInstance,selectedBook) {
    'use strict';
    
    $scope.title = 'title';
    var modalInstance;
  
    $scope.author= {firstName: "", lastName: ""};

    $scope.isEdited=isEdited;
    $scope.selectedBook=selectedBook;
    $scope.addedAuthors = selectedBook.authors;
    
    
  
    
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
    	$modalInstance.close();

      };

      
    	

});
