describe('authors service', function() {
	'use strict';


	  beforeEach(function () {
			module('app.main');
			module('app.authors');
	  	});

		var $authorService;


		 beforeEach(inject(
				    function (authorService) {   				    	
				    		$authorService = authorService;
				    })
				  );
//init tests
	it('FindAll', inject(function() {
		// then
		expect($authorService.findAll).toBeDefined();
	}));
	it('saveAuthor', inject(function() {
		// then
		expect($authorService.saveAuthor).toBeDefined();
	}));
	
//other tests

	it('calls authorRestService.findAll', inject(function($q, authorRestService) {
		// given
		var findDeferred = $q.defer();
		
		var returnedAuthors={
				data : [{id:1,firstName:'Jan', lastName:'Kracy'}]			  			       
			};
		spyOn(authorRestService, 'findAll').and
				.returnValue(findDeferred.promise);
		// when
		$authorService.findAll();
		findDeferred.resolve(returnedAuthors);
		// then
		expect(authorRestService.findAll).toHaveBeenCalled();
	}));
	
	it('calls authorRestService.save', inject(function($q, authorRestService) {
		// given
		var saveDeferred = $q.defer();
		
		var savedAuthor={
				data :  [{id:1,firstName:'Jan', lastName:'Kracy'}]
			};
		spyOn(authorRestService, 'saveAuthor').and
				.returnValue(saveDeferred.promise);
		// when
		$authorService.saveAuthor(savedAuthor);
		saveDeferred.resolve(savedAuthor);
		// then
		expect(authorRestService.saveAuthor).toHaveBeenCalled();
	}));
	

});
