'use strict';
/**
 * @ngdoc module
 * @name cmApp.login
 *
 * @description
 * Frontend controller & services for logging in
 */
angular.module('cmApp.login', [
		'cmApp.dependencies',
		'cmApp.security'
	]
);

/**
 * @ngdoc state
 * @name login
 *
 * @description
 * State for the login page
 */
angular.module('cmApp.login').config(
	function($stateProvider) {
		$stateProvider.state('login', {
			url: '/login',
			templateUrl: 'app/login/login.html',
			controller: 'LoginController'
		});
	}
);

/**
 * @ngdoc controller
 * @name LoginController
 *
 * @description
 * Provides a login function for the frontend
 */
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
