describe('author rest service', function() {
	'use strict';	
		  beforeEach(function () {
				module('app.main');
				module('app.authors');
		  	});

var $authorRestService;
var httpBackend;
var context;


			 beforeEach(inject(
					    function (authorService, authorRestService,$httpBackend, currentContextPath) {   				    	
					    	$authorRestService = authorRestService;
							httpBackend = $httpBackend;
							context = currentContextPath.get();
					    })
					  );
			 
	//init tests
	it('findAll', inject(function() {
		// then
		expect($authorRestService.findAll).toBeDefined();
	}));
	it('saveAuthor', inject(function() {
		// then
		expect($authorRestService.saveAuthor).toBeDefined();
	}));

//Other tests
	
	
	it('should findAll Authors', function(){
		var ret={
				data : [
				        {id:1,firstName:'Jan', lastName:'Kracy'}
				        ]
			};

        httpBackend.expectGET(context + 'rest/authors/authors-list')  
        .respond(ret);
        var deferredResponse = $authorRestService.findAll();
        var authorsReturned;
        deferredResponse.then(function(response){
        	authorsReturned = response.data;
        });

        httpBackend.flush();

        expect(authorsReturned).toEqual(ret);
    });
	
	it('should save author', function(){
		var authorToSave={
					data: [{id:1,firstName:'Jan', lastName:'Kracy'}]};

        httpBackend.expectPOST(context + 'rest/authors/author',authorToSave) .respond(authorToSave);
        var deferredResponse = $authorRestService.saveAuthor(authorToSave);
        var authorReturned;
        deferredResponse.then(function(response){
        	authorReturned = response.data;
        });

        httpBackend.flush();

        expect(authorReturned).toEqual(authorToSave);
    });
	
	
	
});