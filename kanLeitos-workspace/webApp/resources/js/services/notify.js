app.service("Notify", ['ngDialog', 
    function(ngDialog){
        this.openModal = (template, data, width) =>{
            ngDialog.open({
                template: template,
                data: data,
                width: width 
            });
        }
    }
])