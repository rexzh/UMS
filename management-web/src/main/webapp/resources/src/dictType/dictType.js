//generator
app.controller('DictTypeCtrl', function($scope, $location, rest, msgbox) {
    
    rest.endpoint('/dictType.json').get().then(function(resp){
        if(resp.result) {
            //console.log(resp.data);
            $scope.dictTypes = resp.data.dictTypes;
        }
    });

    $scope.remove = function(idx) {
        msgbox.show().then(function(x){
            if(x) {
                var id = $scope.dictTypes[idx].id;
                rest.endppint('dictType.json', id).delete().then(function(resp){
                    if(resp.result);
						$scope.dictTypes.splice(idx, 1);
                });
            }
        });
    }

    $scope.add = function() {
        $location.path("/dictTypeAdd");
    };

    $scope.modify = function(idx) {
        $location.path("/dictTypeModify/" + $scope.dictTypes[idx].id);
    };
});