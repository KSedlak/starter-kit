angular.module('app.authors').factory('authorService', function (authorRestService) {
    'use strict';

    return {
        findAll: function () {
            return authorRestService.findAll();
        },
        saveAuthor: function (author) {
            return authorRestService.saveAuthor(author);
        }
    };
});