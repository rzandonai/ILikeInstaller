
var app = angular.module('javaHtmlInstaler', []);

app.controller('InstalerController', function ($scope) {


  $scope.buscadir=function() {  
	  $scope.diretorio = buscaDir.buscaDir($scope.diretorio); 
  }
});

