app.controller('ChgpwdCtrl', function ($scope, $location, $L) {
    $scope.home = $L("Home");
    $scope.chgpwd = $L("Password Setting");
    $scope.save = $L("Save");
    $scope.oldPwdLabel = $L("Old Password");
    $scope.newPwd1Label = $L("New Password");
    $scope.newPwd2Label = $L("New Password Again");

    $scope.newPwd1 = $scope.newPwd2 = $scope.oldPwd = "";
    
    $scope.hasError = false;
    $scope.errMsg = "";
});