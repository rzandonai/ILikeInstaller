
var app = angular.module('javaHtmlInstaler', []);

app.controller('InstalerController', function ($scope) {


  $scope.buscadir=function() {  
	  $scope.rootFolder = buscaDir.buscaDir($scope.rootFolder); 
  }
  $scope.loaddir=function() {  
	  $scope.rootFolder = buscaDir.ultima(); 
  }
});

