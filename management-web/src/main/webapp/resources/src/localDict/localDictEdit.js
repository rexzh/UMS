//generator

app.controller('LocalDictEditCtrl', function($q, $scope, $location, $window, $routeParams, dataShare, rest, notify) {
    var path = $location.path();
    $scope.isModify = (path != '/localDictAdd/');
    var id = 0;

    var q1 = rest.endpoint('/dictType.json/').get({global: false}).then(function(resp){
        $scope.types = resp.data.dictTypes;
    });

    //TODO:optimize(get current)
    var q2 = rest.endpoint('/organization.json/' + dataShare.getData('user').currentOrg.id).get().then(function(resp){
        $scope.organizations = [resp.data.organization];
    });

    $q.all([q1, q2]).then(function(){
        if($scope.isModify) {
            id = $routeParams.id;
            rest.endpoint('/localDict.json/' + id).get().then(function(resp){
                $scope.localDict = resp.data.localDict;

                for(var i = 0; i < $scope.types.length; i++) {
                    if($scope.types[i].id == $scope.localDict.typeId) {
                        $scope.type = $scope.types[i];
                        break;
                    }
                }

                for(var i = 0; i < $scope.organizations.length; i++) {
                    if($scope.organizations[i].id == $scope.localDict.orgId) {
                        $scope.organization = $scope.organizations[i];
                        break;
                    }
                }
            });
        }
    });

    $scope.back = function() {
        $window.history.back();
    };

    $scope.submit = function(){
        if(!$scope.type) {
            notify.error('请选择类型');
            return;
        }

        if(!$scope.organization) {
            notify.error('请选择机构');
            return;
        }

        if(!$scope.localDict.value) {
            notify.error('值不能为空');
            return;
        }

        if(!$scope.localDict.name) {
            notify.error('名称不能为空');
            return;
        }

        $scope.localDict.orgId = $scope.organization.id;
        $scope.localDict.typeId = $scope.type.id;

        var endpoint = rest.endpoint('/localDict.json');
        if($scope.isModify) {
            endpoint.put($scope.localDict).then(function(x){
                if(x.result)
                    $location.path('/localDict');
            });
        } else {
            endpoint.post($scope.localDict).then(function(x){
                if(x.result)
                    $location.path('/localDict');
            });
        }
    }
});