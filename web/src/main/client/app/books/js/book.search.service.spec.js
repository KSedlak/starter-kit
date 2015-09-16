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
		spyOn(bookRestService, 'search').and
				.returnValue(searchDeferred.promise);
		// when
		$bookService.search('Pierwsza');
		searchDeferred.resolve({
			data : null
		});
		// then
		expect(bookRestService.search).toHaveBeenCalled();
	}));
	
});
