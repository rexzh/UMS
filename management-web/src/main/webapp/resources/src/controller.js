app.controller('NavCtrl', function ($scope, $L) {
    $scope.brand = $L("Generic Platform");
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

app.controller('MenuCtrl', function ($rootScope, $scope, $timeout, $L, $http, $base_url) {

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
        }
    });

});