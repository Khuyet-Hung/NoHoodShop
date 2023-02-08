app.controller("category-ctrl", function($scope, $http) {
	$scope.items = [];
	$scope.cates = [];
	$scope.form = {};
	$scope.initialize = function() {
		$scope.nutupdate = false;
		$scope.nutthem = false;
		$http.get("/rest/categories").then(resp => {
			$scope.items = resp.data;
			
		});
	}

	$scope.initialize();

	$scope.reset = function() {
		$scope.nutthem = false;
		$scope.nutupdate = false;
		$scope.form = {
		}
	}

	$scope.edit = function(item) {
		$scope.nutthem = true;
		$scope.nutupdate = true;
		$scope.form = angular.copy(item);
		$(".nav-tabs button:eq(0)").tab('show')
	}

	$scope.create = function() {
		var item = angular.copy($scope.form);
		$http.post('/rest/categories', item).then(resp => {
			$scope.items.push(resp.data);
			$scope.reset();
			$scope.initialize();
			swal({
				title: "Thao tác",
				text: "Thêm thành công",
				icon: "success",
				button: "OK!",
			});
			
		}).catch(error => {
			swal({
				title: "Thao tác",
				text: "Thêm thất bại",
				icon: "error",
				button: "OK!",
			});
			console.log("Error", error);
		})
	}

	$scope.update = function() {
		var item = angular.copy($scope.form);
		$http.put(`/rest/categories/${item.cateid}`, item).then(resp => {
			var index = $scope.items.findIndex(p => p.cateid == item.cateid);
			$scope.items[index] = item;
			$scope.reset();
			$scope.initialize();
			swal({
				title: "Thao tác",
				text: "Cập nhật thành công",
				icon: "success",
				button: "OK!",
			});
			
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

	$scope.delete = function(item) {
		$http.delete(`/rest/categories/${item.cateid}`).then(resp => {
			var index = $scope.items.findIndex(p => p.cateid == item.cateid);
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
			if(this.page <0){
				this.last();
			}
		},
		next() {
			this.page++;
			if(this.page >= this.count){
				this.first();
			}
		},
		last() {
			this.page = this.count - 1;
		}
	}

	$scope.sortColumn = "name";
	$scope.sort = false;

	$scope.sortdata = function(column){
		$scope.sort = ($scope.sortColumn == column) ? !$scope.sort : false;
		$scope.sortColumn = column;
	}
	$scope.getSortclass = function(column){
		if($scope.sortColumn == column){
			return $scope.sort ? 'fas fa-sort-amount-down' : 'fas fa-sort-amount-up'
		}
		return '';
	}
})