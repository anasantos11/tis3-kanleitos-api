app.controller('loginController', ["$scope", "$http","usuarioFactory",function ($scope, $http, usuarioFactory) {    
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
                        location.href = "#!/home";
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


    //CRIPTOGRAFIA BASEADA EM SHA256
    
    //DADO = valor que deseja codificar
    //CHAVE = string que recebe a chave de codificação, pode ser qualquer coisa
            //sendo que a mesma deve ser guardada para decodificação
            //exemplo de chave: 123456
    const codificar = (dado, chave)=>{
        return CryptoJS.AES.encrypt(dado, chave).toString()
    }
    
    //HASHCODIFICADA = dado que já esta encryptado que ira ser decodificado caso necessário
    //CHAVEDEDECODIFICACAO = valor que deve ser usado como chave para decodificação da hash
                            // pode ser qualuqer valor em string
                            //exemplo de chave 123456
    const decodificar = (hashCodificada, chaveDeDecodificacao)=>{
        var bytes  = CryptoJS.AES.decrypt(hashCodificada, chaveDeDecodificacao)
        return bytes.toString(CryptoJS.enc.Utf8)
    }

    //EXEMPLO
    // var dadoCodificado = codificar("Luiz Henrique Silva Jesus", "12345");
    // console.log(dadoCodificado)
    // console.log(decodificar(dadoCodificado, "12345"))
}]);