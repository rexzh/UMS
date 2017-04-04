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

    svc.factory('regEx', function () {
        return function(pattern, string) {
            pattern = pattern.toString();
            var result = [];
            var groupRX = /\(\<(.*?)\>\s(.*?)\)/;
            while (groupRX.test(pattern)) {
                var match = groupRX.exec(pattern);
                result.push({
                    name : match[1],
                    pattern : match[2],
                    value : null
                });
                pattern = pattern.replace(groupRX, '('+match[2]+')');
            }
             
            var finalMatch=(new RegExp(pattern)).exec(string);
            if(finalMatch) {
                for (var i = 0, len = result.length; i < len; i++) {
                    if(finalMatch[(i + 1)] !== false) {
                        result[i].value = finalMatch[(i + 1)];
                    }
                }
            }
            return result;
        };
    });
})();