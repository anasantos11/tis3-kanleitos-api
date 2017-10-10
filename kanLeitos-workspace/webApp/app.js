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

	$stateProvider.state("pedidoInternacao", {
		url: "/pedidoInternacao",
		templateUrl: "internacao/pedidoInternacao.html"
	});
})