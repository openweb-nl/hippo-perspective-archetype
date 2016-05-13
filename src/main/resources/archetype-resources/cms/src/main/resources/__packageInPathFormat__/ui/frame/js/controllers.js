#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
'use strict';

/* Controllers */

angular.module('myApp.controllers', [])
    .controller('Home', ['${symbol_dollar}scope', '${symbol_dollar}location', '${symbol_dollar}http', function(${symbol_dollar}scope, ${symbol_dollar}location, ${symbol_dollar}http) {
    	${symbol_dollar}http.get('../rest/api/greeting').
    	success(function(data, status, headers, config) {
    		${symbol_dollar}scope.greetings = data;
    	}).error(function(data, status, headers, config) {
    		if (status == 401) {
    			${symbol_dollar}scope.greetings = data.message;
    		}
    	});	
    }])
;
