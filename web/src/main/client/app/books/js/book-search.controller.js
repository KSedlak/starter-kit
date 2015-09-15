angular.module('app.books').controller('BookSearchController', function ($scope, $window, $location, bookService, Flash, $modal) {
    'use strict';

    $scope.books = [];
    $scope.gridOptions = { data: 'books' };
    $scope.prefix = '';
    
   $scope.isBookAdded=false;
   $scope.isEdited=false;
   
   $scope.selectedBook;
   
    var removeBookById = function (bookId) {
        for (var i = 0; i < $scope.books.length; i = i + 1) {
            if ($scope.books[i].id === bookId) {
                $scope.books.splice(i, 1);
                break;
            }
        }
    };

    $scope.search = function () {
        bookService.search($scope.prefix).then(function (response) {
            angular.copy(response.data, $scope.books);
        }, function () {
            Flash.create('danger', 'Wyjątek', 'custom-class');
        });
    };

    $scope.deleteBook = function (bookId) {
        bookService.deleteBook(bookId).then(function () {
            removeBookById(bookId);
            Flash.create('success', 'Książka została usunięta.', 'custom-class');
        });
    };

    $scope.addBook = function () {
    	   var modalInstance = $modal.open({
            templateUrl: 'books/html/add-book.html',
            controller: 'BookAddController',
            size: 'lg',
            resolve: {
            	isBookAdded: function () {
                  return $scope.isBookAdded;
                }
              }
    	 
    	 });
    	 
    	 modalInstance.result.then(function (isBookAdded) {
    	         $scope.isBookAdded=isBookAdded;
    	         if(!isBookAdded){
    	        	 $window.alert('nieudalo sie');
    	         }
    	 });           
    };
    $scope.editBook = function (book) {
    	  $scope.selectedBook=book;
 	   var modalInstance = $modal.open({
         templateUrl: 'books/html/edit-book.html',
         controller: 'BookEditController',
         size: 'lg',
         resolve: {
         	isEdited: function () {
               return $scope.isEdited;
             },
             selectedBook: function () {
                 return $scope.selectedBook;
               }
           }
 	 
 	 });
 	 
 	 modalInstance.result.then(function (isEdited) {
 	         $scope.isEdited=isEdited;
 	         if(!isEdited){
 	        	 $window.alert('nieudalo sie');
 	         }
 	 });           
 };


});
