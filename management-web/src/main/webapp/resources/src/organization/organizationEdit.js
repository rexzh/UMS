//generator

app.controller('OrganizationEditCtrl', function($scope, $location, $window, $routeParams, rest, notify) {
    var path = $location.path();
    $scope.isModify = (path != '/organizationAdd/');
    var id = 0;

    if(!$scope.isModify) {
        $scope.organization = {
            enabled: true
        };
    }


    if($scope.isModify) {
        id = $routeParams.id;
        rest.endpoint('organization.json/' + id).get().then(function(resp){
            $scope.organization = resp.data.organization;
        });
    }


    $scope.back = function() {
        $window.history.back();
    };

    $scope.submit = function(){
        if(!$scope.organization.name) {
            notify.error('名称不能为空');
            return;
        }

        var endpoint = rest.endpoint('/organization.json');
        if($scope.isModify) {
            endpoint.put($scope.organization).then(function(x){
                if(x.result)
                    $location.path('/organization');
            });
        } else {
            endpoint.post($scope.organization).then(function(x){
                if(x.result)
                    $location.path('/organization');
            });
        }
    }
});