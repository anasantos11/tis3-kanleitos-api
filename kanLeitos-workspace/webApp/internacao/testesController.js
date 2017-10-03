var app = angular.module('kanleitos', []);
app.controller('testeCtrl', ["$scope", "$http", "$filter", "diagnosticosFactory", "pacienteFactory", "alasFactory", function ($scope, $http, $filter, diagnosticosFactory, pacienteFactory, alasFactory) {

	$scope.NovoPaciente = function () {
		$scope.paciente = {
			prontuario: "",
		}
	}
	$scope.NovoPaciente();

	$scope.NovoDiagnostico = function () {
		$scope.diagnosticos = {
			diagnostico: "",
		}
	}

	$scope.NovoDiagnostico();
	var request = {
		NumProntuario: $scope.paciente.prontuario
	}

	$scope.getDiagnosticos = function () {
		diagnosticosFactory.getDiagnosticos()
			.then(function (response) {
				$scope.Diagnosticos = response.data;
			}, function (response) {
				swal(
					'Erro!',
					response.data.message,
					'error'
				)
			});
	}

	$scope.getAlas = function () {
		alasFactory.getAlas()
			.then(function (response) {
				$scope.Alas = response.data;
			}, function (response) {
				swal(
					'Erro!',
					response.data.message,
					'error'
				)
			});
	}
	$scope.getPacientes = function () {
		pacienteFactory.getPacientes()
			.then(function (response) {
				$scope.Pacientes = response.data;
			}, function (response) {
				swal(
					'Erro!',
					response.data.message,
					'error'
				)
			});
	}

	$scope.getPaciente = function () {
		pacienteFactory.getPaciente($scope.paciente.prontuario)
			.then(function (response) {
				$scope.pacient = response.data;
			}, function (response) {
				swal(
					'Erro!',
					response.data.message,
					'error'
				)
			});
	}



	$scope.salvarPacienteBanco = function () {

		var request = {
			NomePaciente: $scope.paciente.nome,
			NumProntuario: $scope.paciente.prontuario,
			Idade: $scope.paciente.idade,
			NomeMae: $scope.paciente.mae,
			DataNascimento: $scope.paciente.nascimento,
			Genero: $scope.paciente.sexo,
		};

		$http.defaults.headers.post["Content-Type"] = "application/x-www-form-urlencoded; charset=utf-8";


		$http.post('http://localhost:8080/Cadastro/paciente', JSON.stringify(request)).then(function (response) {
			if (!response.data.Resposta.erro) {
				swal(
					'Concluído!',
					'Cadastro feito com sucesso - ID Paciente: ' + response.data.idPaciente,
					'success'
				)
				$scope.NovoPaciente();
			}
		}, function (response) {
			swal(
				'Erro!',
				response.data.message,
				'error'
			)
		});



	}


}]);
//Factorys
app.factory('diagnosticosFactory', function ($http) {
	var diagnosticos = {};
	//Get Diagnosticos
	diagnosticos.getDiagnosticos = function () {
		return $http({
			url: "http://localhost:8080/Diagnosticos",
			method: 'GET'
		});
	};
	return diagnosticos;
});
app.factory('pacienteFactory', function ($http) {
	var pacientes = {};
	//Get Diagnosticos
	pacientes.getPacientes = function () {
		return $http({
			url: "http://localhost:8080/ListaPacientes",
			method: 'GET'
		});
	};
	//Get Paciente pelo numProntuario
	pacientes.getPaciente = function (prontuario) {
		return $http({
			url: "http://localhost:8080/Paciente",
			method: 'GET',
			params: { numProntuario: prontuario }
		});
	};
	//Salvar Pacientes
	pacientes.savePaciente = function (dados) {
		return $http({
			url: 'http://localhost:8080/CadastroPaciente',
			method: 'POST',
			data: dados
		});
	};
	return pacientes;
});
app.factory('alasFactory', function ($http) {
	var alas = {};
	//Get alas
	alas.getAlas = function () {
		return $http({
			url: "http://localhost:8080/Alas",
			method: 'GET'
		});
	};
	return alas;
});