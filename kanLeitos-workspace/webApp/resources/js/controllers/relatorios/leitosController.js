
app.controller('leitosController', ["$scope", "$http", "$filter", "alasFactory", "enfermariaFactory", "leitoFactory", function ($scope, $http, $filter, alasFactory, enfermariaFactory, leitoFactory) {

    $scope.Inicializar = function () {
        $scope.CarregarLeitos(); 
    }

    $scope.CarregarLeitos = function () {
        leitoFactory.getLeitos()
            .then(function (response) {
                $scope.Leitos = response.data;
            }, function (response) {
                swal(
                    'Erro!',
                    response.data.message,
                    'error'
                )
            });
    };

    $scope.Inicializar();


}]);

