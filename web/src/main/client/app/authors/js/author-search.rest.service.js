angular.module('app.authors').factory('authorRestService', function ($http, currentContextPath) {
    'use strict';

    return {
        findAll: function () {
            return $http.get(currentContextPath.get() + 'rest/authors/authors-list');
        }, 
        saveAuthor: function (author) {
            return $http.post(currentContextPath.get() + 'rest/authors/author', author);
        }
    
    };
});
