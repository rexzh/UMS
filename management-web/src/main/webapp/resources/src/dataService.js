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

    svc.factory('query', function ($q, $http, $base_url) {
        var query = {
            exec: function(path, map, page, rows) {
                var url = pathCombine(base_url, path);
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
                url += q;
                //console.log(url);

                var deferred = $q.defer();
                $http.get(url).then(function(resp){
                if(resp.data.result) {
                        deferred.resolve(resp.data.data);
                    } else {
                        deferred.reject();
                    }
                });
                return deferred.promise;
            }
        }

        return query;
    });
})();