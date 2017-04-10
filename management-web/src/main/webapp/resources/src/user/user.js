'use strict';

app.controller('UserCtrl', function($scope, $location, msgbox, notify, rest) {
    $scope.page = {
        recordsPerPage: 10
    };

    $scope.criteria = {
        enabled: true
    };

    $scope.pageChange = function(p) {
        rest.endpoint('/user.json').get($scope.criteria, p, $scope.page.recordsPerPage).then(function(resp){
            if(resp.result) {
                $scope.users = resp.data.users;
                $scope.page.total = resp.data.count;
            }
        });
    }

    $scope.remove = function(idx) {
        msgbox.show({text: "删除当前记录?"}).then(function(x){
            if(x) {
                var id = $scope.users[idx].id;
                rest.endpoint('user.json', id).delete().then(function(resp){
                    if(resp.result)
                        $scope.users.splice(idx, 1);
                });
            }
        });
    }

    $scope.search = function() {
        rest.endpoint('/user.json').get($scope.criteria, 1, $scope.page.recordsPerPage).then(function(resp){
            if(resp.result) {
                $scope.users = resp.data.users;
                $scope.page.total = resp.data.count;
            }
        });
    };

    $scope.add = function() {
        $location.path("/userAdd");
    };

    $scope.modify = function(idx) {
        $location.path("/userModify/" + $scope.users[idx].id);
    };

    $scope.reset = function(idx) {
        rest.endpoint('user.json/reset/', $scope.users[idx].id).put().then(function(resp){
            if(resp.result) {
                var pwd = resp.data.password;
                msgbox.show({text: "密码重置为：" + pwd});
            }
        });
    }
});