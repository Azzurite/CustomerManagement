'use strict';

/**
 * @ngdoc module
 * @name azUtils
 *
 * @description
 * Collection of helper functions/objects/etc.
 */
angular.module('azUtils', []);

/**
 * @ngdoc constant
 * @name azUtils
 *
 * @description
 * object that contains general methods not necessarily related to angular
 */
angular.module('azUtils').constant('azUtils', {});


/**
 * @ngdoc service
 * @name azNetUtils
 * @requires $http
 *
 * @description
 * Has helper or convenience methods for network related functionality.
 */
angular.module('azUtils').service('azNetUtils',

	function($http) {
		var self = this;

		/**
		 * @ngdoc method
		 * @name azNetUtils#formPostTransform
		 *
		 * @description
		 * Transforms an object into a x-www-form-urlencoded String.
		 *
		 * @param data the object of key/values to convert
		 * @returns {string} the keys joined with its value by '=' and other keys by '&'
		 */
		this.formPostTransform = function(data) {
			var params = [];
			angular.forEach(data, function(value, key) {
				params.push(encodeURIComponent(key) + '=' + encodeURIComponent(value));
			});
			return params.join('&');
		};

		/**
		 * @ngdoc method
		 * @name azNetUtils#formPost
		 *
		 * @description
		 * Performs the same kind of request like a <form method="post" />
		 *
		 * @param url the url to send the post to
		 * @param args the arguments as an object.
		 * @returns {HttpPromise} return value of $http(...)
		 */
		this.formPost = function(url, args) {
			return $http({
				method: 'POST',
				url: url,
				headers: {'Content-Type': 'application/x-www-form-urlencoded'},
				transformRequest: self.formPostTransform,
				data: args
			});
		};
	}
);
