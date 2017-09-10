const app = angular.module("hjk", [
    'ui.router'
])

require("./rotas/rotas.js")
require("./controladores/controladores.js")
require("./servicos/servicos.js")

app.config(function($urlRouterProvider, $stateProvider){
    $urlRouterProvider.otherwise("/logar")
})