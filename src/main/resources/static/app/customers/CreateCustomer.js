'use strict';

angular.module('cmApp.customers').config(
	function($stateProvider) {
		$stateProvider.state('customer.create', {
			url: '/customers/create',
			views: {
				'': {
					templateUrl: 'app/customers/CreateCustomer.html',

					controller: function($scope, $log) {

					}
				}
			}
		})
		;
	}
)
;
