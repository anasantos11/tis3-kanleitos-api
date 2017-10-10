app.controller('pedidoInternacaoController', ["$scope", "$http", "$filter", "pedidoInternacaoFactory", "diagnosticosFactory", "pacienteFactory", "alasFactory", function ($scope, $http, $filter, pedidoInternacaoFactory, diagnosticosFactory, pacienteFactory, alasFactory) {

    $scope.NovoPedido = function () {
        $scope.pedidoInternacao = {
            numProntuario: 0,
            nomePaciente: "",
            nomeMae: "",
            dataNascimento: "",
            idade: 0,
            genero: "",
            AIH: "",
            dataPedido: new Date(),
            status: "Pendente",
            medicoResponsavel: "",
            residenteResponsavel: "",
            dataAdmissao: ""
        }
    }



    $scope.Inicializar = function () {
        $scope.NovoPedido();
        $scope.CarregarDiagnosticos();
        $scope.CarregarAlas();
    }

    $scope.cadastrarPaciente = function () {
        if ($scope.validarDadosPaciente()) {
            $scope.pedidoInternacao.dataNascimento = $filter('date')($scope.pedidoInternacao.dataNascimento, 'yyyy-MM-dd');
            $http.defaults.headers.post["Content-Type"] = "application/x-www-form-urlencoded; charset=utf-8";
            pacienteFactory.savePaciente($scope.pedidoInternacao)
                .then(function (response) {
                    swal(
                        'Concluído!',
                        'Cadastro feito com sucesso - Paciente: ' + response.data.nomePaciente,
                        'success'
                    )
                    $('#cadastroPaciente').modal('hide');
                    $scope.NovoPedido();
                }, function (response) {
                    $scope.pedidoInternacao.dataNascimento = new Date($scope.pedidoInternacao.dataNascimento);
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
    }
    $scope.carregarPacientes = function () {
        pacienteFactory.getPacientes()
            .then(function (response) {
                $scope.ListaPacientes = response.data;
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
    $scope.GetPaciente = function () {
        setTimeout(function () {
            pacienteFactory.getPaciente($scope.pedidoInternacao.numProntuario, $scope.pedidoInternacao.nomeMae)
                .then(function (response) {
                    if (response.data.length > 0) {
                        $scope.pedidoInternacao.nomePaciente = response.data[0].nomePaciente;
                        $scope.pedidoInternacao.nomeMae = response.data[0].nomeMae;
                        $scope.pedidoInternacao.idade = response.data[0].idade;
                        $scope.pedidoInternacao.dataNascimento = new Date(response.data[0].dataNascimento);
                        $scope.pedidoInternacao.genero = response.data[0].genero;
                    }
                });
        }, 1000);
    }

    $scope.Inicializar();

    $scope.salvarPedidoInternacao = function () {
        if ($scope.validarDadosPedidoInternacao()) {
            $scope.pedidoInternacao.dataAdmissao = $filter('date')($scope.pedidoInternacao.dataAdmissao, 'yyyy-MM-dd HH:mm:ss:sss');
            $scope.pedidoInternacao.dataPedido = $filter('date')($scope.pedidoInternacao.dataPedido, 'yyyy-MM-dd HH:mm:ss:sss');

            $http.defaults.headers.post["Content-Type"] = "application/x-www-form-urlencoded; charset=utf-8";
            pedidoInternacaoFactory.savePedidoInternacao($scope.pedidoInternacao)
                .then(function (response) {
                    swal(
                        'Concluído!',
                        'Pedido realizado com sucesso, nº ' + response.data.idPedidoInternacao,
                        'success'
                    )
                    $scope.NovoPedido();
                }, function (response) {
                    $scope.pedidoInternacao.dataNascimento = new Date($scope.pedidoInternacao.dataNascimento);
                    $scope.pedidoInternacao.dataPedido = new Date($scope.pedidoInternacao.dataPedido);
                    $scope.pedidoInternacao.dataAdmissao = new Date($scope.pedidoInternacao.dataAdmissao);
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
    $scope.validarDadosPedidoInternacao = function () {
        if ($scope.pedidoInternacao.numProntuario <= 0) {
            swal(
                'Erro!',
                'Digite o número do prontuário!',
                'error'
            )
            return;
        }
        if ($scope.pedidoInternacao.AIH == "") {
            swal(
                'Erro!',
                'Digite o número do AIH!',
                'error'
            )
            return;
        }
        if ($scope.pedidoInternacao.idDiagnostico == undefined) {
            swal(
                'Erro!',
                'Selecione um diagnóstico!',
                'error'
            )
            return;
        }
        if ($scope.pedidoInternacao.idAla == undefined) {
            swal(
                'Erro!',
                'Selecione uma Ala!',
                'error'
            )
            return;
        }
        if ($scope.pedidoInternacao.dataAdmissao == "") {
            swal(
                'Erro!',
                'Insira a data de admissão!',
                'error'
            )
            return;
        }
        if ($scope.pedidoInternacao.medicoResponsavel == "" && $scope.pedidoInternacao.residenteResponsavel == "") {
            swal(
                'Erro!',
                'Deve informar ao menos um responsável',
                'error'
            )
            return;
        }
        return true;
    }
    $scope.validarDadosPaciente = function () {
        if ($scope.pedidoInternacao.numProntuario <= 0) {
            swal(
                'Erro!',
                'Digite o número do prontuário!',
                'error'
            )
            return;
        }
        if ($scope.pedidoInternacao.nomePaciente == "") {
            swal(
                'Erro!',
                'Digite o nome do paciente!',
                'error'
            )
            return;
        }
        if ($scope.pedidoInternacao.nomeMae == "") {
            swal(
                'Erro!',
                'Digite o nome da mãe do paciente!',
                'error'
            )
            return;
        }
        if ($scope.pedidoInternacao.dataNascimento == "") {
            swal(
                'Erro!',
                'Digite a data de nascimento!',
                'error'
            )
            return;
        }
        if ($scope.pedidoInternacao.idade <= 0) {
            swal(
                'Erro!',
                'Digite a idade do paciente!',
                'error'
            )
            return;
        }
        if ($scope.pedidoInternacao.genero == "") {
            swal(
                'Erro!',
                'Digite o sexo do paciente!',
                'error'
            )
            return;
        }
        return true;
    }

    $scope.calcularIdade = function () {

        // Obtém a idade em milissegundos
        var idadeP = new Date() - new Date($scope.pedidoInternacao.dataNascimento).getTime();

        // Converte os milissegundos em data e subtrai da era linux
        var idadeData = new Date(idadeP);
        var idade = idadeData.getUTCFullYear() - 1970;
        if (!isNaN(idade) && idade != undefined) {
            $scope.pedidoInternacao.idade = idade;
        } else {
            $scope.pedidoInternacao.idade = 0;
        }

    }
}]);
