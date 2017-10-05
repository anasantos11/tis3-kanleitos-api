var app = angular.module('kanleitos', []);
app.controller('pedidoInternacaoController', ["$scope", "$http", "$filter", "pedidoInternacaoFactory", "diagnosticosFactory", "pacienteFactory", "alasFactory", function ($scope, $http, $filter, pedidoInternacaoFactory, diagnosticosFactory, pacienteFactory, alasFactory) {

    $scope.NovoPedido = function () {
        $scope.pedidoInternacao = {
            AIH: "",
            DataPedido: new Date(),
            Status: "",
            MedicoResponsavel: "",
            ResidenteResponsavel: "",
            DataAdmissao: ""
        }
    }



    $scope.Inicializar = function () {
        $scope.NovoPedido();
        $scope.CarregarDiagnosticos();
        $scope.CarregarAlas();
    }


    $scope.CarregarDiagnosticos = function () {
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
    };
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

    $scope.Inicializar();

    $scope.salvarPedidoInternacao = function () {
        $scope.pedidoInternacao.DataAdmissao = $filter('date')($scope.pedidoInternacao.DataAdmissao, 'yyyy-MM-dd HH:mm:ss:sss');
        $scope.pedidoInternacao.DataPedido = $filter('date')($scope.pedidoInternacao.DataPedido, 'yyyy-MM-dd HH:mm:ss:sss');

        /*var query = $scope.pedidoInternacao.IdAla;
        $scope.pedidoInternacao.Ala = $scope.Alas.forEach(function(query){
            return $scope.Alas.idAla == query;
        });*/
        
        
        /*function filterItems(query) {
            return Alas.filter(function (obj) {
                return obj.idAla == query;                
            })
        }*/


        /*$scope.pedidoInternacao.Ala =  function getAla(){
            return Alas.filter(function(obj){
                return obj.idAla == pedidoInternacao.Ala.idAla;
            })
        }    */

        var request = {
            AIH: $scope.pedidoInternacao.AIH,
            DataAdmissao: $scope.pedidoInternacao.DataAdmissao,
            DataPedido: $scope.pedidoInternacao.DataPedido,
            //Ala: $scope.pedidoInternacao.Ala,
            IdAla: $scope.pedidoInternacao.IdAla,
            IdDiagnostico: $scope.pedidoInternacao.IdDiagnostico,
            //Diagnostico: $scope.pedidoInternacao.Diagnostico,
            MedicoResponsavel: $scope.pedidoInternacao.MedicoResponsavel,
            NumProntuario: $scope.pedidoInternacao.NumProntuario,
            ResidenteResponsavel: $scope.pedidoInternacao.ResidenteResponsavel,
            Status: $scope.pedidoInternacao.Status
        }

        $http.defaults.headers.post["Content-Type"] = "application/x-www-form-urlencoded; charset=utf-8";
        pedidoInternacaoFactory.savePedidoInternacao(request)
            .then(function (response) {
                if (!response.data.Resposta.erro) {
                    swal(
                        'Concluído!',
                        'Cadastro feito com sucesso - ID Pedido: ' + response.data.idPedidoInternacao,
                        'success'
                    )
                    $scope.NovoPedido();
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