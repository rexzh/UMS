//generator

app.controller('GlobalDictEditCtrl', function($scope, $location, $window, $routeParams, rest, notify) {
    var path = $location.path();
    $scope.isModify = (path != '/globalDictAdd/');
    var id = 0;

    
    if($scope.isModify) {
        id = $routeParams.id;
        rest.endpoint('/globalDict.json/' + id).get().then(function(resp){
            $scope.globalDict = resp.data.globalDict;
        });
    }

    $scope.back = function() {
        $window.history.back();
    };

    $scope.submit = function(){
        //TODO:Verify

        var endpoint = rest.endpoint('/globalDict.json');
        if($scope.isModify) {
            endpoint.put($scope.globalDict).then(function(x){
                if(x.result)
                    $location.path('/globalDict');
            });
        } else {
            endpoint.post($scope.globalDict).then(function(x){
                if(x.result)
                    $location.path('/globalDict');
            });
        }
    }
});