angular.module('app.authors', ['ngRoute']).config(function ($routeProvider) {
    'use strict';
    $routeProvider.when('/authors/authors-list', {
        templateUrl: 'authors/html/author-list.html',
        controller: 'AuthorSearchController'
    });
    

$routeProvider.when('/authors/add-author', {
    templateUrl: 'authors/html/add-author.html'
});

$routeProvider.when('/authors/edit-author', {
    templateUrl: 'authors/html/edit-author.html'
});

});