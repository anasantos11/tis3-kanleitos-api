const app = angular.module("hjk")

app.config(function($stateProvider){
    $stateProvider
    .state("login", {
        url: "/logar",
        templateUrl: "app/templates/login/index.html"
    })
    .state("teste", {
        url: "/testar",
        templateUrl: "app/templates/login/index.html"
    })
})