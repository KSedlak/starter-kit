angular.module('app.books').controller('BookAddController', function ($scope, $modal, $window,bookService,$modalInstance,Flash, books) {
    'use strict';
    
    $scope.title = 'title';
    var modalInstance;
    $scope.books=books;
    $scope.author;
    $scope.addedBook={
    	id: 1,
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
         $scope.author.firstName = "";
         $scope.author.lastName = "";
         $scope.author.id = "";
       }       
       );
     
    };
    
   
    $scope.save = function () {
    	var dataObj = {
    		
				title : $scope.addedBook.title,
				authors : $scope.addedBook.authors
		};
   
    	var result= bookService.saveBook(dataObj);
    	result.then(function(response) {
    		  Flash.create('success', 'Książka została dodana.', 'custom-class');
    		  var readUpdated =bookService.search('');
    		  readUpdated.then(function(response) {
    			$scope.books=response.data;
    			  $modalInstance.close($scope.books);
    		  });
      });
    };
      
    	

});
