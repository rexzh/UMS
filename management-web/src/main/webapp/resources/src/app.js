var app = angular.module('AppModule', ['ngRoute', 'http.service', 'common', 'metro.directive', 'l10n']);

app.constant('resetMenu', function () {
    console.log("reset!");
    var mn = $("#sidebar-left").first();
    mn.find("a").each(function () {
        if ($(this).attr('href') == '#/dashboard/')
            $(this).parent().addClass('active');
        else
            $(this).parent().removeClass('active');
    });
});

//app.constant('$base_url', 'http://localhost:8000/management');
app.constant('$base_url', '');

app.constant('resize', function(){
    var winHeight = $(window).height();
    var winWidth = $(window).width();

    var contentHeight = $("#content").height();

    if (winHeight) {
        console.log("resize to winHeight");
        $("#content").css("min-height", winHeight/* - 41 * 2 - 6*/);
    }

    if (contentHeight) {
        console.log("resize to contentHeight");
        $("#sidebar-left2").css("height", contentHeight);
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
        when('/submenu/', {
            templateUrl: './src/submenu/submenu.html',
            controller: 'SubmenuCtrl'
        }).
        when('/submenuAdd/', {
            templateUrl: './src/submenu/submenuEdit.html',
            controller: 'SubmenuEditCtrl'
        }).
        when('/submenuModify/:id', {
            templateUrl: './src/submenu/submenuEdit.html',
            controller: 'SubmenuEditCtrl'
        }).
        when('/404/', {
            templateUrl: './src/404.html'
        }).
        otherwise({
            redirectTo: '/404/'
        });
}).config(['$httpProvider', function($httpProvider) {
	$httpProvider.interceptors.push('ajaxInterceptor');
}]).run(function(resize) {
    $(window).bind("resize", resize);
});