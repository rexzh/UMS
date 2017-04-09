//generator
app.controller('OrganizationCtrl', function($scope, $location, rest, msgbox) {
    
    rest.endpoint('/organization.json').get().then(function(resp){
        if(resp.result) {
            //console.log(resp.data);
            $scope.organizations = resp.data.organizations;
        }
    });

    $scope.remove = function(idx) {
        msgbox.show({text: "删除当前记录?"}).then(function(x){
            if(x) {
                var id = $scope.organizations[idx].id;

                rest.endpoint('/organization.json/' + id).delete().then(function(resp){
                    if(resp.result)
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