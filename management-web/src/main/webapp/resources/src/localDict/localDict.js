//generator
app.controller('LocalDictCtrl', function($scope, $location, rest, msgbox) {
    
    rest.endpoint('/localDict.json').get().then(function(resp){
        if(resp.result) {
            //console.log(resp.data);
            $scope.localDicts = resp.data.localDicts;
        }
    });

    $scope.remove = function(idx) {
        msgbox.show().then(function(x){
            if(x) {
                var id = $scope.localDicts[idx].id;
                rest.endppint('localDict.json', id).delete().then(function(resp){
                    if(resp.result);
						$scope.localDicts.splice(idx, 1);
                });
            }
        });
    }

    $scope.add = function() {
        $location.path("/localDictAdd");
    };

    $scope.modify = function(idx) {
        $location.path("/localDictModify/" + $scope.localDicts[idx].id);
    };
});