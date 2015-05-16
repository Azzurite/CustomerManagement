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
angular.module('azUtils').constant('azUtils', {

	/**
	 * Gets the next option given the list of options and the current option. Wraps to the first value if the end is
	 * reached.
	 *
	 * @param curOption
	 * @param optionList
	 * @returns {*}
	 */
	getNextOption: function(curOption, optionList) {
		var first = null;
		var next = false;
		var result = null;
		angular.forEach(optionList, function(value) {
			if (!first) {
				first = value;
			}
			if (next) {
				result = value;
				next = false;
				return false;
			}
			if (curOption === value) {
				next = true;
			}
		});

		return result ? result : first;
	},

	/**
	 * Taken from http://stackoverflow.com/a/29018745/1805439
	 *
	 * @param array A sorted array
	 * @param value An element to search for
	 * @param comparator A comparator function OR string value of a predefined comparator function.
	 *        Pre-defined comparators are: 'stringIgnoreCase'.
	 *        The function takes two arguments: (a, b) and returns:
	 *            a negative number  if a is less than b;
	 *            0 if a is equal to b;
	 *            a positive number of a is greater than b.
	 * @returns {number} the index of of the element in a sorted array or (-n-1) where n is the insertion point for the
	 *     new element.
	 */
	binarySearch: function(array, value, comparator) {
		if (!comparator) {
			comparator = function(obj1, obj2) {
				return obj1 < obj2 ? -1 : (obj1 === obj2 ? 0 : 1);
			};
		}
		if (comparator === 'stringIgnoreCase') {
			comparator = function(s1, s2) {
				var s1lower = s1 ? s1.toLocaleLowerCase() : s1;
				var s2lower = s2 ? s2.toLocaleLowerCase() : s2;
				return s1lower < s2lower ? -1 : (s1lower === s2lower ? 0 : 1);
			};
		}
		var lowerBound = 0;
		var upperBound = array.length - 1;
		while (lowerBound <= upperBound) {
			var curMid = (upperBound + lowerBound) >> 1; // jshint ignore:line
			var compareResult = comparator(value, array[curMid]);
			if (compareResult > 0) {
				lowerBound = curMid + 1;
			} else if (compareResult < 0) {
				upperBound = curMid - 1;
			} else {
				return curMid;
			}
		}
		return -lowerBound - 1;
	},

	/**
	 * Inserts an element into a sorted array.
	 *
	 * @param array A sorted array
	 * @param elem An element to insert
	 * @param comparator A comparator function OR string value of a predefined comparator function.
	 *        Pre-defined comparators are: 'stringIgnoreCase'.
	 *        The function takes two arguments: (a, b) and returns:
	 *            a negative number  if a is less than b;
	 *            0 if a is equal to b;
	 *            a positive number of a is greater than b.
	 */
	insertSorted: function(array, elem, comparator) {
		var result = this.binarySearch(array, elem, comparator);
		var insertAt = result >= 0 ? result : (-result) - 1;
		array.splice(insertAt, 0, elem);
	},

	/**
	 * Gets all the properties from the given element(s) in a sorted array.
	 *
	 * @param elements
	 * @returns {Array}
	 */
	getAllProperties: function(elements) {
		var azUtils = this;
		var properties = {};
		if (!angular.isArray(elements)) {
			elements = [elements];
		}

		angular.forEach(elements, function(elem) {
			angular.forEach(elem, function(value, key) {
				if (value) {
					properties[key] = 1;
				}
			});
		});

		var result = [];
		angular.forEach(properties, function(value, key) {
			azUtils.insertSorted(result, key, 'stringIgnoreCase');
		});
		return result;
	},

	/**
	 * Generates an UUID.
	 *
	 * @returns {string}
	 */
	generateUUID: function() {
		// jshint ignore:start
		var d = new Date().getTime();
		var uuid = 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function(c) {
			var r = (d + Math.random() * 16) % 16 | 0;
			d = Math.floor(d / 16);
			return (c == 'x' ? r : (r & 0x7 | 0x8)).toString(16);
		});
		return uuid.toUpperCase();
		// jshint ignore:end
	}
});


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
