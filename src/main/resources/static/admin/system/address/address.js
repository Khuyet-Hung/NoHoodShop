app.controller("address-ctrl", function($scope, $http) {
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
				$http.post('/rest/system/address/province', $scope.listProvince);
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
					$http.post('/rest/system/address/district', $scope.listDistrict);
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
							$http.post('/rest/system/address/ward', $scope.listWard);
							clearInterval(timerId);
							break;
						}
					}
					is += 100;
				}, 6000);
			}
			
		}
	
	
	
	$scope.Provinces = [];
	$scope.Districts = [];
	$scope.Wards = [];
	
	$scope.initialize = function() {
		$http.get("/rest/system/address/province").then(resp => {
			$scope.Provinces = resp.data;
		});
	}
	$scope.initialize();
	
	$scope.viewProvince = {
		limitNumber : 10,
		isHide : true,
		hideOrView(){
			let ele = document.getElementById('hideOrView');
			if(this.isHide){
				this.limitNumber = $scope.Provinces.length;
				this.isHide = false;
				ele.innerHTML = 'Thu gọn';
			}else{
				this.limitNumber = 10;
				this.isHide = true;
				ele.innerHTML = 'Xem tất cả';
			}
		}
	}
	
	$scope.viewDistrict = {
			view(){
				var province = $scope.selectedItem;
				if(province !== null){
					$http.get(`/rest/system/address/district/${province.provinceid}`).then(resp => {
						$scope.Districts = resp.data;
					});
				}else{
					$scope.Districts = [];
				}
			}
		}
	
	$scope.viewWard = {
			districts : [],
			selectProvice(){
				var province = $scope.selectedProvice;
				if(province !== null){
					$http.get(`/rest/system/address/district/${province.provinceid}`).then(resp => {
						this.districts = resp.data;
					});
				}else{
					this.districts = [];
				}
			},
			selectDistrict(){
				console.log(1);
				var district = $scope.selectedDistrict;
				if(district !== null){
					$http.get(`/rest/system/address/ward/${district.districtid}`).then(resp => {
						$scope.Wards = resp.data;
					});
				}else{
					$scope.Wards = [];
				}
			}
		}
	
})