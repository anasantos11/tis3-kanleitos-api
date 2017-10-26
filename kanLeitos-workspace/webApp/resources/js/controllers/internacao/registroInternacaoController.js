app.controller('registroInternacaoController', ["$scope", "$http", "$filter", "registroInternacaoFactory", "pedidoInternacaoFactory", "diagnosticosFactory", "alasFactory", "enfermariaFactory", "leitoFactory", function ($scope, $http, $filter, registroInternacaoFactory, pedidoInternacaoFactory, diagnosticosFactory, alasFactory, enfermariaFactory, leitoFactory) {

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

    $scope.carregarLeitosEnfermaria = function (enfermaria) {
        leitoFactory.getLeitoEnfermaria(enfermaria)
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

    $scope.carregarPedidos = function () {
    pedidoInternacaoFactory.getPedidos()
        .then(function (response) {
            $scope.ListaPedidos = response.data;
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

    $scope.GetPedido = function () {
        setTimeout(function () {
        pedidoInternacaoFactory.getPedido($scope.pedidoInternacao.paciente.numProntuario)
            .then(function (response) {
                $scope.pedidoInternacao = response.data;
                $scope.pedidoInternacao.paciente.dataNascimento = new Date($scope.pedidoInternacao.paciente.dataNascimento);
                $scope.pedidoInternacao.dataAdmissao = new Date($scope.pedidoInternacao.dataAdmissao);
                $scope.pedidoInternacao.AIH = parseInt($scope.pedidoInternacao.AIH);
                $scope.pedidoInternacao.ala = $scope.Alas.filter(function (obj) {
                    return obj.idAla == $scope.pedidoInternacao.ala.idAla;
                })[0];
                $scope.pedidoInternacao.diagnostico = $scope.Diagnosticos.filter(function (obj) {
                    return obj.idDiagnostico == $scope.pedidoInternacao.diagnostico.idDiagnostico;
                })[0];

            }, function (response) {
                    swal(
                        'Erro!',
                        'Pedido de Internação não encontrado',
                        'error'
                    )
            });
        }, 2000);
    }
    $scope.salvarRegistroInternacao = function () {
        if ($scope.validarRegistroInternacao()) {
        $scope.registroInternacao.idPedido = $scope.pedidoInternacao.idPedidoInternacao;
        $scope.registroInternacao.dataInternacao = $filter('date')($scope.registroInternacao.dataInternacao, 'yyyy-MM-dd HH:mm:ss');
        $scope.registroInternacao.previsaoAlta = $filter('date')($scope.registroInternacao.previsaoAlta, 'yyyy-MM-dd HH:mm:ss');


        $http.defaults.headers.post["Content-Type"] = "application/x-www-form-urlencoded; charset=utf-8";
        registroInternacaoFactory.saveRegistroInternacao($scope.registroInternacao)
            .then(function (response) {
                swal(
                    'Concluído!',
                    'Internação realizada com sucesso, nº ' + response.data.idRegistroInternacao,
                    'success'
                )
                $scope.NovoRegistro();
            }, function (response) {
                $scope.registroInternacao.dataInternacao = new Date($scope.registroInternacao.dataInternacao);
                $scope.registroInternacao.previsaoAlta = new Date($scope.registroInternacao.previsaoAlta);
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
            }

            );
        }
    }

    $scope.calcularIdade = function () {
        const nasc = new Date($scope.registroInternacao.dataNascimento)

        if ($scope.registroInternacao.dataNascimento && nasc.toLocaleDateString().length) {
            var idadeP = new Date() - new Date($scope.registroInternacao.dataNascimento).getTime();
            var idadeData = new Date(idadeP);
            var idade = idadeData.getUTCFullYear() - 1970;
            if (!isNaN(idade) && idade != undefined) {
                $scope.registroInternacao.idade = idade;
            } else {
                $scope.registroInternacao.idade = 0;
            }
        } else {
            $scope.registroInternacao.idade = null
        }

    }

    $scope.validarRegistroInternacao = function () {
        if ($scope.pedidoInternacao == undefined) {
            swal(
                'Erro!',
                'Pedido de Internação não encontrado!',
                'error'
            )
            return;
        }
        if ($scope.pedidoInternacao.idPedidoInternacao <= 0) {
            swal(
                'Erro!',
                'Pedido de Internação não encontrado!',
                'error'
            )
            return;
        }
        if ($scope.registroInternacao.idEnfermaria == undefined) {
            swal(
                'Erro!',
                'Escolha uma enfermaria!',
                'error'
            )
            return;
        }
        if ($scope.registroInternacao.idLeito  == undefined ) {
            swal(
                'Erro!',
                'Escolha um leito!',
                'error'
            )
            return;
        }
        if ($scope.registroInternacao.dataInternacao == "") {
            swal(
                'Erro!',
                'Digite a data de internação do paciente!',
                'error'
            )
            return;
        }
        if ($scope.registroInternacao.previsaoAlta == "") {
            swal(
                'Erro!',
                'Digite a previsão de alta do paciente!',
                'error'
            )
            return;
        }
    return true;
    };
}]);
