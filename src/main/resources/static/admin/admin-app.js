app = angular.module("admin-app", ["ngRoute"]);
app.config(function($routeProvider) {
	$routeProvider
		.when("/product", {
			templateUrl: "../admin/product/productform.html",
			controller: "product-ctrl"
		})
		.when("/authorize", {
			templateUrl: "../admin/authority/index.html",
			controller: "authority-ctrl"
		})
		.when("/unauthorized", {
			templateUrl: "../admin/authority/unauthorized.html",
			controller: "authority-ctrl"
		})
		.when("/account", {
			templateUrl: "../admin/account/accountform.html",
			controller: "account-ctrl"
		})
		.when("/accountvip", {
			templateUrl: "../admin/accountvip/accountformvip.html",
			controller: "accountvip-ctrl"
		})
		.when("/category", {
			templateUrl: "../admin/categories/categoryform.html",
			controller: "category-ctrl"
		})
		.when("/system/address", {
			templateUrl: "../admin/system/address/address.html",
			controller: "address-ctrl"
		})
		.when("/managerorder", {
			templateUrl: "../admin/managerorder/donhang.html",
			controller: "managerorder-ctrl"
		})
		.otherwise({
			templateUrl: "../admin/chart/thongke.html",
			controller: "thongke-ctrl"
		});
});
app.controller("admin-ctrl", function ($rootScope, $http,$location) {
    $rootScope.sluser = {};
    $rootScope.slcate = {};
	$rootScope.sumdt = {};
	$rootScope.slbanra = {};
	$rootScope.account = {};
    $rootScope.all = function () {
		$http.get("/rest/accounts/getone").then(resp => {
			$rootScope.account = resp.data;
		}).catch(error => {
			$rootScope.account = null;
		});
        $http.get("/rest/accounts/soluong").then(resp => {
            $rootScope.sluser = resp.data;
        });
        $http.get("/rest/categories/soluong").then(resp => {
            $rootScope.slcate = resp.data;
        });
		$http.get("/rest/orderdetail/tongdt").then(resp => {
            $rootScope.sumdt = resp.data;
        });
		$http.get("/rest/orderdetail/tongsl").then(resp => {
            $rootScope.slbanra = resp.data;
        });
    }
    $rootScope.all();
});
