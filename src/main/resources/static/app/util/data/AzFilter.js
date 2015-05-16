'use strict';

angular.module('azData').directive('azFilter', function($compile) {
	return {
		template: '<h2>Filters</h2><div></div><button type="button" class="btn btn-default" ng-click="addFilter();">Add filter</button>',
		scope: {
			toFilter: '=filter',
			output: '=',
			filterOptions: '='
		},
		link: function(scope, elem, attrs) {
			var filterContainer = angular.element(elem.children()[1]);
			scope.options = Object.keys(scope.filterOptions);
			scope.addFilter = function() {
				var newFilter = $compile('<az-filter-element options="options"></az-filter-element>')(scope, null,
					{futureParentElement: elem});
				filterContainer.append(newFilter);
			};
		},
		controller: function($scope, $element, $attrs, azUtils, $filter) {
			var self = this;


			angular.forEach($scope.filterOptions, function(value, key) {
				if (!angular.isArray(value)) {
					$scope.filterOptions[key] = [value];
				}
			});

			$scope.$watchCollection('toFilter', function() {
				filter();
			});

			var filters = {};

			self.register = function(filterId, filterProperty, filterValue) {
				filters[filterId] = {
					option: filterProperty,
					value: filterValue
				};
				filter();
			};

			self.unregister = function(filterId) {
				delete filters[filterId];
				filter();
			};


			function multiPropertyContains(value, object, properties) {
				var matched = false;
				angular.forEach(properties, function(property) {
					var objectProperty = (object[property] || '').toLocaleLowerCase();
					var filterValue = (value || '').toLocaleLowerCase();
					if (objectProperty.indexOf(filterValue) > -1) {
						matched = true;
					}
				});
				return matched;
			}

			function filter() {
				$scope.output = $scope.toFilter;
				angular.forEach(filters, function(filter) {
					$scope.output = $filter('filter')($scope.output, function(elem) {
						return multiPropertyContains(filter.value, elem, $scope.filterOptions[filter.option]);
					});
				});
			}
		}

	};
});


angular.module('azData').directive('azFilterElement', function(azUtils) {
	return {
		templateUrl: 'app/util/data/filterElement.html',
		require: '^azFilter',
		scope: {options: '='},
		link: function(scope, elem, attrs, controller) {
			scope.$watchCollection('options', function(val) {
				if (!scope.selected && val && val.length !== 0) {
					scope.selected = val[0];
				}
			});

			var uuid = azUtils.generateUUID();

			scope.$watchGroup([
				'selected',
				'filterValue'
			], function() {
				controller.register(uuid, scope.selected, scope.filterValue);
			});

			scope.remove = function() {
				controller.unregister(uuid);
				elem.remove();
			};
		}
	};
});
