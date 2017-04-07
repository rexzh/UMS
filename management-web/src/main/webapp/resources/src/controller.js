app.controller('NavCtrl', function ($scope, $L, $base_url, $http, $window, dataShare) {
    $scope.brand = $L("Generic Platform");

    $scope.$on('login', function(msg, data){
        //console.log(data.user, data.user.role, data.env, data.user.organizations);
        $scope.env = data.env;
        $scope.user = data.user;
        $scope.organizations = data.user.organizations;
        if($scope.organizations.length > 0)
            $scope.user.currentOrg = $scope.organizations[0];

        dataShare.setData('user', $scope.user);
    });

    $scope.logout = function(msg, data){
        $scope.user = {};
        $scope.organizations = [];
        $scope.user.currentOrg = {name: "请选择"};
        dataShare.setData('user', null);

        $http.get($base_url + '/management/logout.json').success(function(x){
            $window.location.assign("/management/resources/login.html");
        });
    };

    $scope.select = function(idx) {
        $scope.user.currentOrg = $scope.organizations[idx];
        //TODO:Send to server??
    }
});

app.controller('SystemStatusCtrl', function ($scope, $timeout, $L) {
    $scope.showWait = false;
    $scope.showMessage = false;

    $scope.message = {
        type: 'success',
        head: 'Attention',
        detail: '成功'
    }

    $scope.hide = function() {
        $scope.showMessage = false;
    }

    $scope.$on('ajaxStart', function(){
        $scope.showWait = true;
        $scope.showMessage = false;
    });

    $scope.$on('ajaxEnd', function(){
        $scope.showWait = false;
    });

    $scope.$on('notify', function(evt, msg) {

        $scope.message = {
            type: msg.type,
            detail: msg.text
        }
        $scope.showMessage = true;
        if(msg.type == "success") {
            $timeout(function(){
                $scope.showMessage = false;
            }, 3000);
        }
    });

    $scope.$on('serviceSuccess', function(evt, resp) {

        if(resp.data && resp.data.result === false) {
            console.log(resp.data);
            $scope.message = {
                type: 'error',
                head: resp.data.data.error,
                detail: JSON.stringify(resp.data.data.stack)
            }
            $scope.showMessage = true;
        }
    });

    $scope.$on('serviceFailure', function(evt, resp) {
        $scope.message = {
            type: 'error',
            head: '错误',
            detail: JSON.stringify(resp)
        }
        $scope.showMessage = true;
    });
});

app.controller('MenuCtrl', function ($rootScope, $scope, $window, $http, $L, $base_url) {

    $http.get($base_url + '/management/index.json').success(function(x){
        if(x.result) {
            var menus = x.data.menus;

            for(var i = 0; i < menus.length; i++) {
                var mn = menus[i];
                mn.name = $L(mn.name);
                for(var j = 0; j < mn.submenus.length; j++) {
                    var itm = mn.submenus[j];
                    itm.name = $L(itm.name);
                }
            }

            $scope.menus = menus;
            $rootScope.$broadcast('login', x.data);
        } else {
            if(x.data.error && x.data.error == 'NotLogin') {
                $window.location.assign("/management/resources/login.html");
            }
        }
    });
});