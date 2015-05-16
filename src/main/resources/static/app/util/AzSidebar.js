'use strict';

/**
 *
 */
angular.module('azUtils').directive('azSidebarWrapper', function() {
	return {
		template: '<div class="row" ng-transclude></div>',
		transclude: true,
		restrict: 'E',
		replace: true,
		controller: function($scope, $element, $attrs) {
			var self = this;
			self.shown = true;

			self.toggle = function() {
				self.shown = !self.shown;

				var sidebar = $element.find('az-sidebar');
				var content = $element.find('az-sidebar-content');
				sidebar.removeClass();
				content.removeClass();

				if (self.shown) {
					sidebar.addClass('col-md-3');
					content.addClass('col-md-9');
				} else {
					sidebar.addClass('hidden');
					content.addClass('col-md-12');
				}
			};
		}
	};
});

angular.module('azUtils').directive('azSidebarToggle', function() {
	return {
		template: '<ng-transclude ng-click="toggle();"></ng-transclude>',
		transclude: true,
		restrict: 'E',
		controller: function($scope, $element, $attrs) {
			$scope.shown = true;

			var sidebar = null;

			function assertSidebar() {
				if (!sidebar) {
					var sidebarElem = angular.element(document.getElementById($attrs.sidebarId));
					sidebar = sidebarElem.controller('azSidebarWrapper');
				}

				return sidebar;
			}

			assertSidebar();

			$scope.$watch(
				function() {
					assertSidebar();
					return sidebar.shown;
				},
				function(newVal, oldVal) {
					if (newVal === oldVal) {
						return;
					}

					$scope.shown = newVal;
				}
			)
			;

			$scope.toggle = function() {
				assertSidebar();
				sidebar.toggle();
			};
		}
	}
		;
})
;

angular.module('azUtils').directive('azSidebar', function() {
	return {
		require: '^^azSidebarWrapper',
		restrict: 'E',
		link: function(scope, element, attr, controller) {
			element.addClass('col-md-3');
		}
	};
});

angular.module('azUtils').directive('azSidebarContent', function() {
	return {
		require: '^^azSidebarWrapper',
		restrict: 'E',
		link: function(scope, element, attr, controller) {
			element.addClass('col-md-9');
		}
	};
});
