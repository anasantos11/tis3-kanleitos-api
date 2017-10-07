var app = angular.module('kanleitos', []);
app.controller('EstruturaCtrl', ["$scope", "$http", "$filter", "alasFactory", "enfermariaFactory", "leitoFactory", function ($scope, $http, $filter, alasFactory, enfermariaFactory, leitoFactory) {

    $scope.Estrutura = {
        leitos: [
            { nomeAla: 'Ala A', nomeEnfermaria: 'Enfermaria E', nomeLeito: 'Leito-01' },
            { nomeAla: 'Ala B', nomeEnfermaria: 'Enfermaria D', nomeLeito: 'Leito-01' },
            { nomeAla: 'Ala C', nomeEnfermaria: 'Enfermaria C', nomeLeito: 'Leito-01' },
            { nomeAla: 'Ala D', nomeEnfermaria: 'Enfermaria B', nomeLeito: 'Leito-01' },
            { nomeAla: 'Ala E', nomeEnfermaria: 'Enfermaria A', nomeLeito: 'Leito-01' },
        ],
    };

    $scope.Inicializar = function () {
        $scope.CarregarLeitos(); 
    }

    $scope.CarregarAlas = function () {
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
    };
    $scope.CarregarEnfermarias = function () {
        enfermariaFactory.getEnfermarias()
            .then(function (response) {
                $scope.Enfermarias = response.data;
            }, function (response) {
                swal(
                    'Erro!',
                    response.data.message,
                    'error'
                )
            });
    };
    $scope.CarregarLeitos = function () {
        leitoFactory.getLeitos()
            .then(function (response) {
                $scope.Leitos = response.data;
            }, function (response) {
                swal(
                    'Erro!',
                    response.data.message,
                    'error'
                )
            });
    };

    $scope.Inicializar();


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

app.factory('pedidoInternacaoFactory', function ($http) {
    var pedido = {};
    //Salvar Pedido Internacao
    pedido.savePedidoInternacao = function (dados) {
        return $http({
            url: 'http://localhost:8080/PedidoInternacao',
            method: 'POST',
            data: dados
        });
    };
    return pedido;
});
app.factory('enfermariaFactory', function ($http) {
    var enfermarias = {};
    //Get Enfermarias
    enfermarias.getEnfermarias = function () {
        return $http({
            url: "http://localhost:8080/Enfermarias",
            method: 'GET'
        });
    };
    return enfermarias;
});

app.factory('leitoFactory', function ($http) {
    var leitos = {};
    //Get Leitos
    leitos.getLeitos = function () {
        return $http({
            url: "http://localhost:8080/Leitos",
            method: 'GET'
        });
    };
    return leitos;
});