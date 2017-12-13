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
	"720kb.tooltips",
	"ui.utils.masks"]);

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
		templateUrl: "login.html"
	});

	$stateProvider.state("home", {
		url: "/home",
		templateUrl: "dashboard/kanBam.html",
	});

	$stateProvider.state("emConstrucao", {
		url: "/emConstrucao",
		templateUrl: "erro.html",
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

