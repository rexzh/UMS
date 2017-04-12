'use strict';

app.controller('RoleEditCtrl', function($scope, $location, $window, $routeParams, rest, notify) {
    var path = $location.path();
    $scope.isModify = (path != '/roleAdd/');
    var id = 0;

    rest.endpoint('/submenu.json').get().then(function(resp){
        $scope.submenus = resp.data.submenus;
        if($scope.isModify) {
            id = $routeParams.id;
            rest.endpoint('/role.json/' + id).get().then(function(resp){
                $scope.role = resp.data.role;

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
        } else{
            $scope.role = {
                enabled: true
            };
        }
    });

    $scope.back = function() {
        $window.history.back();
    };

    $scope.submit = function(){
        if(!$scope.role.name) {
            notify.error('角色名称不能为空');
            return;
        }

        $scope.role.roleMenus = [];
        for(var i = 0; i < $scope.submenus.length; i++) {
            var m = $scope.submenus[i];
            if(m.checked)
                $scope.role.roleMenus.push({"roleId": $scope.role.id, "submenuId": m.id});
        }

        console.log($scope.role);


        var endpoint = rest.endpoint('/role.json');
        if($scope.isModify) {
            endpoint.put($scope.role).then(function(x){
                if(x.result)
                    $location.path('/role');
            });
        } else {
            endpoint.post($scope.role).then(function(x){
                if(x.result)
                    $location.path('/role');
            });
        }
    }
});