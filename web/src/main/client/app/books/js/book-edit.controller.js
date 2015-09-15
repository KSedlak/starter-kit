angular.module('app.books').controller('BookEditController', function ($scope, $modal, $window,bookService,$modalInstance,selectedBook,Flash) {
    'use strict';
    
    $scope.title = 'title';
    var modalInstance;
  
    $scope.author= {firstName: "", lastName: ""};

    $scope.editedAuthor= {firstName: "", lastName: ""};
    
    $scope.selectedBook=selectedBook;

    

  
    
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
    	
         $scope.selectedBook.authors.push({
         	firstName: editedAuthor.firstName,
         	lastName: editedAuthor.lastName
         });
         $scope.editedAuthor.firstName = "";
         $scope.editedAuthor.lastName = "";
       }       
       );
     
    };
    

    $scope.save = function () {

    	var dataObj = {
    			id: '',
				title : 'jas',
				authors : $scope.selectedBook.authors
		};
    	

    	var result= bookService.saveBook(dataObj);
    	result.then(function(response) {
    		  $scope.selectedBook = response.data;
          	   Flash.create('success', 'Książka została edytowana.', 'custom-class');
    		})
    	$modalInstance.close();

      };

      $scope.remove = function(author) { 
    	  var index = $scope.selectedBook.authors.indexOf(author);
    	  $scope.selectedBook.authors.splice(index, 1);     
    	}
    	

});
