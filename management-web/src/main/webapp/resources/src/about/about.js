app.controller('AboutCtrl', function ($scope, $L, resetMenu) {
    $scope.home = $L("Home");
    $scope.about = $L("About");
    $scope.name = $L("Generic Platform");
    $scope.version = $L("Version");

    $scope.resetMenu = resetMenu;


});