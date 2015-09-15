angular.module('app.books').controller('BookEditController', function ($scope, $modal, $window,isEdited,bookService,$modalInstance,selectedBook) {
    'use strict';
    
    $scope.title = 'title';
    var modalInstance;
  
    $scope.author= {firstName: "", lastName: ""};

    $scope.editedAuthor= {firstName: "", lastName: ""};
    
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
    	
         $scope.addedAuthors.push({
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
    			id: $scope.selectedBook.id,
				title : $scope.selectedBook.title,
				authors : $scope.addedAuthors
		};
    	

    	var result= bookService.saveBook(dataObj);
    	result.then(function(response) {
    		  $scope.selectedBook = response.data;
    		})
    	$modalInstance.close($scope.isEdited);

      };

      $scope.remove = function(author) { 
    	  var index = $scope.addedAuthors.indexOf(author);
    	  $scope.addedAuthors.splice(index, 1);     
    	}
    	

});
