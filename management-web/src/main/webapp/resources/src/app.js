var app = angular.module('AppModule', ['ngRoute', 'http.service', 'common', 'metro.directive', 'l10n']);

app.constant('resetMenu', function () {
    var path = window.location.hash;
    if(path[path.length - 1] == '/') path = path.substr(0, path.length - 1);

    var mn = $("#sidebar-left").first();
    mn.find("a").each(function () {
        if ($(this).attr('href') == path)
            $(this).parent().addClass('active');
        else
            $(this).parent().removeClass('active');
    });
});

app.constant('resize', function(){
    var winHeight = $(window).height();
    var winWidth = $(window).width();

    var contentHeight = $("#content").height();

    if (winHeight) {
        //console.log("resize to winHeight");
        $("#content").css("min-height", winHeight/* - 41 * 2 - 6*/);
    }

    if (contentHeight) {
        //console.log("resize to contentHeight");
        $("#sidebar-left").css("height", (winHeight > contentHeight) ? winHeight : contentHeight);
    }
});

app.config(function ($routeProvider, $LProvider) {
    var lang = localStorage.getItem('lang');
    if(lang)
        $LProvider.setLocale(lang);
    else
        $LProvider.setLocale('zh_cn');

    $routeProvider.
        when('/settings/', {
            templateUrl: './src/settings/settings.html',
            controller: 'SettingsCtrl'
        }).
        when('/about/', {
            templateUrl: './src/about/about.html',
            controller: 'AboutCtrl'
        }).
        when('/chgpwd/', {
            templateUrl: './src/chgpwd/chgpwd.html',
            controller: 'ChgpwdCtrl'
        }).
        when('/user/', {
            templateUrl: './src/user/user.html',
            controller: 'UserCtrl'
        }).
        when('/userAdd/', {
            templateUrl: './src/user/userEdit.html',
            controller: 'UserEditCtrl'
        }).
        when('/userModify/:id', {
            templateUrl: './src/user/userEdit.html',
            controller: 'UserEditCtrl'
        }).
        when('/role/', {
            templateUrl: './src/role/role.html',
            controller: 'RoleCtrl'
        }).
        when('/roleAdd/', {
            templateUrl: './src/role/roleEdit.html',
            controller: 'RoleEditCtrl'
        }).
        when('/roleModify/:id', {
            templateUrl: './src/role/roleEdit.html',
            controller: 'RoleEditCtrl'
        }).
        when('/organization/', {
            templateUrl: './src/organization/organization.html',
            controller: 'OrganizationCtrl'
        }).
        when('/organizationAdd/', {
            templateUrl: './src/organization/organizationEdit.html',
            controller: 'OrganizationEditCtrl'
        }).
        when('/organizationModify/:id', {
            templateUrl: './src/organization/organizationEdit.html',
            controller: 'OrganizationEditCtrl'
        }).
        when('/menu/', {
            templateUrl: './src/menu/menu.html',
            controller: 'MenuCtrl'
        }).
        when('/menuAdd/', {
            templateUrl: './src/menu/menuEdit.html',
            controller: 'MenuEditCtrl'
        }).
        when('/menuModify/:id', {
            templateUrl: './src/menu/menuEdit.html',
            controller: 'MenuEditCtrl'
        }).
        when('/submenu/', {
            templateUrl: './src/menu/submenu.html',
            controller: 'SubmenuCtrl'
        }).
        when('/submenuAdd/', {
            templateUrl: './src/menu/submenuEdit.html',
            controller: 'SubmenuEditCtrl'
        }).
        when('/submenuModify/:id', {
            templateUrl: './src/menu/submenuEdit.html',
            controller: 'SubmenuEditCtrl'
        }).
        when('/dictType/', {
            templateUrl: './src/dictionary/dictType.html',
            controller: 'DictTypeCtrl'
        }).
        when('/dictTypeAdd/', {
            templateUrl: './src/dictionary/dictTypeEdit.html',
            controller: 'DictTypeEditCtrl'
        }).
        when('/dictTypeModify/:id', {
            templateUrl: './src/dictionary/dictTypeEdit.html',
            controller: 'DictTypeEditCtrl'
        }).
        when('/globalDict/', {
            templateUrl: './src/dictionary/globalDict.html',
            controller: 'GlobalDictCtrl'
        }).
        when('/globalDictAdd/', {
            templateUrl: './src/dictionary/globalDictEdit.html',
            controller: 'GlobalDictEditCtrl'
        }).
        when('/globalDictModify/:id', {
            templateUrl: './src/dictionary/globalDictEdit.html',
            controller: 'GlobalDictEditCtrl'
        }).
        when('/localDict/', {
            templateUrl: './src/dictionary/localDict.html',
            controller: 'LocalDictCtrl'
        }).
        when('/localDictAdd/', {
            templateUrl: './src/dictionary/localDictEdit.html',
            controller: 'LocalDictEditCtrl'
        }).
        when('/localDictModify/:id', {
            templateUrl: './src/dictionary/localDictEdit.html',
            controller: 'LocalDictEditCtrl'
        }).
        when('/404/', {
            templateUrl: './src/404.html'
        }).
        otherwise({
            redirectTo: '/404/'
        });
}).config(['$httpProvider', function($httpProvider) {
	$httpProvider.interceptors.push('ajaxInterceptor');
}]).run(function(resize, rest) {
    var base_url = '/management/';
    //var base_url = 'localhost:8000/management/';
    rest.init(base_url);
    resize();
    $(window).bind("resize", resize);
});