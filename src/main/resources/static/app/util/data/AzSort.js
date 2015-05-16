'use strict';

angular.module('azData').constant('SORT_ORDER', {
	ASCENDING: 'asc',
	DESCENDING: 'desc',
	NONE: 'none'
});

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

angular.module('azData').directive('azSortField', function($timeout) {
	return {
		restrict: 'A',
		scope: {},
		transclude: true,
		require: '^azSort',
		template: '<a type="button" style="display:inline-block;height: 100%; width: 100%">' +
		'<span ng-transclude></span>' +
		'<span style="float:right;" ng-class="{glyphicon: true,' +
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
