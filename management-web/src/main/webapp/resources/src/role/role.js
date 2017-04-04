'use strict';

app.controller('RoleCtrl', function($scope, $location, $http, msgbox, $base_url) {
    
    $http.get($base_url + '/management/role.json').then(function(resp){
        if(resp.data.result) {
            //console.log(resp.data);
            $scope.roles = resp.data.data.roles;
        }
    });

    $scope.remove = function(idx) {
        msgbox.show().then(function(x){
            if(x) {
                var id = $scope.roles[idx].id;
                var url = $base_url + '/management/role.json/' + id;
                $http.delete(url).then(function(resp){
                    console.log(resp);
                    $scope.roles.splice(idx, 1);
                });
            }
        });
    }

    $scope.add = function() {
        $location.path("/roleAdd");
    };

    $scope.modify = function(idx) {
        $location.path("/roleModify/" + $scope.roles[idx].id);
    };
});