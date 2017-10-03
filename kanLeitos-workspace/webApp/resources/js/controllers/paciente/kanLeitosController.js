var app = angular.module('kanleitos', []);
app.controller('kanLeitosController', ["$scope", "$http", "$filter", "pacienteFactory", function ($scope, $http, $filter, pacienteFactory) {


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
			url: "http://localhost:8080/Pacientes",
			method: 'GET'
		});
	};
	//Salvar Pacientes
	pacientes.savePaciente = function (dados) {
		return $http({
			url: 'http://localhost:8080/Cadastro/paciente',
			method: 'POST',
			data: dados
		});
	};
	return pacientes;
});