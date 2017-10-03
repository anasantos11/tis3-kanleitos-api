angular.module('kanleitos', []).
 factory('pacienteFactory', function($http) {
    var pacientes = {};
    //Get Diagnosticos
    pacientes.getPacientes = function() {
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
            data : dados
        });
    };
    return pacientes;
  });