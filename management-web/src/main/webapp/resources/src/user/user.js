'use strict';

app.controller('UserCtrl', function($scope, $location, $http, msgbox, $base_url) {
    
    $http.get($base_url + '/management/user.json').then(function(resp){
        if(resp.data.result) {
            //console.log(resp.data);
            $scope.users = resp.data.data.users;
        }
    });

    $scope.remove = function(idx) {
        msgbox.show({text: "删除当前记录?"}).then(function(x){
            if(x) {
                var id = $scope.users[idx].id;
                var url = $base_url + '/management/user.json/' + id;
                $http.delete(url).then(function(resp){
                    console.log(resp);
                    $scope.users.splice(idx, 1);
                });
            }
        });
    }

    $scope.add = function() {
        $location.path("/userAdd");
    };

    $scope.modify = function(idx) {
        $location.path("/userModify/" + $scope.users[idx].id);
    };

    $scope.reset = function(idx) {
        //TODO:call reset service and display it
    }
});