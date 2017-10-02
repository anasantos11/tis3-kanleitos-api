var app = angular.module('kanleitos', []);
app.controller('testeCtrl', ["$scope", "$http", "$filter", function ($scope, $http, $filter) {

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

	$scope.getPacientes = function () {
		$http.get('http://localhost:8080/Listar/paciente', JSON.stringify(request))
			.then(function (response) {
				debugger;
				console.log(response);
				if (!response.data.Resposta.erro) {
					swal(
						'Concluído!',
						'Cadastro feito com sucesso - ID Paciente: ' + response.data.idPaciente,
						'success'
					)
					$scope.NovoPaciente();
				}
			}, function (response) {
				debugger;
				swal(
					'Erro!',
					response.data.message,
					'error'
				)
			});
	}

	$scope.getDiagnosticos = function () {
		$http.get('http://localhost:8080/Diagnosticos')
			.then(function (response) {
				debugger;
				console.log(response);
				$scope.diagnosticos.diagnostico = response;
				if (!response.data.Resposta.erro) {
					swal(
						'Concluído!',
						'Cadastro feito com sucesso - ID Paciente: ' + response.data.idPaciente,
						'success'
					)
					$scope.NovoPaciente();
				}
			}, function (response) {
				debugger;
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
			debugger;
			if (!response.data.Resposta.erro) {
				swal(
					'Concluído!',
					'Cadastro feito com sucesso - ID Paciente: ' + response.data.idPaciente,
					'success'
				)
				$scope.NovoPaciente();
			}
		}, function (response) {
			debugger;
			swal(
				'Erro!',
				response.data.message,
				'error'
			)
		});



	}


}]);