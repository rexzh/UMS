app.controller('SubmenuCtrl', function ($scope,  $L, $location, msgbox, rest) {
    $scope.remove = function(idx) {
        msgbox.show({text: "删除当前记录?"}).then(function(x){
            if(x){
                var id = $scope.submenus[idx].id;
                rest.endpoint('submenu.json/' + id).delete().then(function(x){
                    if(!x.result) {
                        return;
                    }
                    $scope.submenus.splice(idx, 1);
                });

            }
        });
    }

    rest.endpoint('submenu.json').get().then(function(x){
        if(x.result) {
            $scope.submenus = x.data.submenus;
        }
    });

    $scope.add = function() {
        $location.path("/submenuAdd");
    };

    $scope.modify = function(idx) {
        $location.path("/submenuModify/" + $scope.submenus[idx].id);
    };
});