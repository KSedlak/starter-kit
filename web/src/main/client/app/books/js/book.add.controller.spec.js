describe('BookAddController', function () {
    'use strict';
  beforeEach(function () {
	        module('app.main');
	        module('flash');
	        module('app.books');
  });

  var Ctrl;
  var scope;
  var modalInstance;


  beforeEach(inject(
    function ($controller, $rootScope) {   
      scope = $rootScope.$new();
      modalInstance = {                  
        close: jasmine.createSpy('modalInstance.close'),
        dismiss: jasmine.createSpy('modalInstance.dismiss'),
        result: {
          then: jasmine.createSpy('modalInstance.result.then')
        }
      };
      Ctrl = $controller('BookAddController', {
        $scope: scope,
        $modalInstance: modalInstance,
        
        books:[{id:1,title:'Lalka',authors:
        	[{id:1,firstName:'Jan', lastName:'Kracy'}]
        }]

      });
    })
  );

  describe('Book Add Test', function () {
	  
	    it('should instantiate the controller properly', function () {
	      expect(Ctrl).not.toBeUndefined();
	    });
	    
	    it('save book should call bookService.saveBook', inject(function ( $q, Flash, bookService) {
	        // given
	    	 scope.addedBook={
	    		    	id: null,
	    		    	title:'Janusze Biznesu',
	    		    	authors:[{id:null,firstName:'Jan', lastName:'Janusz'}]
	    		    	
	    		    };
	        var saveDeferred = $q.defer();
	        
	        spyOn(bookService, 'saveBook').and.returnValue(saveDeferred.promise);
	        spyOn(Flash, 'create');
	
	        
	        // when
	        scope.save(scope.addedBook);
	        saveDeferred.resolve();
	        scope.$digest();
	        // then
	        expect(bookService.saveBook).toHaveBeenCalledWith(scope.addedBook);
	        expect(Flash.create).toHaveBeenCalledWith('success', 'Książka została dodana.', 'custom-class');
	        expect(scope.books.length).toBe(2);
	        expect(modalInstance.close).toHaveBeenCalledWith(scope.books);
	        
	    }));
	  });
	});