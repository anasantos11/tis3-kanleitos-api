//var app = angular.module("kanleitos", []);
//Factorys
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
    leitos.getLeitoEnfermaria = function (idEnfermaria) {
    return $http({
        url: "http://localhost:8080/GetLeitosEnfermaria",
        method: 'GET',
        params: { idEnfermaria: idEnfermaria }
    });
    };
    return leitos;
});
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
    pedido.getPedido = function (prontuario) {
        return $http({
            url: "http://localhost:8080/GetPedidoInternacao",
            method: 'GET',
            params: { numProntuario: prontuario }
        });
    };
    pedido.getPedidos = function () {
    return $http({
        url: "http://localhost:8080/ListaPedidos",
        method: 'GET'
    });
    };
    return pedido;
});
app.factory('registroInternacaoFactory', function ($http) {
    var registro = {};
    //Salvar Registro Internacao
    registro.saveRegistroInternacao = function (dados) {
        return $http({
            url: 'http://localhost:8080/RegistroInternacao',
            method: 'POST',
            data: dados
        });
    };
    return registro;
});

app.factory('usuarioFactory', function ($http) {
    var usuario = {};
    usuario.login = function (dados) {
        return $http({
            url: 'http://localhost:8080/Login',
            method: 'POST',
            data: dados
        });
    };
    return usuario;
});