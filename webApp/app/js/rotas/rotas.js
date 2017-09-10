const app = angular.module("hjk")

app.config(function($stateProvider){
    
    $stateProvider.state("login", {
        url: "/logar",
        templateUrl: "app/templates/login/index.html"
    })
})