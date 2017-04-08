app.controller('SubmenuCtrl', function ($scope, $http, $L, $location, msgbox, $base_url) {
    $scope.remove = function(idx) {
        msgbox.show({text: "删除当前记录?"}).then(function(x){
            if(x){
                var id = $scope.submenus[idx].id;
                var url = $base_url + '/management/submenu.json/' + id;
                $http.delete(url).then(function(resp){
                    if(!resp.data.result) {
                        return;
                    }
                    $scope.submenus.splice(idx, 1);
                });

            }
        });
    }

    $http.get($base_url + '/management/submenu.json').then(function(resp){
        if(resp.data.result) {
            $scope.submenus = resp.data.data.submenus;
        }
    });

    $scope.add = function() {
        $location.path("/submenuAdd");
    };

    $scope.modify = function(idx) {
        $location.path("/submenuModify/" + $scope.submenus[idx].id);
    };
});