'use strict';

angular.module('cmApp.customers', [
		'cmApp.dependencies'
	]
);

angular.module('cmApp.customers').config(
	function($stateProvider) {
		$stateProvider.state('customer', {
			template: '<ui-view></ui-view>',
			abstract: true
		});
	}
);

