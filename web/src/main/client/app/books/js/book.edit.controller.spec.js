describe('BookEditController', function () {
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
      
      Ctrl = $controller('BookEditController', {
        $scope: scope,
        $modalInstance: modalInstance,       
      	selectedBook:{id:1,title:'Lalka',authors:
        	[{id:1,firstName:'Jan', lastName:'Kracy'}]
        }

      });
    })
  );

  describe('Book EditTest', function () {
	  
	    it('should instantiate the controller properly', function () {
	      expect(Ctrl).not.toBeUndefined();
	    });
	    
	    it('save book should call bookService.saveBook', inject(function ( $q, Flash, bookService) {
	        // given
	    	 scope.newBookTitle='Potop';
	        var saveDeferred = $q.defer();
	        
	        spyOn(bookService, 'saveBook').and.returnValue(saveDeferred.promise);
	        spyOn(Flash, 'create');
	
	        
	        // when
	        scope.save(scope.selectedBook);
	        saveDeferred.resolve();
	        scope.$digest();
	        // then
	        expect(bookService.saveBook).toHaveBeenCalledWith(scope.selectedBook);
	        expect(Flash.create).toHaveBeenCalledWith('success', 'Książka została edytowana.', 'custom-class');
	        expect(scope.selectedBook.title).toBe(scope.newBookTitle);
	        expect(modalInstance.close).toHaveBeenCalledWith();
	        
	    }));
	  });
	});