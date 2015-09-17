describe('book service', function() {
	'use strict';


	  beforeEach(function () {
			module('app.main');
			module('app.books');
	  	});

		var $bookService;


		 beforeEach(inject(
				    function (bookService) {   				    	
				    		$bookService = bookService;
				    })
				  );
//init tests
	it('search', inject(function() {
		// then
		expect($bookService.search).toBeDefined();
	}));
	it('deleteBook', inject(function() {
		// then
		expect($bookService.deleteBook).toBeDefined();
	}));
	it('saveBookd', inject(function() {
		// then
		expect($bookService.saveBook).toBeDefined();
	}));
	
//other tests

	it('calls bookRestService.search', inject(function($q, bookRestService) {
		// given
		var searchDeferred = $q.defer();
		var bookTitle='Lalka';
		
		var returnedBook={
				data : [
				        {id:1,
				        title:bookTitle,
				        authors:[{id:1,firstName:'Jan', lastName:'Kracy'}]
				        }
				        ]
			};
		spyOn(bookRestService, 'search').and
				.returnValue(searchDeferred.promise);
		// when
		$bookService.search(bookTitle);
		searchDeferred.resolve(returnedBook);
		// then
		expect(bookRestService.search).toHaveBeenCalled();
	}));
	
	it('calls bookRestService.save', inject(function($q, bookRestService) {
		// given
		var saveDeferred = $q.defer();
		
		var saveBook={
				data : [
				        {id:1,
				        title:'Lalka',
				        authors:[{id:1,firstName:'Jan', lastName:'Kracy'}]
				        }
				        ]
			};
		spyOn(bookRestService, 'saveBook').and
				.returnValue(saveDeferred.promise);
		// when
		$bookService.saveBook(saveBook);
		saveDeferred.resolve(saveBook);
		// then
		expect(bookRestService.saveBook).toHaveBeenCalled();
	}));
	
	it('calls bookRestService.Delete', inject(function($q, bookRestService) {
		// given
		var deleteDeferred = $q.defer();
		spyOn(bookRestService, 'deleteBook').and
				.returnValue(deleteDeferred.promise);
		// when
		$bookService.deleteBook(1);
		deleteDeferred.resolve();
		// then
		expect(bookRestService.deleteBook).toHaveBeenCalled();
	}));
	
});
