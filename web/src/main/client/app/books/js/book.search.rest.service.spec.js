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

//Other tests
	
	
	it('should search book', function(){
		var query='Lalk';
		var ret={
				data : [
				        {id:1,
				        title:'Lalka',
				        authors:[{id:1,firstName:'Jan', lastName:'Kracy'}]
				        }
				        ]
			};

        httpBackend.expectGET(context + 'rest/books/books-by-title'+
        		'?titlePrefix='+query      
        )  
        .respond(ret);
        var deferredResponse = $bookRestService.search(query);
        var bookReturned;
        deferredResponse.then(function(response){
        	bookReturned = response.data;
        });

        httpBackend.flush();

        expect(bookReturned).toEqual(ret);
    });
	
	it('should save book', function(){
		var bookToSave={
					data:	[
				        {
				        id:1,
				        title:'Lalka',
				        authors:[{id:1,firstName:'Jan', lastName:'Kracy'}]
				        }
				        ]
			};

        httpBackend.expectPOST(context + 'rest/books/book',bookToSave) .respond(bookToSave);
        var deferredResponse = $bookRestService.saveBook(bookToSave);
        var bookReturned;
        deferredResponse.then(function(response){
        	bookReturned = response.data;
        });

        httpBackend.flush();

        expect(bookReturned).toEqual(bookToSave);
    });
	
	it('should delete book', function(){
		var idToDelete=1;
        httpBackend.expectDELETE(context + 'rest/books/book/'+idToDelete) .respond(200, 'OK');
        var deferredResponse = $bookRestService.deleteBook(idToDelete);
        var deleteResponse;
        deferredResponse.then(function(response){
        	deleteResponse = response;
        });
        

        httpBackend.flush();

        expect(deleteResponse.status).toBe(200);
    });
	
	
});