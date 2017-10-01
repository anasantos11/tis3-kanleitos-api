var app = angular.module("kanleitos", ['ui.router']);

app.config(function ($stateProvider, $urlRouterProvider, $locationProvider) {
	$urlRouterProvider.otherwise("/");

	$stateProvider.state("home", {
		url: "/home",
		templateUrl: "index.html",
	});

	$stateProvider.state("cadastroPaciente", {
		url: "/cadastroPaciente",
		templateUrl: "internacao/cadastroPaciente.html"
	});

	$stateProvider.state("xxx", {
		url: "/testex",
		templateUrl: "x.html"
	});

	$stateProvider.state("yyy", {
		url: "/testey",
		templateUrl: "y.html"
	});
})