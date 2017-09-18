(function e(t,n,r){function s(o,u){if(!n[o]){if(!t[o]){var a=typeof require=="function"&&require;if(!u&&a)return a(o,!0);if(i)return i(o,!0);var f=new Error("Cannot find module '"+o+"'");throw f.code="MODULE_NOT_FOUND",f}var l=n[o]={exports:{}};t[o][0].call(l.exports,function(e){var n=t[o][1][e];return s(n?n:e)},l,l.exports,e,t,n,r)}return n[o].exports}var i=typeof require=="function"&&require;for(var o=0;o<r.length;o++)s(r[o]);return s})({1:[function(require,module,exports){
require("./rotas/rotas.js")
require("./controladores/controladores.js")
require("./servicos/servicos.js")

const app = angular.module("hjk", [
    'ui.router'
])

app.config(function($urlRouterProvider){
    $urlRouterProvider.otherwise("/logar")
})
},{"./controladores/controladores.js":2,"./rotas/rotas.js":4,"./servicos/servicos.js":5}],2:[function(require,module,exports){
const app = angular.module("hjk")

require("./login/loginCtrl.js")
},{"./login/loginCtrl.js":3}],3:[function(require,module,exports){
const Controladores = angular.module("controladores")
},{}],4:[function(require,module,exports){
const app = angular.module("hjk")

app.config(function($){
    $stateProvider
    .state("login", {
        url: "/logar",
        templateUrl: "app/templates/login/index.html"
    })
    .state("teste", {
        url: "/testar",
        templateUrl: "app/templates/login/teste.html"
    })
})
},{}],5:[function(require,module,exports){

},{}]},{},[1]);
