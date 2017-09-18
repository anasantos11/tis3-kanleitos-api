require("./rotas/rotas.js")
require("./controladores/controladores.js")
require("./servicos/servicos.js")

const app = angular.module("hjk", [
    'ui.router'
])

app.config(function($urlRouterProvider){
    $urlRouterProvider.otherwise("/logar")
})