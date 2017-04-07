'use strict';

app.controller('UserEditCtrl', function($scope, $location, $window, $routeParams, $http, dataShare, notify, $base_url) {
    var path = $location.path();
    $scope.isModify = (path != '/userAdd/');
    var id = 0;

    if(!$scope.isModify) {
        $scope.user = {
            enabled: true
        };
    }

    $http.get($base_url + '/management/organization.json/byUser').then(function(resp){
        $scope.orgs = resp.data.data.organizations;

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

                    for(var i = 0; i < $scope.orgs.length; i++) {
                        for(var j = 0; j < $scope.user.organizations.length; j++) {
                            var related_org = $scope.user.organizations[j];
                            if(related_org.id == $scope.orgs[i].id) {
                                $scope.orgs[i].checked = true;
                            }
                        }
                    }
                });
            }
        });
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


        $scope.user.organizations = [];
        for(var i = 0; i < $scope.orgs.length; i++) {
            var org = $scope.orgs[i];
            if(org.checked)
                $scope.user.organizations.push(org);
        }


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