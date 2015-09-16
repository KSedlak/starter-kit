describe('AuthorSearchController', function () {
    'use strict';

    beforeEach(function () {
        module('app.main');
        module('flash');
        module('app.authors');
    });

    var $scope;
    var modal;
    beforeEach(inject(function ($rootScope, $modal) {
        $scope = $rootScope.$new();
        modal=$modal;
    }));

    it('initByFindingAll is defined', inject(function ($controller) {
        // when
        $controller('AuthorSearchController', {$scope: $scope});
        // then
        expect($scope.initByFindingAll).toBeDefined();
    }));



});
