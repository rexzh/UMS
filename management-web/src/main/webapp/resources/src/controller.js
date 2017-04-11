﻿app.controller('NavCtrl', function ($scope, $L, rest, $window, dataShare) {
    $scope.brand = $L("Generic Platform");
    $scope.userRole = $L("UserRole");
    $scope.logoutLabel = $L("Logout");
    $scope.selectOrg = $L("Select Organization");

    $scope.$on('login', function(msg, data){
        $scope.env = data.env;
        $scope.user = data.user;
        $scope.organizations = data.user.organizations;

        $scope.user.currentOrg = data.user.currentOrganization;

        dataShare.setData('user', $scope.user);
    });

    $scope.logout = function(msg, data){
        $scope.user = {};
        $scope.organizations = [];
        $scope.user.currentOrg = {name: $L('Please Select')};
        dataShare.setData('user', null);

        rest.endpoint('logout.json').get().then(function(x){
            $window.location.assign("/management/resources/login.html");
        });
    };

    $scope.select = function(idx) {
        var selectedOrg = $scope.organizations[idx];
        rest.endpoint('/currentOrg.json').post(selectedOrg).then(function(x){
            if(x.result)
                $scope.user.currentOrg = selectedOrg;
        });
    }
});

app.controller('SystemStatusCtrl', function ($scope, $location, $window, $L, $timeout, dataShare, resetMenu) {
    $scope.resetMenu = resetMenu;
    $scope.home = $L("Home");

    $scope.$on('login', function() {
        var path = $location.path();
        if(path[0] == '/') path = '#' + path;
        if(path[path.length - 1] == '/') path = path.substr(0, path.length - 1);

        var menus = dataShare.getData('menus');

        for(var i = 0; i < menus.length; i++) {
            var m = menus[i];
            for(var j = 0; j < m.submenus.length; j++) {
                var sub = m.submenus[j];
                if(path == sub.link) {
                    $scope.breadcrumb = sub.name;
                }
            }
        }
        if(!$scope.breadcrumb)
            $scope.breadcrumb = '404';
    });

    $scope.showWait = false;
    $scope.showMessage = false;
    $scope.showDetail = true;
    $scope.icon = 'icon-chevron-up';

    function showMessage() {
        $scope.showMessage = true;
        if($scope.showDetail && $scope.message.type == 'error')
            $scope.toggleDetail();
        if((!$scope.showDetail) && $scope.message.type != 'error')
            $scope.toggleDetail();
    }

    $scope.message = {
        type: 'success',
        head: 'Attention',
        detail: $L('Success')
    };

    $scope.hide = function() {
        $scope.showMessage = false;
    };

    $scope.toggleDetail = function() {
        if($scope.showDetail) {
            $scope.icon = 'icon-chevron-down';
            $scope.showDetail = false;
        } else {
            $scope.icon = 'icon-chevron-up';
            $scope.showDetail = true;
        }
    };

    $scope.$on('menuChange', function(evt, data){
        $scope.breadcrumb = data.name;
    });

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
            detail: msg.text,
            head: $L('Attention')
        }
        showMessage();
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
                head: $L(resp.data.data.error),
                detail: JSON.stringify(resp.data.data.stack)
            }
            showMessage();
        }
    });

    $scope.$on('serviceFailure', function(evt, resp) {
        $scope.message = {
            type: 'error',
            head: $L('Error'),
            detail: JSON.stringify(resp)
        }
        showMessage();
    });

    $scope.$on('logout', function(){
        $window.location.assign("/management/resources/login.html");
    })
});

app.controller('MenuCtrl', function ($rootScope, $scope, $window, $timeout, $L, rest, resetMenu, dataShare) {

    rest.endpoint('/index.json').get().then(function(x){
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
            dataShare.setData('menus', menus);
            $rootScope.$broadcast('login', x.data);
            $timeout(function(){
                resetMenu();
            }, 0);
        } else {
            if(x.data.error && x.data.error == 'NotLogin') {
                $window.location.assign("/management/resources/login.html");
            }
        }
    });
});