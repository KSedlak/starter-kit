angular.module('app.books').controller('BookSearchController', function ($scope, $window, $location, bookService, Flash, $modal) {
    'use strict';

    $scope.books = [];
    $scope.gridOptions = { data: 'books' };
    $scope.prefix = '';
    
   $scope.isBookAdded=false;
   $scope.isEdited=false;
   
   $scope.selectedBook={id:'', title:'', authors:''};
   $scope.addedBook={id:'', title:'', authors:''};
   
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
           },
           books:function(){
        	   return $scope.books;
           }
         }
	 
	 });   
	   
	   modalInstance.result.then(function (books) {
		      $scope.books= books;
});
    };  
    $scope.editBook = function (book) {
    	  $scope.selectedBook=book;
    	  $modal.open({
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
 };
 


});


//Dodany test dla search wg polecania ma byc tu a nie w *.spec.js


describe('book controller', function () {
    'use strict';

    beforeEach(function () {
        module('app.main');
        module('flash');
        module('app.books');
    });

    var $scope;
    beforeEach(inject(function ($rootScope) {
        $scope = $rootScope.$new();
    }));

    it('search is defined', inject(function ($controller) {
        // when
        $controller('BookSearchController', {$scope: $scope});
        // then
        expect($scope.search).toBeDefined();
    }));

    it(' bookService.searchBook', inject(function ($controller, $q, bookService) {
        // given
    	
    	$scope.books=[
    	               {id:1,title:'Pierwsza książka',authors:
        	[{id:1,firstName:'Jan', lastName:'Kracy'}]}, 
        	
            {id:2,title:'Druga książka',authors:
            	[{id:1,firstName:'Jan', lastName:'Kracy'}]}
        	
    	               ];
    	
    	var bookResult=	{data: //add data: to simulation result;
    		[{id:1,title:'Pierwsza książka',authors:
        	[{id:1,firstName:'Jan', lastName:'Kracy'}]}]};
    	
    	
        $controller('BookSearchController', {$scope: $scope});
        var searchPrefix='Pierwsza';

        var searchDeferred = $q.defer();
        spyOn(bookService, 'search').and.returnValue(searchDeferred.promise);

        // when
        $scope.prefix=searchPrefix;
        $scope.search();
        searchDeferred.resolve(bookResult);
    
        $scope.$digest();
        // then
        expect(bookService.search).toHaveBeenCalledWith($scope.prefix);
        expect($scope.books.length).toBe(1);
        expect($scope.books[0].title).toBe('Pierwsza książka');
    }));
});

