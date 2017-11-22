app.controller('ModalPesquisaPedidoInternacaoController', ["$scope", "pedidoInternacaoFactory",
function ($scope, pedidoInternacaoFactory) {
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
}]);
