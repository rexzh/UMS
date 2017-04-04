'use strict';
var l10n = angular.module('l10n', []);    
    
var _l = 'en_us';
var _data = null;
var _promise = null;
var _packages = {};
    
l10n.addResource = function (l, data) {
    _packages[l] = data;
};

l10n.provider('$L', function () {
    this.setLocale = function (l) {
        if (!l)
            _l = 'en_us';
        else
            _l = l;
    };

    this.register = function (l, data) {
        _packages[l] = data;
    };
        
    this.$get = function ($http, $q) {            
        _data = _packages[_l];
            
        return function (str) {
            if (_data && _data[str])
                return _data[str];
            else
                return str;
        };
    }
});