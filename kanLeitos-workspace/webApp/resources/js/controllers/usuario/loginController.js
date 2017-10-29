app.controller('loginController', ["$scope", "$http","usuarioFactory", function ($scope, $http, usuarioFactory) {

    $scope.NovoLogin = function () {
        $scope.login = {
            login: "",
            senha: "",
        }
    }
    $scope.NovoLogin();

    $scope.validarLogin = function () {
        if ($scope.login.login == "") {
            swal(
                'Erro!',
                'Insira o login',
                'error'
            )
            return;
        }
        if ($scope.login.senha == "") {
            swal(
                'Erro!',
                'Insira a senha',
                'error'
            )
            return;
        }

        return true;
    }

    $scope.realizarLogin = function (login) {
        if ($scope.validarLogin()) {
            $http.defaults.headers.post["Content-Type"] = "application/x-www-form-urlencoded; charset=utf-8";
            usuarioFactory.login($scope.login)
                .then(function (response) {
                    if (response.data.usuarioValidado) {
                        location.href = "/";
                    } else {
                        swal(
                            'Erro!',
                            'Usuário inválido',
                            'error'
                        )
                    }

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
    }
}]);