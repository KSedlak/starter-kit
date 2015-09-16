describe('AuthorAddController', function () {
    'use strict';
  beforeEach(function () {
	        module('app.main');
	        module('flash');
	        module('app.authors');
  });

  var Ctrl;
  var scope;
  var modalInstance;

  // Initialize the controller and a mock scope
  beforeEach(inject(
    function ($controller, $rootScope) {     // Don't bother injecting a 'real' modal
      scope = $rootScope.$new();
      modalInstance = {                    // Create a mock object using spies
        close: jasmine.createSpy('modalInstance.close'),
        dismiss: jasmine.createSpy('modalInstance.dismiss'),
        result: {
          then: jasmine.createSpy('modalInstance.result.then')
        }
      };
      Ctrl = $controller('AuthorAddController', {
        $scope: scope,
        $modalInstance: modalInstance,

      });
    })
  );

  describe('Initial state', function () {
	  
	    it('should instantiate the controller properly', function () {
	      expect(Ctrl).not.toBeUndefined();
	    });
	    
	    it('should close the modal and return author', function () {
	        scope.ok();
	        expect(modalInstance.close).toHaveBeenCalledWith(scope.author);
	      });
	  });
	});