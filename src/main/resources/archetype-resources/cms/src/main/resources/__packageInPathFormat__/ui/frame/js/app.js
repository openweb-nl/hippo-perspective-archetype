#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
'use strict';

// Declare app level module which depends on filters, and services
angular.module('myApp',
		[ 'myApp.filters', 'myApp.services', 'myApp.directives', 'myApp.controllers', 'ui.router', 'ngSanitize'])
	.config(['${symbol_dollar}urlRouterProvider', '${symbol_dollar}stateProvider', function(${symbol_dollar}urlRouterProvider, ${symbol_dollar}stateProvider) {
		${symbol_dollar}urlRouterProvider.otherwise('/');
		${symbol_dollar}stateProvider
			.state('home', {
				url: '/',
				templateUrl: 'templates/home.html',
				controller: 'Home'
			})
	}])
