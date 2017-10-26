app.controller('pedidoInternacaoController', ["$scope", "$rootScope","$http", "$filter", "pedidoInternacaoFactory", "diagnosticosFactory", "pacienteFactory", "alasFactory", 
    function ($scope, $rootScope,$http, $filter, pedidoInternacaoFactory, diagnosticosFactory, pacienteFactory, alasFactory) {

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

        $rootScope.$watch((data)=>{
            console.log(data)
            if(data.pacienteSelecionado){
                $scope.pedidoInternacao.numProntuario = data.pacienteSelecionado.numProntuario || null
                $scope.pedidoInternacao.nomePaciente = data.pacienteSelecionado.nomePaciente || null
                $scope.pedidoInternacao.nomeMae = data.pacienteSelecionado.nomeMae || null
                $scope.pedidoInternacao.dataNascimento = new Date(getData(data.pacienteSelecionado.dataNascimento)) || null
                $scope.pedidoInternacao.idade = data.pacienteSelecionado.idade || null
                $scope.pedidoInternacao.genero = data.pacienteSelecionado.genero || null
            }
        })

        $scope.Inicializar = function () {
            $scope.NovoPedido();
            $scope.CarregarDiagnosticos();
            $scope.CarregarAlas();
        }

        $scope.setDadosPaciente = (pacienteSelecionado) =>{
            $rootScope.pacienteSelecionado = pacienteSelecionado
            document.getElementById("CloseModalPesquisa").click()
        }

        $scope.cadastrarPaciente = function (paciente) {
            if ($scope.validarDadosPaciente()) {
                $scope.pedidoInternacao.dataNascimento = $filter('date')($scope.pedidoInternacao.dataNascimento, 'yyyy-MM-dd');
                $http.defaults.headers.post["Content-Type"] = "application/x-www-form-urlencoded; charset=utf-8";
                pacienteFactory.savePaciente($scope.pedidoInternacao)
                    .then(function (response) {
                        $scope.setDadosPaciente(response.data)
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
                            $scope.pedidoInternacao.dataNascimento = new Date(getData(response.data[0].dataNascimento));
                            $scope.pedidoInternacao.genero = response.data[0].genero;
                        }
                    });
            }, 1000);
        }

        const getData = (dataDesformatada) => {
            const splitedDate = dataDesformatada.split("-")
            return "" + splitedDate[1] + "/" + splitedDate[2] + "/" + splitedDate[0];
        }

        $scope.Inicializar();

        $scope.salvarPedidoInternacao = function () {
            if ($scope.validarDadosPedidoInternacao()) {
                $scope.pedidoInternacao.dataAdmissao = $filter('date')($scope.pedidoInternacao.dataAdmissao, 'yyyy-MM-dd HH:mm:ss');
                $scope.pedidoInternacao.dataPedido = $filter('date')($scope.pedidoInternacao.dataPedido, 'yyyy-MM-dd HH:mm:ss');

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
    }
]);
