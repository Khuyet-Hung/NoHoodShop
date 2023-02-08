var app = angular.module("test-app", []);
app.controller("test-ctrl", function($scope, $http){
	$scope.listProvince = [];
	$scope.listDistrict = [];
	$scope.listWard = [];
	
	$scope.update = function(){
		$scope.province.updateProvince();
		$scope.district.updateDistrict();
	}
	
	$scope.province = {
		urlRequest : 'https://online-gateway.ghn.vn/shiip/public-api/master-data/province',
		headers : {token:'ebb9ad14-3d84-11ed-b824-262f869eb1a7'},
		updateProvince(){
			$http.get(this.urlRequest,{headers:this.headers}).then(resp =>{
				resp.data.data.forEach(a => {
					provice = {
						provinceid : a.ProvinceID,
						provincename : a.ProvinceName
					}
					$scope.listProvince.push(provice);
				})
				$http.post('/rest/address/province', $scope.listProvince);
				console.log("Lưu tỉnh thành phố thành công");
			})
		}
	}
	
	$scope.district = {
			urlRequest : 'https://online-gateway.ghn.vn/shiip/public-api/master-data/district',
			headers : {token:'ebb9ad14-3d84-11ed-b824-262f869eb1a7'},
			updateDistrict(){
				$http.get(this.urlRequest,{headers:this.headers}).then(resp =>{
					resp.data.data.forEach(a => {
						district = {
							districtid : a.DistrictID,
							province :{provinceid : a.ProvinceID},
							districtname : a.DistrictName
						}
						$scope.listDistrict.push(district);
					})
					$http.post('/rest/address/district', $scope.listDistrict);
					console.log("Lưu quận huyện thành công");
					$scope.ward.updateWard();
				})
			}
		}
	
	$scope.ward = {
			urlRequest : 'https://online-gateway.ghn.vn/shiip/public-api/master-data/ward',
			headers : {token:'ebb9ad14-3d84-11ed-b824-262f869eb1a7'},
			updateWard(){
				var districts = $scope.listDistrict;
				var i = 0,is = 100;
				let timerId = setInterval(function() {
					for(; i < is; i++){
						try {
							$http.get($scope.ward.urlRequest,{headers:$scope.ward.headers, params : {district_id : districts[i].districtid}}).then(resp =>{
								try {
									resp.data.data.forEach(a => {
										ward = {
											wardid : parseInt(a.WardCode),
											district :{districtid : a.DistrictID},
											wardname : a.WardName
										}
										$scope.listWard.push(ward);
									})
								} catch (e) {
									console.log(e);
								}
								
							})
						} catch (e) {
							console.log(e);
						}
						if(i > districts.length){
							console.log($scope.listWard);
							$http.post('/rest/address/ward', $scope.listWard);
							console.log("Lưu phường xã thành công");
							clearInterval(timerId);
							break;
						}
					}
					is += 100;
				}, 6000);
			}
			
		}
})