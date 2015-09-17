angular.module('app.books').controller('BookEditController', function ($scope, $modal, $window,bookService,$modalInstance,selectedBook,Flash) {
    'use strict';
    
    $scope.title = 'title';
    var modalInstance;

    $scope.author= {id:'', firstName: '', lastName: ''};

    $scope.editedAuthor= {id:'', firstName: '', lastName: ''};
    
    $scope.selectedBook=selectedBook;
    $scope.newBookTitle=selectedBook.title;
    

  
    
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
         $scope.selectedBook.authors.push({
         	firstName: author.firstName,
         	lastName: author.lastName
         });
         $scope.author.firstName = '';
         $scope.author.lastName = '';
       }       
       );
     
    };
    $scope.remove = function (authorId) {
        for (var i = 0; i < $scope.selectedBook.authors.length; i = i + 1) {
            if ($scope.selectedBook.authors[i].id === authorId) {
                $scope.selectedBook.authors.splice(i, 1);
                break;
            }
        }
    };
  	 
  
    $scope.editAuthor= function (editedAuthor) {
    	
    	$scope.editedAuthor=editedAuthor;
    
    	modalInstance = $modal.open({
    	  templateUrl: 'authors/html/edit-author.html',
          controller:'AuthorEditController',
          		size: 'sm',
            resolve: {
            	editedAuthor: function () {
                  return $scope.editedAuthor;
                }
              }
     
    });
     
    	modalInstance.result.then(function (editedAuthor) {
    		$scope.editedAuthor=editedAuthor;
       }       
       );
     
    };
    

    $scope.save = function () {
    	$scope.selectedBook.title=$scope.newBookTitle;
    	var result= bookService.saveBook($scope.selectedBook);
    	result.then(function() {
          	Flash.create('success', 'Książka została edytowana.', 'custom-class');
           	$modalInstance.close();
    		});


      };

      
    	

});
