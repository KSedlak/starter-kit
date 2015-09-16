describe('AuthorEditController', function () {
    'use strict';
  beforeEach(function () {
	        module('app.main');
	        module('flash');
	        module('app.authors');
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
      Ctrl = $controller('AuthorEditController', {
        $scope: scope,
        $modalInstance: modalInstance,
        editedAuthor:{id: 1, firstName:'Henryk', lastName:'Sienkiewicz'}
      });
    })
  );

  describe('Author Editor', function () {
	  
	    it('should instantiate the controller properly', function () {
	      expect(Ctrl).not.toBeUndefined();
	    });
	    
	    it('should ', function () {
	    	scope.newName='Adam';
	        scope.ok();
	        expect(scope.editedAuthor.firstName).toBe(scope.newName);
	        expect(modalInstance.close).toHaveBeenCalledWith(scope.editedAuthor);
	       
	      });
	  });
	});