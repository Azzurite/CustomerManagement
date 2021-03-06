'use strict';

angular.module('cmApp.dependencies', [
	'azUtils',
	'azData',
	'azResourceAddStatus',
	'ngCookies',
	'ngResource',
	'ui.router',
	'angular-growl',
	'angular-cache'
]);

angular.module('cmApp', [
		'cmApp.dependencies',
		'cmApp.security',
		'cmApp.login',
		'cmApp.customers'
	]
).config(
	function($stateProvider, $urlRouterProvider, $locationProvider, growlProvider, $compileProvider) {
		//$compileProvider.debugInfoEnabled(false);
		$locationProvider.html5Mode({
			enabled: true
		});

		$urlRouterProvider.otherwise('/');

		$stateProvider.state('home', {
			url: '/',
			templateUrl: 'app/home/home.html'
		});

		growlProvider.globalPosition('top-center');
		growlProvider.globalTimeToLive(6000);
	}
).run(
	function(Session, $state, $location, $rootScope, $log) {
		$log.debug('Application start');


	}
);
