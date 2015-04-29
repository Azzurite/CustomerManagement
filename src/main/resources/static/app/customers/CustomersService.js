'use strict';

angular.module('cmApp.customers').service('CustomersService',
	function($resource) {
		return $resource('/rest/customers/:customer', null, {

		});
	}
);
