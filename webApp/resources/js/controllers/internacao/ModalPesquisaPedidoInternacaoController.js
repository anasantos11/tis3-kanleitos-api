app.controller('ModalPesquisaPedidoInternacaoController', ["$scope", "pedidoInternacaoFactory",
    function ($scope, pedidoInternacaoFactory) {


        $scope.calcularHorasAguardando = function (dataAtual, dataAdmissao) {
            var diffMs = (dataAtual - dataAdmissao);
            var diffHrs = Math.floor((diffMs % 86400000) / 3600000);
            var diffMins = Math.round(((diffMs % 86400000) % 3600000) / 60000);
            return diffHrs + 'h ' + diffMins + 'min';

        };

        $scope.atualizarHorasAguardando = function () {
            var dataAtual = new Date();
            for (var i = 0;i < $scope.listaPedidos.length; i++) {
                $scope.listaPedidos[i].horasAguardando = $scope.calcularHorasAguardando(dataAtual, new Date($scope.listaPedidos[i].dataAdmissao))
            };
        };


        $scope.carregarPedidos = function () {
            pedidoInternacaoFactory.getPedidosEmAberto()
                .then(function (response) {
                    $scope.listaPedidos = response.data;
                    $scope.atualizarHorasAguardando();
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
    }]);
