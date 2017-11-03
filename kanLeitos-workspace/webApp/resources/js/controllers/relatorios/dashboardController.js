app.controller('dashboardController', ["$scope" ,"$interval","kanbanFactory", "Notify", 
    function($scope, $interval, kanbanFactory, Notify){

        $scope.kanban = {
            Verde: {
                qtdPacientes: 0,
                pacientes: []
            },
            Vermelho: {
                qtdPacientes: 0,
                pacientes: []
            },
            Amarelo: {
                qtdPacientes: 0,
                pacientes: []
            }
        }

        const openModalPaciente = (pacientes) =>{
            Notify.openModal("dashboard/modal.html", {pacientes: pacientes}, 1320)
        }

        const atualizaRegistrosInternacao = () =>{
            return kanbanFactory.atualizaRegistrosInternacao()
            .then(()=>{
                return getAllInternacoes()
            }).catch((err)=>{
                console.log(err)
            })
        }
        
        const getAllInternacoes = ()=> {
            return getRegistrosPorClassificação("Vermelho")
            .then(()=>{
                return getRegistrosPorClassificação("Amarelo")
            }).then(()=>{
                return getRegistrosPorClassificação("Verde")
            }).catch((err)=>{
                console.log(err)
            })
        }
        
        const getRegistrosPorClassificação = (classificacao) => {
            return kanbanFactory.getRegistrosPorClassificação(classificacao)
            .then((res)=>{
                $scope.kanban[classificacao].qtdPacientes = res.data.length
                $scope.kanban[classificacao].pacientes = res.data
            }).catch((err)=>{
                console.log(err)
            })
        }

        const getLocalDate = (data) =>{
            const res = new Date(data).toLocaleDateString();
            return res;
        }

        const init = () => {
            getAllInternacoes()
            
            $interval(()=>{
                return getAllInternacoes()
            },10000)
        }

        $scope.init= init
        $scope.getLocalDate= getLocalDate
        $scope.getAllInternacoes = getAllInternacoes
        $scope.openModalPaciente = openModalPaciente    
    }
])