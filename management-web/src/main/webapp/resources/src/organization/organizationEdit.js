//generator

app.controller('OrganizationEditCtrl', function($scope, $location, $window, $routeParams, $http, notify, $base_url) {
    var path = $location.path();
    $scope.isModify = (path != '/organizationAdd/');
    var id = 0;

    if(!$scope.isModify) {
        $scope.organization = {
            enabled: true
        };
    }

    $http.get($base_url + '/management/organization.json').then(function(resp){
        $scope.organizations = resp.data.data.organizations;
        if($scope.isModify) {
            id = $routeParams.id;
            $http.get($base_url + '/management/organization.json/' + id).then(function(resp){
                $scope.organization = resp.data.data.organization;
            });
        }
    });

    $scope.back = function() {
        $window.history.back();
    };

    $scope.submit = function(){
        //TODO:Verify

        var url = $base_url + '/management/organization.json';
        if($scope.isModify) {
            $http.put(url, $scope.organization).then(function(x){
                if(x.data.result)
                    $location.path('/organization');
            });
        } else {
            $http.post(url, $scope.organization).then(function(x){
                if(x.data.result)
                    $location.path('/organization');
            });
        }
    }
});