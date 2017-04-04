'use strict';

app.controller('UserEditCtrl', function($scope, $location, $window, $routeParams, $http, notify, $base_url) {
    var path = $location.path();
    $scope.isModify = (path != '/userAdd/');
    var id = 0;

    $http.get($base_url + '/management/role.json').then(function(resp){
        $scope.roles = resp.data.data.roles;
        if($scope.isModify) {
            id = $routeParams.id;
            $http.get($base_url + '/management/user.json/' + id).then(function(resp){
                $scope.user = resp.data.data.user;


                var role = $scope.user.role;

                for(var i = 0; i < $scope.roles.length; i++) {
                    if($scope.roles[i].id == role.id) {
                        $scope.role = $scope.roles[i];
                        break;
                    }
                }

            });
        }
    });

    $scope.back = function() {
        $window.history.back();
    };

    $scope.submit = function(){
        if(!$scope.user.code) {
            notify.error('用户名不能为空');
            return;
        }

        if(!$scope.role) {
            notify.error('角色不能为空');
            return;
        }
        $scope.user.role = $scope.role;

        var url = $base_url + '/management/user.json';
        if($scope.isModify) {
            $http.put(url, $scope.user).then(function(x){
                if(x.data.result)
                    $location.path('/user');
            });
        } else {
            $http.post(url, $scope.user).then(function(x){
                if(x.data.result)
                    $location.path('/user');
            });
        }
    };

});