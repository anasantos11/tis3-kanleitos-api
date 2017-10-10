
app.controller('EstruturaHospitalCtrl', ["$scope", "$http", "$filter", "alasFactory", "enfermariaFactory", "leitoFactory", function ($scope, $http, $filter, alasFactory, enfermariaFactory, leitoFactory) {

    $scope.Inicializar = function () {
        $scope.CarregarLeitos(); 
    }

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
    $scope.CarregarEnfermarias = function () {
        enfermariaFactory.getEnfermarias()
            .then(function (response) {
                $scope.Enfermarias = response.data;
            }, function (response) {
                swal(
                    'Erro!',
                    response.data.message,
                    'error'
                )
            });
    };
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

