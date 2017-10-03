angular.module('kanleitos', []).
 factory('alasFactory', function($http) {
    var alas = {};
    //Get alas
    alas.getAlas = function() {
      return $http({
            url: "http://localhost:8080/Alas",
            method: 'GET'
           });
    };
    return alas;
  });