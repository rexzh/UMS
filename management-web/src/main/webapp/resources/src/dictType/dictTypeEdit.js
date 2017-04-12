//generator

app.controller('DictTypeEditCtrl', function($scope, $location, $window, $routeParams, rest, notify) {
    var path = $location.path();
    $scope.isModify = (path != '/dictTypeAdd/');
    var id = 0;

    if(!$scope.isModify) {
        $scope.dictType = {
            global: true
        };
    }
    
    if($scope.isModify) {
        id = $routeParams.id;
        rest.endpoint('/dictType.json/' + id).get().then(function(resp){
            $scope.dictType = resp.data.dictType;
        });
    }

    $scope.back = function() {
        $window.history.back();
    };

    $scope.submit = function(){
        //TODO:Verify

        var endpoint = rest.endpoint('/dictType.json');
        if($scope.isModify) {
            endpoint.put($scope.dictType).then(function(x){
                if(x.result)
                    $location.path('/dictType');
            });
        } else {
            endpoint.post($scope.dictType).then(function(x){
                if(x.result)
                    $location.path('/dictType');
            });
        }
    }
});