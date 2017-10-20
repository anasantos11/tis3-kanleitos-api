app.controller('registroInternacaoController', ["$scope", "$http", "$filter", "pedidoInternacaoFactory", "diagnosticosFactory", "alasFactory", "enfermariaFactory", "leitoFactory", function ($scope, $http, $filter, pedidoInternacaoFactory, diagnosticosFactory, alasFactory, enfermariaFactory, leitoFactory) {

    $scope.NovoRegistro = function () {
        $scope.registroInternacao = {
            numProntuario: 0,
            AIH: "",
            nomePaciente: "",
            nomeMae: "",
            dataNascimento: "",
            idade: 0,
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

    $scope.calcularIdade = function () {
        // Obtém a idade em milissegundos
        var idadeP = new Date() - new Date($scope.registroInternacao.dataNascimento).getTime();

        // Converte os milissegundos em data e subtrai da era linux
        var idadeData = new Date(idadeP);
        var idade = idadeData.getUTCFullYear() - 1970;
        if (!isNaN(idade) && idade != undefined) {
            $scope.registroInternacao.idade = idade;
        } else {
            $scope.registroInternacao.idade = 0;
        }

    }
}]);
