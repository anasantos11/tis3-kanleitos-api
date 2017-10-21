app.controller('registroInternacaoController', ["$scope", "$http", "$filter", "pedidoInternacaoFactory", "diagnosticosFactory", "alasFactory", "enfermariaFactory", "leitoFactory", function ($scope, $http, $filter, pedidoInternacaoFactory, diagnosticosFactory, alasFactory, enfermariaFactory, leitoFactory) {

    $scope.NovoRegistro = function () {
        $scope.registroInternacao = {
            numProntuario: null,
            AIH: "",
            nomePaciente: "",
            nomeMae: "",
            dataNascimento: "",
            idade: null,
            genero: "",
            dataAdmissao: "",
            medicoResponsavel: "",
            residenteResponsavel: "",
            dataInternacao: "",
            previsaoAlta: ""
        }
    }

    $scope.Inicializar = function () {
        $scope.NovoRegistro();
        $scope.CarregarDiagnosticos();
        $scope.CarregarAlas();
        $scope.CarregarEnfermarias();
        $scope.CarregarLeitos();

    }

    $scope.carregarLeitosEnfermaria = function(enfermaria){
       $scope.Leitos = $scope.Leitos.filter(function(obj){
                    return obj.enfermaria.idEnfermaria == enfermaria;
     })
    };

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
    $scope.CarregarEnfermarias = function () {
        enfermariaFactory.getEnfermarias()
            .then(function (response) {
                $scope.Enfermarias = response.data;
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
    $scope.CarregarLeitos = function () {
        leitoFactory.getLeitos()
            .then(function (response) {
                $scope.Leitos = response.data;
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
    $scope.Inicializar();

    $scope.GetPedido = function () {

        pedidoInternacaoFactory.getPedido($scope.pedidoInternacao.paciente.numProntuario)
            .then(function (response) {
                $scope.pedidoInternacao = response.data;
                $scope.pedidoInternacao.paciente.dataNascimento = new Date($scope.pedidoInternacao.paciente.dataNascimento);
                $scope.pedidoInternacao.dataAdmissao = new Date($scope.pedidoInternacao.dataAdmissao);
                $scope.pedidoInternacao.AIH =  parseInt($scope.pedidoInternacao.AIH);
                $scope.pedidoInternacao.ala = $scope.Alas.filter(function(obj){
                    return obj.idAla == $scope.pedidoInternacao.ala.idAla;
                })[0];
                $scope.pedidoInternacao.diagnostico = $scope.Diagnosticos.filter(function(obj){
                    return obj.idDiagnostico == $scope.pedidoInternacao.diagnostico.idDiagnostico;
                })[0];

            }, function(response){
                swal(
                    'Erro!',
                    'Pedido de Internação não encontrado',
                    'error'
                )
            });
    }

    $scope.calcularIdade = function () {
        const nasc = new Date($scope.registroInternacao.dataNascimento)

        if($scope.registroInternacao.dataNascimento && nasc.toLocaleDateString().length){
            var idadeP = new Date() - new Date($scope.registroInternacao.dataNascimento).getTime();
            var idadeData = new Date(idadeP);
            var idade = idadeData.getUTCFullYear() - 1970;
            if (!isNaN(idade) && idade != undefined) {
                $scope.registroInternacao.idade = idade;
            } else {
                $scope.registroInternacao.idade = 0;
            }
        }else{
            $scope.registroInternacao.idade = null
        }

    }
}]);
