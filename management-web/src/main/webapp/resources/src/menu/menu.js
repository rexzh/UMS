//generator
app.controller('MenuCtrl', function($scope, $location, rest, msgbox) {
    
    rest.endpoint('/menu.json').get().then(function(resp){
        if(resp.result) {
            //console.log(resp.data);
            $scope.menus = resp.data.menus;
        }
    });

    $scope.remove = function(idx) {
        msgbox.show().then(function(x){
            if(x) {
                var id = $scope.menus[idx].id;
                rest.endpoint('menu.json', id).delete().then(function(resp){
                    if(resp.result)
						$scope.menus.splice(idx, 1);
                });
            }
        });
    }

    $scope.add = function() {
        $location.path("/menuAdd");
    };

    $scope.modify = function(idx) {
        $location.path("/menuModify/" + $scope.menus[idx].id);
    };
});