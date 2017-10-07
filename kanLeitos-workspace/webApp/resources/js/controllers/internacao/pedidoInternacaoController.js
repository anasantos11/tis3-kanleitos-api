var app = angular.module('kanleitos', []);
app.controller('pedidoInternacaoController', ["$scope", "$http", "$filter", "pedidoInternacaoFactory", "diagnosticosFactory", "pacienteFactory", "alasFactory", function ($scope, $http, $filter, pedidoInternacaoFactory, diagnosticosFactory, pacienteFactory, alasFactory) {

    $scope.NovoPedido = function () {
        $scope.pedidoInternacao = {
            AIH: "",
            DataPedido: new Date(),
            Status: "",
            MedicoResponsavel: "",
            ResidenteResponsavel: "",
            DataAdmissao: "",
            NomePaciente: "",
            NomeMae: ""
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
                if (response.data != undefined) {
                    swal(
                        'Erro!',
                        response.data.message,
                        'error'
                    )
                } else {
                    swal(
                        'Erro!',
                        'Ocorreu algum erro no servidor',
                        'error'
                    )
                }
            });
    };
    $scope.CarregarAlas = function () {
        alasFactory.getAlas()
            .then(function (response) {
                $scope.Alas = response.data;
            }, function (response) {
                if (response.data != undefined) {
                    swal(
                        'Erro!',
                        response.data.message,
                        'error'
                    )
                } else {
                    swal(
                        'Erro!',
                        'Ocorreu algum erro no servidor',
                        'error'
                    )
                }
            });
    };
    $scope.GetPaciente = function () {
        pacienteFactory.getPaciente($scope.pedidoInternacao.NumProntuario, $scope.pedidoInternacao.NomeMae)
            .then(function (response) {
                $scope.pacient = response.data;
                $scope.pedidoInternacao.NomePaciente = response.data[0].nomePaciente;
                $scope.pedidoInternacao.Idade = response.data[0].idade;
                $scope.pedidoInternacao.DataNascimento = new Date(response.data[0].dataNascimento);
            }, function (response) {
                if (response.data != undefined) {
                    swal(
                        'Erro!',
                        response.data.message,
                        'error'
                    )
                } else {
                    swal(
                        'Erro!',
                        'Ocorreu algum erro no servidor',
                        'error'
                    )
                }
            });
    }
    $scope.getPacientes = function () {
        pacienteFactory.getPacientes()
            .then(function (response) {
                $scope.Pacientes = response.data;
            }, function (response) {
                if (response.data != undefined) {
                    swal(
                        'Erro!',
                        response.data.message,
                        'error'
                    )
                } else {
                    swal(
                        'Erro!',
                        'Ocorreu algum erro no servidor',
                        'error'
                    )
                }
            });
    }

    $scope.Inicializar();

    $scope.salvarPedidoInternacao = function () {
        $scope.pedidoInternacao.DataAdmissao = $filter('date')($scope.pedidoInternacao.DataAdmissao, 'yyyy-MM-dd HH:mm:ss:sss');
        $scope.pedidoInternacao.DataPedido = $filter('date')($scope.pedidoInternacao.DataPedido, 'yyyy-MM-dd HH:mm:ss:sss');

        var request = {
            AIH: $scope.pedidoInternacao.AIH,
            DataAdmissao: $scope.pedidoInternacao.DataAdmissao,
            DataPedido: $scope.pedidoInternacao.DataPedido,
            IdAla: $scope.pedidoInternacao.IdAla,
            IdDiagnostico: $scope.pedidoInternacao.IdDiagnostico,
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
                $scope.pedidoInternacao.DataNascimento = new Date($scope.pedidoInternacao.DataNascimento);
                $scope.pedidoInternacao.DataPedido = new Date($scope.pedidoInternacao.DataPedido);
                $scope.pedidoInternacao.DataAdmissao = new Date($scope.pedidoInternacao.DataAdmissao);
                if (response.data != undefined) {
                    swal(
                        'Erro!',
                        response.data.message,
                        'error'
                    )
                } else {
                    swal(
                        'Erro!',
                        'Ocorreu algum erro no servidor',
                        'error'
                    )
                }
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