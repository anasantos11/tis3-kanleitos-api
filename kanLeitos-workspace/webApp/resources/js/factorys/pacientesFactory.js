angular.module('kanleitos', []).
  factory('pacienteFactory', function ($http) {
    var pacientes = {};
    //Get Diagnosticos
    pacientes.getPacientes = function () {
      return $http({
        url: "http://localhost:8080/ListaPacientes",
        method: 'GET'
      });
    };
    //Get Paciente pelo numProntuario ou nomeMae
    pacientes.getPaciente = function (prontuario, mae) {
      return $http({
        url: "http://localhost:8080/Paciente",
        method: 'GET',
        params: { numProntuario: prontuario, nomeMae: mae }
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