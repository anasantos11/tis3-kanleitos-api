var app = angular.module('kanleitos', []);

app.service("Notify", [function(){
	
	this.getErrorMenssage = function(title, message){
		return swal(
		  title,
		  message,
		  'error'
		);
	};

	this.getMenssage = function(title, message){
		return swal(
		  message
		);
	};

	this.getErrorMenssage = function(message){
		return swal(
		  title,
		  message,
		  'success'
		);
	};

	this.getQuestionMenssage = function(title, message, action, actionText){
		return swal({
		  title: title,
		  text: message,
		  type: 'warning',
		  showCancelButton: true,
		  confirmButtonColor: '#3085d6',
		  cancelButtonColor: '#d33',
		  confirmButtonText: 'Sim'
		}).then(function () {
		  swal(
		    action,
		    actionText,
		    'success'
		  )
		})
	};
}]);