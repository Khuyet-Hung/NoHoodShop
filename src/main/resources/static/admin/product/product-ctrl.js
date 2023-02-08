app.controller("product-ctrl", function ($scope, $http) {
	$scope.items = [];
	$scope.cates = [];
	$scope.form = {};
	$scope.initialize = function () {
		$scope.nutupdate = false;
		$scope.nutthem = false;
		$http.get("/rest/products").then(resp => {
			$scope.items = resp.data;
			$scope.items.forEach(item => {
				item.datecreate = new Date(item.datecreate)
			});
		});
		$http.get("/rest/categories").then(resp => {
			$scope.cates = resp.data;

		});
	}

	$scope.initialize();
	$scope.reset = function () {
		$scope.nutthem = false;
		$scope.nutupdate = false;
		$scope.form = {
			image: ""
		}
	}

	$scope.edit = function (item) {
		$scope.form = angular.copy(item);
		$scope.nutthem = true;
		$scope.nutupdate = true;
		$(".nav-tabs button:eq(0)").tab('show')
	}

	$scope.create = function () {
		$scope.form.image = 'index1';
		var item = angular.copy($scope.form);
		$http.post('/rest/products', item).then(resp => {
			resp.data.datecreate = new Date(resp.data.datecreate);
			$scope.imageChanged(resp.data.productid);
			$scope.items.push(resp.data);
			swal({
				title: "Thao tác",
				text: "Thêm thành công",
				icon: "success",
				button: "OK!",
			});
			$scope.reset();
			$scope.initialize();
		}).catch(error => {
			swal({
				title: "Thao tác",
				text: "Thêm thất bại vui lòng nhập thông tin đầy đủ",
				icon: "error",
				button: "OK!",
			});
			console.log("Error", error);
		})
	}

	$scope.update = function () {
		var item = angular.copy($scope.form);
		$http.put(`/rest/products/${item.productid}`, item).then(resp => {
			var index = $scope.items.findIndex(p => p.productid == item.productid);
			$scope.items[index] = item;
			swal({
				title: "Thao tác",
				text: "Cập nhật thành công",
				icon: "success",
				button: "OK!",
			});
			$scope.reset();
			$scope.initialize();
		}).catch(error => {
			swal({
				title: "Thao tác",
				text: "Cập nhật thất bại",
				icon: "error",
				button: "OK!",
			});
			console.log("Error", error);
		})

	}

	$scope.delete = function (item) {
		$http.delete(`/rest/products/${item.productid}`).then(resp => {
			var index = $scope.items.findIndex(p => p.productid == item.productid);
			$scope.items.splice(index, 1);
			$scope.reset();
			swal({
				title: "Thao tác",
				text: "Xóa thành công",
				icon: "success",
				button: "OK!",
			});
			$scope.initialize();
		}).catch(error => {
			swal({
				title: "Thao tác",
				text: "Xóa thất bại",
				icon: "error",
				button: "OK!",
			});
			console.log("Error", error);
		})
	}

	$scope.pager = {
		page: 0,
		size: 5,
		get items() {
			var start = this.page * this.size;
			return $scope.items.slice(start, start + this.size);
		},
		get count() {
			return Math.ceil(1.0 * $scope.items.length / this.size);
		},
		first() {
			this.page = 0;
		},
		prev() {
			this.page--;
			if (this.page < 0) {
				this.last();
			}
		},
		next() {
			this.page++;
			if (this.page >= this.count) {
				this.first();
			}
		},
		last() {
			this.page = this.count - 1;
		}
	}

	$scope.imageChanged = function (folder) {
		var  files = document.formdulieu.anh1.files ;
		if (files.length > 3) {
			swal({
				title: "Hình ảnh",
				text: "Được tải lên tối đa 3 tệp hình ảnh",
				icon: "error",
				button: "OK!",
			});
		} else {
			var data = new FormData();
			for (let i = 0; i < files.length; i++) {
				data.append('file', files[i]);
			}
			$http.post(`/rest/upload/product/${folder}`, data, {
				transformRequest: angular.identity, headers: { 'Content-Type': undefined }
			}).then(resp => {
				$scope.form.image = 'index1';
			}).catch(error => {
				alert("Lỗi upload hình ảnh");
				console.log("Error", error);
			})
		}
	}
	$scope.imageUpdated = function (files) {
		if (files.length > 3) {
			swal({
				title: "Hình ảnh",
				text: "Được tải lên tối đa 3 tệp hình ảnh",
				icon: "error",
				button: "OK!",
			});
		} else {
			var productid = $scope.form.productid;
			var data = new FormData();
			console.log(files);
			for (let i = 0; i < files.length; i++) {
				data.append('file', files[i]);
			}
			console.log("ok")
			$http.post(`/rest/upload/update/${productid}`, data, {
				transformRequest: angular.identity, headers: { 'Content-Type': undefined }
			}).then(resp => {
				$scope.form.image = 'index1';
			}).catch(error => {
				alert("Lỗi upload hình ảnh");
				console.log("Error", error);
			})
		}

	}
})