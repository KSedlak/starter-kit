angular.module('app.books').controller('BookAddController', function ($scope, $modal, $window,bookService,$modalInstance,Flash, books) {
    'use strict';
    
    $scope.title = 'title';
    var modalInstance;
   
    $scope.books=books;
    $scope.author= {id:'', firstName: '', lastName: ''};
    $scope.addedBook={
    	id: null,
    	title:'',
    	authors:[]
    	
    };

    
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
    		$scope.author=author;

    		$scope.addedBook.authors.push({
    	  		id: $scope.author.id,
         	firstName: $scope.author.firstName,
         	lastName: $scope.author.lastName
   
         });
         $scope.author.firstName = '';
         $scope.author.lastName = '';
         $scope.author.id = '';
       }       
       );
     
    };
    
   
    $scope.save = function () {
 
    	var result= bookService.saveBook($scope.addedBook);
    	result.then(function() {
    		  Flash.create('success', 'Książka została dodana.', 'custom-class');
    		  $scope.books.push($scope.addedBook);
    			  $modalInstance.close($scope.books);
    		 
      });
    };
      
    	

});
