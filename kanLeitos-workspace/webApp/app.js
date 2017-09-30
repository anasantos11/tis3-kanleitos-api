var app = angular.module("kanleitos", ['ui.router']).config(function ($stateProvider, $urlRouterProvider, $locationProvider) {
	$locationProvider.html5Mode(false); 
	$urlRouterProvider.otherwise("/");

	$stateProvider
		.state("home", {
			url: "/",
			templateUrl: "index.html",
		})

		.state("cadastroPaciente", {
			url: "/cadastroPaciente",
			templateUrl: "internacao/cadastroPaciente.html"
		})
		
		$urlRouterProvider.otherwise("/teste");	
})






// LUIZ
/*(function e(t, n, r) { function s(o, u) { if (!n[o]) { if (!t[o]) { var a = typeof require == "function" && require; if (!u && a) return a(o, !0); if (i) return i(o, !0); var f = new Error("Cannot find module '" + o + "'"); throw f.code = "MODULE_NOT_FOUND", f } var l = n[o] = { exports: {} }; t[o][0].call(l.exports, function (e) { var n = t[o][1][e]; return s(n ? n : e) }, l, l.exports, e, t, n, r) } return n[o].exports } var i = typeof require == "function" && require; for (var o = 0; o < r.length; o++)s(r[o]); return s })({
	1: [function (require, module, exports) {
		const app = angular.module("kanleitos", [
			'ui.router'
		])

		require("./rotas/rotas.js")
		require("./controladores/controladores.js")
		require("./servicos/servicos.js")

		app.config(function ($urlRouterProvider, $stateProvider) {
			$urlRouterProvider.otherwise("/logar")
		})
	}, { "./controladores/controladores.js": 2, "./rotas/rotas.js": 3, "./servicos/servicos.js": 4 }], 2: [function (require, module, exports) {

	}, {}], 3: [function (require, module, exports) {
		const app = angular.module("kanleitos")

		app.config(function ($stateProvider,$urlRouterProvider) {
			
			$urlRouterProvider.otherwise('/');
			$stateProvider
			//-------------Internacao--------------
			.state("cadastroPaciente", {
				url: "/cadastroPaciente",
				controller : "cadastroPacienteController",
				templateUrl : "internacao/cadastroPaciente.html"
			})// novo

			.state("construcao", {
				url: "/construcao",
				templateUrl : "diversos/construcao.html"
			})// construcao
			
			.otherwise({
				redirectTo : "/"
			});
			 $locationProvider.html5Mode(true);
		})
		






	}, {}], 4: [function (require, module, exports) {
		arguments[4][2][0].apply(exports, arguments)
	}, { "dup": 2 }]
}, {}, [1]);


*/


