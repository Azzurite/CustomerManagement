'use strict';

angular.module('cmApp.login', [
		'cmApp.dependencies',
		'cmApp.security'
	]
);

angular.module('cmApp.login').config(
	function($stateProvider) {
		$stateProvider.state('login', {
			url: '/login',
			templateUrl: 'app/login/login.html',
			controller: 'LoginController'
		});
	}
);

angular.module('cmApp.login').controller('LoginController',
	function($scope, $http, $state, growl, Session) {
		$scope.login = function(username, password) {
			Session.login(username, password).then(
				function() {
					$state.go('home');
				},
				function(account) {
					if (account) {
						growl.info('Already logged in as ' + account.name);
					} else {
						growl.error('Username or password wrong.');
					}
				}
			);
		};
	}
);
