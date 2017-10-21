var app = angular.module("kanleitos", ['ui.router']);

app.config(function ($stateProvider, $urlRouterProvider, $locationProvider) {
	$urlRouterProvider.otherwise("/");

	$stateProvider.state("home", {
		url: "/home",
		templateUrl: "dashboard/kanBam.html",
	});

	$stateProvider.state("emConstrucao", {
		url: "/emConstrucao",
		templateUrl: "erro.html",
	});

	$stateProvider.state("login", {
		url: "/login",
		templateUrl: "login.html",
	});

	$stateProvider.state("cadastroPaciente", {
		url: "/cadastroPaciente",
		templateUrl: "internacao/cadastroPaciente.html"
	});

	$stateProvider.state("pedidoInternacao", {
		url: "/pedidoInternacao",
		templateUrl: "internacao/pedidoInternacao.html"
	});

	$stateProvider.state("registroInternacao", {
		url: "/registroInternacao",
		templateUrl: "internacao/registroInternacao.html"
	});

	$stateProvider.state("leitos", {
		url: "/leitos",
		templateUrl: "relatorios/leitos.html"
	});
})