(function(){
    'use strict';
    var base_url = '/management/';
    //var base_url = 'localhost:8000/management/';

    var svc = angular.module('dataService', []);

    function pathCombine(p1, p2) {
        var p1End = p1[p1.length-1];
        var p2Begin = p2[0];
        if(p1End == '/' && p2Begin != '/')
            return p1 + p2;
        if(p1End != '/' && p2Begin == '/')
            return p1 + p2;
        if(p1End != '/' && p2Begin != '/')
            return p1 + '/' + p2;
        return p1 + p2.substr(1);
    }

    svc.factory('rest', function($q, $http){
        function url(args) {
            var path = base_url;
            for(var i = 0; i < args.length; i++) {
                path = pathCombine(path, args[i]);
            }
            return path;
        };
        var rest = {
            endpoint: function() {
                var path = url(arguments);
                return {
                    get: function(map, page, rows) {
                        var q = '?';
                        if(typeof(page) != 'undefined')
                            q += 'page=' + page + '&rows=' + (rows || 10);
                        for(var k in map) {
                            q += ('&' + k + '=' + map[k]);
                        }
                        if(q.length == 1)
                            q = '';
                        if(q[1] == '&')
                            q = '?' + q.substr(2);
                        path += q;
                        //console.log(path);

                        var deferred = $q.defer();
                        $http.get(path).then(function(resp){
                            deferred.resolve(resp.data);
                        }, function(err){
                            deferred.reject(err);
                        });
                        return deferred.promise;
                    },

                    post: function(arg) {
                        var deferred = $q.defer();
                        $http.post(path, arg).then(function(resp){
                            deferred.resolve(resp.data);
                        });
                        return deferred.promise;
                    },

                    put: function(arg) {
                        var deferred = $q.defer();
                        $http.put(path, arg).then(function(resp){
                            deferred.resolve(resp.data);
                        });
                        return deferred.promise;
                    },

                    delete: function(arg) {
                        var deferred = $q.defer();
                        $http.delete(path, arg).then(function(resp){
                            deferred.resolve(resp.data);
                        });
                        return deferred.promise;
                    }
                }
            }
        };

        return rest;
    });
})();