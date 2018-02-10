// Initialize Firebase
var config = {
	apiKey: "AIzaSyB5CnszQphntRXBUpc8Kisk1_WqOJesaKo",
	authDomain: "kanleitos.firebaseapp.com",
	databaseURL: "https://kanleitos.firebaseio.com",
	projectId: "kanleitos",
	storageBucket: "kanleitos.appspot.com",
	messagingSenderId: "573530601324"
};
firebase.initializeApp(config);

var isLogado = false;

var app = angular.module("kanleitos", [
	'firebase',
	'ui.router',
	"ngDialog",
	"720kb.tooltips"]);

app.controller('KanController', ['$rootScope', '$scope', '$state', '$firebaseAuth', function ($rootScope, $scope, $state, $firebaseAuth) {
	$scope.auth = $firebaseAuth();
	$scope.auth.$onAuthStateChanged(function (user) {
		if (user != null) {
			$scope.nomeUsuario = user.displayName;
			$scope.emailUsuario = user.email;
			isLogado = true;
			location.href = "#!/home";
		}
	});

	$scope.sairAplicacao = function () {
		firebase.auth().signOut()
			.then(function () {
				$('#sairAppModal').modal('hide');
				location.href = "#!/login";
				isLogado = false;
			}).catch(function (error) {
				swal(
					"Algum erro ocorreu, tente novamente!",
					"",
					error
				)
			});

	};
}]);


app.config(function ($stateProvider, $urlRouterProvider, $locationProvider) {
	$urlRouterProvider.otherwise("/login");

	$stateProvider.state("login", {
		url: "/login",
		templateUrl: "templates/login.html"
	});

	$stateProvider.state("home", {
		url: "/home",
		templateUrl: "templates/dashboard/kanban-leitos.html",
	});

	$stateProvider.state("emConstrucao", {
		url: "/emConstrucao",
		templateUrl: "templates/erro.html",
	});

	$stateProvider.state("cadastroPaciente", {
		url: "/cadastroPaciente",
		templateUrl: "templates/internacao/cadastroPaciente.html"
	});

	$stateProvider.state("pedidoInternacao", {
		url: "/pedidoInternacao",
		templateUrl: "templates/internacao/pedidoInternacao.html"
	});

	$stateProvider.state("registroInternacao", {
		url: "/registroInternacao",
		templateUrl: "templates/internacao/registroInternacao.html"
	});

	$stateProvider.state("leitos", {
		url: "/leitos",
		templateUrl: "templates/relatorios/leitos.html"
	});

	$stateProvider.state("relatorioPedidosEmAberto", {
		url: "/relatorioPedidosEmAberto",
		templateUrl: "templates/relatorios/pedidoInternacaoEmAberto.html"
	});
})

app.run(['$rootScope', '$location',
	function ($rootScope, $location) {

		$rootScope.$on('$locationChangeStart', function (event, next, current) {
			var rota = $location.path();
			if (rota == '/login' || rota == '/' || rota == '') {
				$rootScope.IsLogin = true;
			} else if (rota != '/' && rota != '') {
				$rootScope.IsLogin = false;
				if (!isLogado) {
					event.preventDefault();
					location.href = "#!/login";
				}
			}
		});
	}]);

