describe('author controller', function () {
    'use strict';

    beforeEach(function () {
        module('app.main');
        module('flash');
        module('app.author');
    });

    var $scope;
    beforeEach(inject(function ($rootScope) {
        $scope = $rootScope.$new();
    }));

    it('search is defined', inject(function ($controller) {
        // when
        $controller('AuthorSearchController', {$scope: $scope});
        // then
        expect($scope.search).toBeDefined();
    }));


});
