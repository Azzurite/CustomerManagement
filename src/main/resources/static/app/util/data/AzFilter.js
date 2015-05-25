'use strict';

/**
 * @ngdoc directive
 * @name azFilter
 *
 * @requires $filter
 * @requires azUtils
 *
 * @restrict E
 *
 * @description
 * Provides a way to filter an array of objects by multiple of the array object's properties.
 *
 * @param {Array} filter the array of objects to filter
 * @param {Array} output the filtered array
 * @param {Object} filterOptions an object with the keys as names for a filter and the values as the property of the
 *     object in the array to filter by.
 *     Can be an array of values as well, it will then be filtered by "property1 + ' ' + property2 + ' ' + ....".
 *
 */
angular.module('azData').directive('azFilter', function($compile) {
	return {
		templateUrl: 'app/util/data/filter.html',
		restrict: 'E',
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
			// add first
			scope.addFilter();
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


			function lowerNormalizedString(s) {
				return (s || '').toLocaleLowerCase();
			}

			function multiPropertyContains(value, object, properties) {
				var appended = '';
				angular.forEach(properties, function(property) {
					var propertyValue = lowerNormalizedString(object[property]);
					appended += ' ' + propertyValue;
				});

				var filterValue = lowerNormalizedString(value);
				return appended.indexOf(filterValue) > -1;
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

/**
 * @ngdoc directive
 * @name azFilterElement
 *
 * @restrict E
 *
 * @description
 * A filter element created by the azFilter directive.
 *
 * @param {Array} options the options that can be filtered
 */
angular.module('azData').directive('azFilterElement', function(azUtils) {
	return {
		templateUrl: 'app/util/data/filterElement.html',
		require: '^azFilter',
		restrict: 'E',
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
