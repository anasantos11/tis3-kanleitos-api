//var app = angular.module("kanleitos", []);
//Factorys

var DEV_HJK = "https://kanleitoshjk-service.azurewebsites.net/";

app.factory('alasFactory', function ($http) {
    var alas = {};
    //Get alas
    alas.getAlas = function () {
        return $http({
            url: DEV_HJK + "Alas",
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
            url: DEV_HJK + "Enfermarias",
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
            url: DEV_HJK + "Leitos",
            method: 'GET'
        });
    };
    leitos.getLeitoEnfermaria = function (idEnfermaria) {
    return $http({
        url: DEV_HJK + "GetLeitosEnfermaria",
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
            url: DEV_HJK + "Diagnosticos",
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
            url: DEV_HJK + "ListaPacientes",
            method: 'GET'
        });
    };
    //Get Paciente pelo numProntuario ou nomeMae
    pacientes.getPaciente = function (prontuario, mae) {
        return $http({
            url: DEV_HJK + "Paciente",
            method: 'GET',
            params: { numProntuario: prontuario, nomeMae: mae }
        });
    };
    //Salvar Pacientes
    pacientes.savePaciente = function (dados) {
        return $http({
            url: DEV_HJK + "CadastroPaciente",
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
            url: DEV_HJK + "PedidoInternacao",
            method: 'POST',
            data: dados
        });
    };
    pedido.getPedido = function (prontuario) {
        return $http({
            url: DEV_HJK + "GetPedidoInternacao",
            method: 'GET',
            params: { numProntuario: prontuario }
        });
    };
    pedido.getPedidos = function () {
    return $http({
        url: DEV_HJK + "ListaPedidos",
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
            url: DEV_HJK + "RegistroInternacao",
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
            url: DEV_HJK + "Login",
            method: 'POST',
            data: dados
        });
    };
    return usuario;
});

app.factory("kanbanFactory", function($http){
    var kanban = {};
    kanban.getRegistrosPorClassificação = function(tipoClassificacao){
        return $http({
            url: DEV_HJK + "KanbanInternacoes", 
            method: 'GET',
            params:  {classificacao: "" + tipoClassificacao}
        });
    }

    kanban.atualizaRegistrosInternacao = function(tipoClassificacao){
        return $http({
            url: DEV_HJK + "AtualizarInternacoes", 
            method: 'GET'
        });
    }

    return kanban;
})