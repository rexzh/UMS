app.controller('SettingsCtrl', function ($scope, $window, $interval, $L, resetMenu) {
    $scope.resetMenu = resetMenu;

    $scope.home = $L("Home");
    $scope.settings = $L("Settings");
    $scope.language = $L("Language");
    $scope.frequency = $L("Frequency");
    $scope.messageCount = $L("MessageCount");
    $scope.timezone = $L("TimeZone");
    $scope.apply = $L("Apply");
    $scope.ms = $L("ms");

    var lang = localStorage.getItem('lang') || 'en_us';
    $scope.selectedLanguage = $('a[data-language="' + lang + '"]').first().text();

    $scope.selLanguage = function (lang) {
        localStorage.setItem('lang', lang);
        $scope.selectedLanguage = $('a[data-language="' + lang + '"]').first().text();
    }

    var frequency = localStorage.getItem('frequency') || 5000;
    $('#frequency').first().val(frequency);

    var msgCount = localStorage.getItem('msgCount') || 10;
    $('#msgCount').first().val(msgCount);

    var tz = parseFloat(localStorage.getItem('tz')) || 8;
    $('#timezone').first().val(tz);

    $scope.applyChange = function () {
        var f = $('#frequency').first().val();
        localStorage.setItem('frequency', f);

        var c = $('#msgCount').first().val();
        localStorage.setItem('msgCount', c);

        var t = $('#timezone').first().val();
        localStorage.setItem('tz', t);

        $window.location.reload();
    }
});