describe('book rest service', function() {
	'use strict';	
		  beforeEach(function () {
				module('app.main');
				module('app.books');
		  	});

var $bookRestService;
var httpBackend;
var context;


			 beforeEach(inject(
					    function (bookService, bookRestService,$httpBackend, currentContextPath) {   				    	
					    	$bookRestService = bookRestService;
							httpBackend = $httpBackend;
							context = currentContextPath.get();
					    })
					  );
			 
	//init tests
	it('search', inject(function() {
		// then
		expect($bookRestService.search).toBeDefined();
	}));
	it('deleteBook', inject(function() {
		// then
		expect($bookRestService.deleteBook).toBeDefined();
	}));
	it('saveBook', inject(function() {
		// then
		expect($bookRestService.saveBook).toBeDefined();
	}));

	//other tests
/*	it('bookRestService.saveBook', inject(function($rootScope, $controller) {
		// given
	    $scope = $rootScope.$new();
		$controller('BookSearchController', {
		      $scope: $scope
		    });
		var book=
		{id:null,title:'Lalka',authors:
        	[{id:null,firstName:'Jan', lastName:'Kracy'}]
        };
		// when
		httpBackend.expect('POST', context + 'rest/books/book', book).respond({
			data : [{id:1,title:'Lalka',authors:
	        	[{id:1,firstName:'Jan', lastName:'Kracy'}]}]
		});
		$bookRestService.saveBook(book);
		// then
	
		httpBackend.flush();
		$scope.search('');//refresh books
	    expect($scope.books.length).toBe(1);
		
	}));*/
	
	
	
	it('should ', function(){
		var ret={
				data : [
				        {id:1,
				        title:'Lalka',
				        authors:[{id:1,firstName:'Jan', lastName:'Kracy'}]
				        }
				        ]
			};
		
        httpBackend.expect('GET',context + 'rest/books/books-by-title?titlePrefix=Lalka').respond(ret);
        var deferredResponse = $bookRestService.search('Lalka');
        var bookReturned;
        deferredResponse.then(function(response){
        	bookReturned = response.data;
        });

        httpBackend.flush();

        expect(bookReturned).toEqual(ret);
    });
	
});