const rota = angular.module("hjk")
rota.config(function($stateProvider){
    $stateProvider.state("teste", {
        url: "/testar",
        templateUrl: "app/templates/login/teste.html"
    })
})