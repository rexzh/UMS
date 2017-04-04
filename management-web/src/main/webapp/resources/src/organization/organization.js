//generator
app.controller('OrganizationCtrl', function($scope, $location, $http, msgbox, $base_url) {
    
    $http.get($base_url + '/management/organization.json').then(function(resp){
        if(resp.data.result) {
            //console.log(resp.data);
            $scope.organizations = resp.data.data.organizations;
        }
    });

    $scope.remove = function(idx) {
        msgbox.show().then(function(x){
            if(x) {
                var id = $scope.organizations[idx].id;
                var url = $base_url + '/management/organization.json/' + id;
                $http.delete(url).then(function(resp){
                    console.log(resp);
                    $scope.organizations.splice(idx, 1);
                });
            }
        });
    }

    $scope.add = function() {
        $location.path("/organizationAdd");
    };

    $scope.modify = function(idx) {
        $location.path("/organizationModify/" + $scope.organizations[idx].id);
    };
});