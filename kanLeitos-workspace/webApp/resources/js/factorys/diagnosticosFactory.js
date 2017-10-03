angular.module('kanleitos', []).
 factory('diagnosticosFactory', function($http) {
    var diagnosticos = {};
    //Get Diagnosticos
    diagnosticos.getDiagnosticos = function() {
      return $http({
            url: "http://localhost:8080/Diagnosticos",
            method: 'GET'
           });
    };
    return diagnosticos;
  });