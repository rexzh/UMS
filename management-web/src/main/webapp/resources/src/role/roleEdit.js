'use strict';

app.controller('RoleEditCtrl', function($scope, $location, $window, $routeParams, $http, msgbox, $base_url) {
    var path = $location.path();
    $scope.isModify = (path != '/roleAdd/');
    var id = 0;

    $http.get($base_url + '/management/submenu.json').then(function(resp){
        $scope.submenus = resp.data.data.submenus;
        if($scope.isModify) {
            id = $routeParams.id;
            $http.get($base_url + '/management/role.json/' + id).then(function(resp){
                $scope.role = resp.data.data.role;

                var role_menu = $scope.role.roleMenus;

                for(var i = 0; i < $scope.submenus.length; i++) {
                    for(var j = 0; j < role_menu.length; j++) {
                        var m = $scope.submenus[i];
                        if(role_menu[j].submenuId == m.id) {
                            m.checked = true;
                        }
                    }
                }
            });
        }
    });

    $scope.back = function() {
        $window.history.back();
    };

    $scope.submit = function(){
        if(!$scope.role.name) {
            msgbox.error('角色名称不能为空');
            return;
        }

        $scope.role.roleMenus = [];
        for(var i = 0; i < $scope.submenus.length; i++) {
            var m = $scope.submenus[i];
            if(m.checked)
                $scope.role.roleMenus.push({"roleId": $scope.role.id, "submenuId": m.id});
        }

        console.log($scope.role);


        var url = $base_url + '/management/role.json';
        if($scope.isModify) {
            $http.put(url, $scope.role).then(function(x){
                if(x.data.result)
                    $location.path('/role');
            });
        } else {
            $http.post(url, $scope.role).then(function(x){
                if(x.data.result)
                    $location.path('/role');
            });
        }
    }
});