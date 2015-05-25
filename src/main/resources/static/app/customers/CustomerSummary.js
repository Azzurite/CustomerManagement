'use strict';

angular.module('cmApp.customers').config(
	function($stateProvider) {
		$stateProvider.state('customer.summary', {
			url: '/customers/:id/summary',
			views: {
				'': {
					templateUrl: 'app/customers/CustomerSummary.html',
					resolve: {},
					controller: function($scope, $log) {

					}
				}
			}
		})
		;
	}
)
;
