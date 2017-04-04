app.controller('SubmenuEditCtrl', function($scope, $location, $routeParams, $window, $http, notify, $base_url) {
    var path = $location.path();
    $scope.isModify = (path != '/submenuAdd/');
    var id = 0;

    $http.get($base_url + '/management/menu.json').then(function(resp){
        $scope.lv1menus = resp.data.data.menus;
        if($scope.isModify) {
            id = $routeParams.id;
            $http.get($base_url + '/management/submenu.json/' + id).then(function(resp){
                $scope.submenu = resp.data.data.submenu;

                for(var i = 0; i < $scope.lv1menus.length; i++) {
                    if($scope.lv1menus[i].id == $scope.submenu.parentId) {
                        $scope.lv1menu = $scope.lv1menus[i];
                        break;
                    }
                }

            });
        } else {
            $scope.submenu = {};
        }
    });

    $scope.back = function() {
        $window.history.back();
    };

    $scope.submit = function(){
        if(!$scope.submenu.name) {
            notify.error('菜单名称不能为空');
            return;
        }

        if(!$scope.submenu.link) {
            notify.error('链接不能为空');
            return;
        }

        if(!$scope.lv1menu) {
            notify.error('请选择上级菜单');
            return;
        } else {
            $scope.submenu.parentId = $scope.lv1menu.id;
        }

        if(isNaN(parseInt($scope.submenu.displayOrder))) {
            notify.error('显示顺序必须是数字');
            return;
        }

        notify.info();

        var url = $base_url + '/management/submenu.json';
        if($scope.isModify) {
            $http.put(url, $scope.submenu).then(function(x){
                if(x.data.result)
                    $location.path('/submenu');
            });
        } else {
            $http.post(url, $scope.submenu).then(function(x){
                if(x.data.result)
                    $location.path('/submenu');
            });
        }
    }
});