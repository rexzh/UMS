(function(){
    'use strict';
    var svc = angular.module('http.service', []);

    svc.factory('ajaxInterceptor', function ($rootScope) {
        var interceptor = {
            request: function(config) {
                //console.log("request", config);
                $rootScope.$broadcast('ajaxStart');
                return config;
            },

            response: function(response) {
                if(response.config.url.match(/^(.)*.json/g)) {
                    $rootScope.$broadcast("serviceSuccess", response);
                }
                //console.log("response", response);
                $rootScope.$broadcast('ajaxEnd');
                return response;
            },

            responseError: function(response) {
                if(response.status == 401) {
                    //TODO:Auth
                } else {
                    $rootScope.$broadcast("serviceFailure", response);
                }
                //console.log("response", response);
                $rootScope.$broadcast('ajaxEnd');
                return response;
            }
        }

        return interceptor;
    });
})();

(function(){
    'use strict';
    var svc = angular.module('common', []);

    svc.factory('dataShare', function () {
        var data = {};
        return {
            getData: function(key) {
                return data[key];
            },
            setData: function(key, val) {
                data[key] = val;
            }
        }
    });
})();