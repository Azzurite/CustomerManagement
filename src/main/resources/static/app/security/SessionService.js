'use strict';
/**
 * @ngdoc service
 * @name Session
 * @requires $q
 * @requires $http
 * @requires $log
 * @requires $rootScope
 * @requires $state
 * @requires CacheFactory
 * @requires growl
 * @requires azNetUtils
 *
 * @description
 * Checks for and establishes a temporary javascript-only Session.
 */
angular.module('cmApp.security').service('Session',
	function($q, $http, $log, $rootScope, $state, CacheFactory, growl, azNetUtils) {
		var self = this;
		var sessionCache = CacheFactory.createCache('SessionCache', {
			maxAge: 10000,
			deleteOnExpire: 'passive'
		});


		/**
		 * @ngdoc onEvent
		 * @name $stateChangeStart
		 *
		 * @description
		 * Check for session on each route change and forward the user to login if missing.
		 */
		$rootScope.$on('$stateChangeStart',
			function(event, toState, toParams) {
				if (toState.name === 'login' || sessionCache.get('account')) {
					return;
				}

				event.preventDefault();
				self.get().then(
					function() {
						$state.go(toState, toParams);
					},
					function() {
						growl.error('Session not found. Please log in.');
						$state.go('login');
					}
				);
			}
		);

		/**
		 * @ngdoc method
		 * @name get
		 *
		 * @description
		 * Try to get the session
		 *
		 * @returns {Promise} a promise that resolves with the Session data when a session is found
		 */
		this.get = function() {
			var account = sessionCache.get('account');

			if (account) {
				$log.debug('Session is cached:', account);
				return $q(function(resolve) {
					resolve(account);
				});
			}

			var promise = getAccount();

			promise.then(function(account) {
				sessionCache.put('account', account);
			});

			return promise;
		};

		function getAccount() {
			return $q(function(resolve, reject) {
				$http.get('/rest/account').success(
					function(account) {
						$log.debug('Session exists:', account);
						self.account = account;
						resolve(account);
					}
				).error(
					function() {
						$log.debug('Session missing');
						reject();
					}
				);
			});
		}

		/**
		 * @ngdoc method
		 * @name create
		 *
		 * @description
		 * Creates a session by logging the user in and then retrieving the Session data. If a Session already exists,
		 *     rejects the returned promise with the already existing Session data.
		 *
		 * @param username
		 * @param password
		 * @returns {Promise}
		 */
		this.create = function(username, password) {
			return $q(function(resolve, reject) {
				self.get().then(
					function(account) {
						$log.debug('Won\'t create session, it already exists.');
						reject(account);
					},
					function() {
						self.login(username, password).then(
							function(account, a1, a2, a3, a4) {
								$log.debug('Created session:', account, a1, a2, a3, a4);
								self.get().then(resolve, reject);
							},
							function(data, status, a1, a2, a3, a4) {
								$log.debug('Session creation failed:', status, data, a1, a2, a3, a4);
								reject();
							}
						);
					}
				);
			});
		};

		/**
		 * @ngdoc method
		 * @name login
		 *
		 * @description
		 * Logs the user in.
		 *
		 * @param username
		 * @param password
		 * @returns {Promise} resolves with the login result.
		 */
		this.login = function(username, password) {
			return $q(function(resolve, reject) {
				azNetUtils.formPost('/login', {
						username: username,
						password: password
					}
				).success(
					function() {
						resolve();
					}
				).error(
					function(data) {
						reject(data);
					}
				);
			});
		};
	}
)
;
