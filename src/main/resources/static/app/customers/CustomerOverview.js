'use strict';

angular.module('cmApp.customers').config(
	function($stateProvider) {
		$stateProvider.state('customer.overview', {
			url: '/customers',
			views: {
				'navsub@': {
					templateUrl: 'app/customers/CustomerOverview-navsub.html'
				},

				'': {
					templateUrl: 'app/customers/CustomerOverview.html',
					resolve: {
						customers: function(CustomersService, $log) {
							return CustomersService.query(function(data) {
									$log.debug('Found customers: ', data);
								},
								function(data, headers) {
									$log.error('Error while retrieving customer list: ', data, headers);
								});
						}
					},
					controller: function($scope, $log, CustomersService, $state, customers) {
						$scope.customers = customers;

						$scope.newCustomer = {};

						$scope.filterOptions = {
							'Name': [
								'firstName',
								'lastName'
							],
							'Status': 'relationshipStatus',
							'Task': 'currentTask',
							'Type': 'type'
						};

						$scope.filteredCustomers = [{uniqueName: 'test'}];
						$scope.sortedCustomers = [];

						$scope.createCustomer = function(customer) {
							$log.debug('Creating customer: ', customer);
							CustomersService.save(customer,
								function(data) {
									$log.debug('Customer saved: ', data);
									if (data.$status === 201) {
										$scope.customers.push(data);
									}
									$scope.newCustomer = {};
								},
								function(error) {
									$log.debug('Nay...', error);
								}
							);
						};

						$scope.deleteCustomer = function(customer) {
							CustomersService.delete(
								{customer: customer.uniqueName},
								function() {
									var idx = $scope.customers.indexOf(customer);
									$scope.customers.splice(idx, 1);
								},
								function(data, status) {
									$log.error('error deleting customer ', customer, status);
								}
							);
						};
					}
				}
			}
		})
		;
	}
)
;
