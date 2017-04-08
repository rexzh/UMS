app.controller('NavCtrl', function ($scope, $L, $base_url, $http, $window, dataShare) {
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

        $http.get($base_url + '/management/logout.json').success(function(x){
            $window.location.assign("/management/resources/login.html");
        });
    };

    $scope.select = function(idx) {
        var selectedOrg = $scope.organizations[idx];
        $http.post($base_url + '/management/currentOrg.json', selectedOrg).success(function(x){
            console.log(x.data);
            $scope.user.currentOrg = selectedOrg;
        });
    }
});

app.controller('SystemStatusCtrl', function ($scope, $location, $L, $timeout, dataShare, resetMenu) {
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

    $scope.message = {
        type: 'success',
        head: 'Attention',
        detail: $L('Success')
    };

    $scope.hide = function() {
        $scope.showMessage = false;
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
                head: $L(resp.data.data.error),
                detail: JSON.stringify(resp.data.data.stack)
            }
            $scope.showMessage = true;
        }
    });

    $scope.$on('serviceFailure', function(evt, resp) {
        $scope.message = {
            type: 'error',
            head: $L('Error'),
            detail: JSON.stringify(resp)
        }
        $scope.showMessage = true;
    });
});

app.controller('MenuCtrl', function ($rootScope, $scope, $window, $http, $L, $base_url, dataShare) {

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
            dataShare.setData('menus', menus);
            $rootScope.$broadcast('login', x.data);
        } else {
            if(x.data.error && x.data.error == 'NotLogin') {
                $window.location.assign("/management/resources/login.html");
            }
        }
    });
});