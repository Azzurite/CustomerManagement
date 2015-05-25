'use strict';

/**
 * @ngdoc directive
 * @name azSidebarWrapper
 *
 * @restrict E
 *
 * @description
 * Required wrapper for a sidebar and content section. Has to have a html id tag
 *
 * @param {String} id the id of the sidebar.
 * @param {Boolean} initShown if the sidebar should start visible.
 * @param {Number} sidebarColumns the number of bootstrap columns the sidebar should fill.
 * @param {Number} contentColumns the number of bootstrap columns the content should fill.
 */
angular.module('azUtils').directive('azSidebarWrapper', function() {
	return {
		template: '<div class="row" ng-transclude></div>',
		transclude: true,
		restrict: 'E',
		replace: true,
		link: function(scope, element, attrs) {
			element.css('height', '100%');
		},
		controller: function($scope, $element, $attrs) {
			var self = this;
			self.shown = $attrs.initShown !== 'false';
			var contentColumns = $attrs.contentColumns || 9;
			var sidebarColumns = $attrs.sidebarColumns || 3;

			self.updateVisibility = function() {
				var sidebar = $element.find('az-sidebar');
				var content = $element.find('az-sidebar-content');
				sidebar.removeClass();
				content.removeClass();

				if (self.shown) {
					sidebar.addClass('col-md-' + sidebarColumns);
					content.addClass('col-md-' + contentColumns);
				} else {
					sidebar.addClass('hidden');
					content.addClass('col-md-' + (contentColumns + sidebarColumns));
				}
			};

			self.toggle = function() {
				self.shown = !self.shown;
				self.updateVisibility();
			};
		}
	};
});

/**
 * @ngdoc directive
 * @name azSidebarToggle
 *
 * @restrict E
 *
 * @description
 * Transcludes its content. When the directive is clicked, the specified sidebar will be toggled.
 *
 * @param {String} sidebarId the id of the sidebar wrapper to toggle.
 */
angular.module('azUtils').directive('azSidebarToggle', function() {
	return {
		template: '<ng-transclude ng-click="toggle();"></ng-transclude>',
		transclude: true,
		restrict: 'E',
		controller: function($scope, $element, $attrs) {
			var sidebar = null;

			function assertSidebar() {
				if (!sidebar) {
					var sidebarElem = angular.element(document.getElementById($attrs.sidebarId));
					sidebar = sidebarElem.controller('azSidebarWrapper');
				}

				return sidebar;
			}

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
			);

			$scope.toggle = function() {
				assertSidebar();
				sidebar.toggle();
			};
		}
	};
});

/**
 * @ngdoc directive
 * @name azSidebar
 *
 * @restrict E
 *
 * @description
 * Required wrapper for everything that should be placed in the side bar.
 */
angular.module('azUtils').directive('azSidebar', function() {
	return {
		require: '^^azSidebarWrapper',
		restrict: 'E',
		link: function(scope, elem, attrs, controller) {
			elem.css('height', '100%');
			elem.css('overflow', 'auto');

			controller.updateVisibility();
		}
	};
});

/**
 * @ngdoc directive
 * @name azSidebarContent
 *
 * @restrict E
 *
 * @description
 * Required wrapper for everything that should be the actual content beside the sidebar.
 */
angular.module('azUtils').directive('azSidebarContent', function() {
	return {
		require: '^^azSidebarWrapper',
		restrict: 'E',
		link: function(scope, elem, attrs, controller) {
			elem.css('height', '100%');
			elem.css('overflow', 'auto');

			controller.updateVisibility();
		}
	};
});
