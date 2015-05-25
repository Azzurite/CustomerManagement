'use strict';

/**
 * @ngdoc constant
 * @name SORT_ORDER
 *
 * @description
 * Enumeration to signify the sort order.
 */
angular.module('azData').constant('SORT_ORDER', {
	ASCENDING: 'asc',
	DESCENDING: 'desc',
	NONE: 'none'
});

/**
 * @ngdoc directive
 * @name azSort
 *
 * @requires $filter
 * @requires azUtils
 * @requires SORT_ORDER
 *
 * @restrict AE
 *
 * @description
 * Transcludes its content. Directive to group a bunch of azSortFields together to sort the given array by one of these.
 *
 * @param {Array} azSort the array to sort
 * @param {Array} azSortOutput the variable where the sorted array will be put
 */
angular.module('azData').directive('azSort', function() {
	return {
		restrict: 'AE',
		scope: {
			toSort: '=azSort',
			output: '=azSortOutput'
		},
		transclude: true,
		link: function(scope, element, attrs, controller, transclude) {
			transclude(function(clone, scope) {
				element.append(clone);
			});
		},
		controller: function($scope, $element, $attrs, $transclude, SORT_ORDER, azUtils, $filter) {
			var curSort = SORT_ORDER.NONE;
			var curSortField = null;
			$scope.output = $scope.toSort;

			var sort = function() {
				if (curSort === SORT_ORDER.NONE) {
					$scope.output = $scope.toSort;
				} else {
					var sortFields = curSortField.split(',');
					var reverseOrder = curSort === SORT_ORDER.DESCENDING;
					$scope.output = $filter('orderBy')($scope.toSort, sortFields, reverseOrder);

				}
			};

			$scope.$watchCollection('toSort', function() {
				sort();
			});

			this.sortStatus = function(sortField) {
				return curSortField === sortField ? curSort : SORT_ORDER.NONE;
			};

			this.clicked = function(sortField) {
				if (curSortField !== sortField) {
					curSortField = sortField;
					curSort = SORT_ORDER.ASCENDING;
				} else {
					curSort = azUtils.getNextOption(curSort, SORT_ORDER);
				}

				sort();
			};
		}
	};
});

/**
 * @ngdoc directive
 * @name azSortField
 *
 * @requires azSort
 *
 * @restrict AE
 *
 * @description
 * Transcludes its content. The content will be placed in a 'a'-tag with block-display-behaviour and a sort icon added.
 * Its value should be a property name of the objects in the array being sorted, by which this field should sort.
 * Requires azSort as its parent.
 *
 * @param {String} azSortField the name of a property on one of the array values.
 */
angular.module('azData').directive('azSortField', function($timeout) {
	return {
		restrict: 'A',
		scope: {},
		transclude: true,
		require: '^azSort',
		template: '<a class="btn-block nohover" role="button">' +
		'<span ng-transclude></span>' +
		'<span style="float:right;" ng-class="{\'glyphicon\': true,\'center\': true,' +
		'\'glyphicon-sort\': sort === \'none\',' +
		'\'glyphicon-sort-by-alphabet\': sort === \'asc\',' +
		'\'glyphicon-sort-by-alphabet-alt\': sort === \'desc\'}">' +
		'</span>' +
		'</a>',
		link: function(scope, element, attrs, controller) {
			element.css('cursor', 'pointer');
			element.css('user-select', 'none');

			var sortField = attrs.azSortField;

			scope.$watch(function() {
				return controller.sortStatus(sortField);
			}, function(newVal) {
				scope.sort = newVal;
			});

			element.on('click', function() {
				$timeout(function() {
					controller.clicked(sortField);
				});
			});
		}
	};
});
