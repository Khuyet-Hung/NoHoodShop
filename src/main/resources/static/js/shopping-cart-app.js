paypal.Buttons.driver('angular', window.angular);
var app = angular.module("shopping-cart-app", ['paypal-buttons']);
app.filter('productHomeFilter', function () {
	return function (input, searchKey, priceKey, colorKey) {
		var listResult = [];
		input.forEach(i => {
			if (i.productname.includes(`${searchKey}`) || searchKey == undefined) {
				if (priceKey == undefined) {
					listResult.push(i)
				} else {
					console.log(priceKey[0]);
					if (priceKey[0] == 0 && priceKey[1] == undefined) {
						listResult.push(i);
					} else if (i.price >= priceKey[0] && priceKey[1] == undefined) {
						listResult.push(i);
					} else if (i.price >= priceKey[0] && i.price <= priceKey[1]) {
						listResult.push(i)
					}
				}
			}
		})
		return listResult;
	}
})
app.controller("shopping-cart-ctrl", function ($scope, $rootScope, $http) {
	$scope.pro = [];
	$scope.view = {};
	$scope.sanpham = {};
	$scope.favorite = [];
	$scope.account = {};
	$rootScope.soluonglike = [];
	$rootScope.soluotfav = {};
	$scope.products = function () {
		$http.get("/rest/products").then(resp => {
			$scope.pro = resp.data;
		});
		$http.get("/rest/accounts/getone").then(resp => {
			$scope.account = resp.data;
		}).catch(error => {
			$scope.account = null;
		});
		$http.get("/rest/favorite/all").then(resp => {
			$scope.favorite = resp.data;
		});
		$http.get("/rest/favorite/yeuthich").then(resp => {
			$rootScope.soluonglike = resp.data;
		});
	}
	$scope.pros = function (item) {
		$scope.sanpham = item;
	}
	var username = $("#userremost").text();
	$rootScope.cart = {
		listCarts: [],
		add(id) {
			function saveCart(callback) {
				var item = $rootScope.cart.listCarts.find(i => { return i.product.productid == id });
				if (item) {
					item.quantity++;
					swal({
						title: "Thành công!",
						text: "Thêm vào giỏ hàng",
						icon: "success",
						button: "OK!",
					});
					callback();
				} else {
					$http.get(`/rest/products/${id}`).then(resp => {
						var newCart = {
							account: { username: username },
							product: resp.data,
							quantity: 1,
							sumprice: resp.data.price
						};

						$rootScope.cart.listCarts.push(newCart);
						swal({
							title: "Thành công!",
							text: "Thêm vào giỏ hàng",
							icon: "success",
							button: "OK!",
						});
						callback();
					})
				}
			}

			if (this.checkLogin()) {
				saveCart(this.saveToDatabase);
			} else {
				saveCart(this.saveToLocalStorage);
			}
		},
		clearCart(username) {
			if (this.checkLogin()) {
				$http.delete(`/rest/cart/delbyuser/${username}`)
				this.loadFromDatabase();
			} else {
				$rootScope.cart.listCarts = [];
				this.saveToLocalStorage();
			}
		},
		checkLogin() {
			if (username == '') {
				return false;
			}
			return true;
		},
		saveToLocalStorage() {
			var json = JSON.stringify(angular.copy($rootScope.cart.listCarts));
			localStorage.setItem("cart", json);
		},
		saveToDatabase() {
			$http.post('/rest/cart/list', $rootScope.cart.listCarts).then(resp => {
				$rootScope.cart.loadFromDatabase();
			})
		},
		loadFromDatabase() {
			$http.get(`/rest/cart/getbyuser/${username}`).then(resp => {
				this.listCarts = resp.data;
			})
		},
		loadFromLocalStorage() {
			var json = localStorage.getItem("cart");
			$rootScope.cart.listCarts = json ? JSON.parse(json) : [];
		},

		deleteFromDatabase(cartId) {
			$http.delete(`/rest/cart/${cartId}`).then(resp => {
				$rootScope.cart.loadFromDatabase();
			})
		},
		deleteFromLocalStorage(productId) {
			var index = this.listCarts.findIndex(item => item.product.productId == productId);
			this.listCarts.splice(index, 1);
			this.saveToLocalStorage();
		},
		loadCart() {
			if (this.checkLogin()) {
				var json = localStorage.getItem("cart");
				if (json == '[]') {
					this.loadFromDatabase();
				} else {
					var data = json ? JSON.parse(json) : [];
					data.map(cart => {
						cart.account.username = username;
					})
					this.listCarts = data;
					localStorage.setItem("cart", '[]');
					$http.delete(`/rest/cart/delbyuser/${username}`).then(resp => {
						this.saveToDatabase();
					})

				}
			} else {
				this.loadFromLocalStorage();
			}
		},
		saveCart() {
			if (this.checkLogin()) {
				this.saveToDatabase();
			} else this.saveToLocalStorage();
		},
		deleteCart(cartId, proudctId) {
			if (this.checkLogin()) {
				this.deleteFromDatabase(cartId);
			} else this.deleteFromLocalStorage(proudctId);
		},
		get count() {
			return this.listCarts.map(item => item.quantity).reduce((total, quantity) => total += quantity, 0);
		},
		get amount() {
			return this.listCarts.map(item => item.quantity * item.product.price).reduce((total, quantity) => total += quantity, 0);
		}
	}
	$rootScope.cart.loadCart();
	console.log($rootScope.cart.listCarts)


	$scope.checkfav = function (pro, acc) {
		var chfav = $scope.favorite.find(ur => ur.account.username == acc.username && ur.product.productid == pro.productid);

		if (chfav) {
			return true;

		} else {
			return false;
		}
	}
	$scope.actionfav = function (pro, acc) {
		var chfav = $scope.favorite.find(ur => ur.account.username == acc.username && ur.product.productid == pro.productid);
		if (chfav) {
			console.log(chfav);
			$http.delete(`/rest/favorite/${chfav.favid}`).then(resp => {
				var index = $scope.favorite.findIndex(a => a.favid == chfav.favid);
				$scope.favorite.splice(index, 1);
				console.log("xoa thanh cong");
			}).catch(error => {
				console.log("Error", error);
			})

		} else {
			var f = {
				favdate: new Date(), account: { username: acc.username }, product: { productid: pro.productid }
			}
			$http.post(`/rest/favorite`, f).then(resp => {
				$scope.favorite.push(resp.data);
				console.log("thanh cong");
			}).catch(error => {
				console.log("Error", error);
			})
		}
	}
	$scope.register = function () {
		var username = document.getElementById("username").value;
		var email = document.getElementById("email").value;
		var password = document.getElementById("matkhau").value;
		var phone = document.getElementById("phone").value;
		var re = {
			username: username,
			email: email,
			password: password,
			phone: phone
		}
		$http.post("/rest/accounts/dangky", re).then(resp => {
			// swal("Tài khoản", "Đăng ký tài khoản thành công", "success");
			swal({
				title: "Tài khoản",
				text: "Đăng ký tài khoản thành công",
				icon: "success",
				buttons: "Đồng ý",
				dangerMode: true,
			})
				.then((willDelete) => {
					if (willDelete) {
						window.location.href = '/';
					}
				});
			// swal({
			// 	title: "Tài khoản",
			// 	text: "Đăng ký tài khoản thành công",
			// 	icon: "success",
			// 	confirmButtonColor: '#3085d6',
			// 	cancelButtonColor: '#d33',
			// 	confirmButtonText: 'Đồng ý'
			// }).then((result) => {
			// 	if (result.isConfirmed) {
			// 		window.location.href = '/';
			// 	}
			// })

		}).catch(error => {
			console.log("Error", error);
		})
	}
	$scope.findone = function (item) {
		$scope.view = item;
	}
	$scope.products();
});
// Điều khiển trang chủ ////////////////////////////////////////////////////////////////
app.controller("home-ctrl", function ($scope, $http) {
	$scope.listLikeMost = [];
	$scope.accounts = {};
	$scope.get = function () {
		$http.get("/rest/accounts/getone").then(resp => {
			$scope.accounts = resp.data;
			console.log($scope.accounts);
			var username = $scope.accounts.username;
			$http.get(`/rest/favorite/finduser/${username}`).then(resp => {
				resp.data.forEach(item => {
					$scope.listLikeMost.push(item.product);
				})
				console.log($scope.listLikeMost)
			})
		}).catch(error => {
			$scope.accounts = null;
			console.log(error);
		});



	}
	$scope.get()
})
// Điều khiển danh sách sản phẩm ////////////////////////////////////////////////////////////////
app.controller("productlist-ctrl", function ($scope, $http) {
	$scope.listCategory = [];
	$scope.keySort = 'productname';

	$scope.getCategoties = function () {
		$http.get("/rest/categories").then(resp => {
			$scope.listCategory = resp.data;
		})
	}
	$scope.changeKeySort = function (keySort, opt) {
		$scope.keySort = keySort;
		if (opt !== undefined) {
			$scope.optKeySort = opt;
			console.log($scope.optKeySort)
		}
	}
	$scope.getCategoties();

	$scope.changePriceKey = function (min, max) {
		$scope.priceKey = [min, max];
	}
});


// Điều khiển trang chi tiết sản phẩm////////////////////////////////////////////////////////////////
app.controller("detail-ctrl", function ($scope, $http, $rootScope) {
	$scope.favorite = {
		user: $("#username").text(),
		proid: $("#productid").text(),
		isLike: false,
		countLike: 0,
		ListLike: [],
		Likeone: {},
		checkLike() {
			$http.get(`/rest/favorite/checkexist?p=${this.proid}&u=${this.user}`).then(resp => {
				this.isLike = resp.data;
			})
			$http.get("/rest/favorite/yeuthich").then(resp => {
				this.ListLike = resp.data;
				this.Likeone = this.ListLike.find(ur => ur.productid == this.proid);
				if (this.Likeone != undefined) {
					this.countLike = this.Likeone.number;
				}

			});
		},
		triggle() {
			$http.get(`/rest/favorite?p=${this.proid}&u=${this.user}`).then(resp => {
				if (resp.data != "") {
					$http.delete(`/rest/favorite/${resp.data.favid}`)

					this.isLike = false;
					this.countLike -= 1;
				} else {
					this.isLike = true;
					var f = {
						favdate: new Date(),
						product: { productid: this.proid },
						account: { username: this.user }
					}
					$http.post(`/rest/favorite`, f)
					this.countLike += 1;
				}
			});
		}
	}
	$scope.favorite.checkLike()


	$scope.comment = {
		user: $("#username").text(),
		proid: $("#productid").text(),
		listComments: [],
		getListComments: function () {
			$http.get(`/rest/comment/getpid/${this.proid}`).then(resp => {
				console.log(resp.data);
				this.listComments = resp.data;
				this.countComment = this.listComments.length;
			})
		},
		countComment: 0,
		add(productid, username) {
			cmt = {
				content: $scope.content,
				cmtdate: new Date(),
				product: { productid: productid },
				account: { username: username },
			}
			$http.post(`/rest/comment?p=${productid}&u=${username}`, cmt).then(resp => {
				this.clear();
				this.getListComments();
			})
		},
		clear() {
			$scope.content = "";
		}
	}

	$scope.comment.getListComments();

	$scope.account = {
		update(username) {
			info = {
				username: username,
				password: $scope.password,
				email: $scope.email,
				phone: $scope.phone,
				gender: $scope.gender,
				address: $scope.address,
				photo: $scope.photo
			}
		}
	}
});


//Điều khiển trang chỉnh mật khẩu của người dùng////////////////////////////////////////////////////////////////
app.controller("change-pass-ctrl", function ($scope, $http) {
	$scope.account = {};
	$scope.initialize = function () {
		$http.get(`/rest/users/${$("#userremost").text()}`).then(resp => {
			$scope.account = resp.data
		})
	}
	$scope.updatePass = function () {
		if ($scope.newPass == $scope.confirmPass) {
			if ($scope.account.password == $scope.oldPass) {
				$scope.account.password = $scope.newPass;
				$http.post("/rest/users", $scope.account)
				$scope.restForm()
				swal({
					title: "Thành công!",
					text: "Đã đổi mật khẩu!",
					icon: "success",
					button: "OK!",
				});
			} else console.log("Mật khẩu cũ không chính xács")
		} else console.log("Nhập lại không chính xác")
	}
	$scope.initialize();
	$scope.restForm = function () {
		$scope.oldPass = '';
		$scope.newPass = '';
		$scope.confirmPass = '';
	}
});



//Điều khiển trang chỉnh sửa hồ sơ của người dùng////////////////////////////////////////////////////////////////
app.controller("profile-ctrl", function ($scope, $http) {
	$scope.user = {
		profile: {},
		getInfo(username) {
			$http.get(`/rest/users/${username}`).then(resp => {
				this.profile = resp.data;
			})
		},
		update() {
			swal({
				title: "Thành công!",
				text: "Đã đổi hồ sơ người dùng!",
				icon: "success",
				button: "OK!",
			});
			$http.post("/rest/users", this.profile)
		}
	}
	$scope.user.getInfo($("#userremost").text());

	//Thay đổi hình ảnh
	$scope.imageChanged = function (files) {
		var data = new FormData();
		data.append('file', files[0]);
		console.log(angular.identity);
		$http.post('/rest/upload/avatar', data, {
			transformRequest: angular.identity, headers: { 'Content-Type': undefined }
		}).then(resp => {
			$scope.user.profile.photo = resp.data.name;
		}).catch(error => {
			alert("Lỗi rồi");
			console.log("Error", error);
		})
	}
});


//Điều khiển trang danh sách địa chỉ của người dùng////////////////////////////////////////////////////////////////
app.controller("address-ctrl", function ($scope, $rootScope, $http) {
	$rootScope.Provinces = [];
	$rootScope.Districts = [];
	$rootScope.Wards = [];
	$rootScope.Addresses = [];
	$rootScope.addressIsSelect = {};

	$rootScope.initialize = function () {
		$http.get("https://online-gateway.ghn.vn/shiip/public-api/master-data/province",
			{ headers: { token: 'ebb9ad14-3d84-11ed-b824-262f869eb1a7' } }).then(resp => {
				resp.data.data.forEach(item => {
					var province = {
						provinceid: item.ProvinceID,
						provincename: item.ProvinceName
					}
					$rootScope.Provinces.push(province);
				})
			});
		$rootScope.loadAddress();
	}


	$rootScope.loadAddress = function () {
		$http.get(`/rest/address/username/${$("#userremost").text()}`).then(resp => {
			$rootScope.Addresses = resp.data;
			$rootScope.addressIsSelect = $rootScope.Addresses.find(function (address) {
				if (address.isdefault) {
					if ($rootScope.ship) {
						$rootScope.ship.getFee(address)
					}
				}
				return address.isdefault
			})
		});
	}
	$scope.pager = {
		page: 0, size: 5, get Addresses() {
			var start = this.page * this.size;
			return $scope.Addresses.slice(start, start + this.size);
		}, get count() {
			return Math.ceil(1.0 * $scope.Addresses.length / this.size);
		}, first() {
			this.page = 0;
		}, prev() {
			this.page--;
			if (this.page < 0) {
				this.last();
			}
		}, next() {
			this.page++;
			if (this.page >= this.count) {
				this.first();
			}
		}, last() {
			this.page = this.count - 1;
		}
	}
	$rootScope.initialize();


	$rootScope.viewDistrict = {
		selectProvince() {
			$rootScope.address.district.districtid = 0;
			$rootScope.address.ward.wardid = 0;
			$rootScope.Districts = [];
			$rootScope.Wards = [];
			var provinceid = $rootScope.address.province.provinceid;
			if (provinceid != 0) {
				$http.get("https://online-gateway.ghn.vn/shiip/public-api/master-data/district",
					{ headers: { token: 'ebb9ad14-3d84-11ed-b824-262f869eb1a7' }, params: { "province_id": provinceid } }).then(resp => {
						resp.data.data.forEach(item => {
							var district = {
								districtid: item.DistrictID,
								districtname: item.DistrictName,
								province: { provinceid: item.ProvinceID }
							}
							$rootScope.Districts.push(district);
						})
					});
			} else {
				$rootScope.Districts = [];
			}
		}
	}

	$rootScope.viewWard = {
		selectDistrict() {
			$rootScope.address.ward.wardid = 0;
			$rootScope.Wards = [];
			var districtid = $rootScope.address.district.districtid;
			if (districtid != 0) {
				$http.get(`https://online-gateway.ghn.vn/shiip/public-api/master-data/ward?district_id=${districtid}`,
					{ headers: { token: 'ebb9ad14-3d84-11ed-b824-262f869eb1a7' } }).then(resp => {

						resp.data.data.forEach(item => {
							var ward = {
								wardid: parseInt(item.WardCode),
								wardname: item.WardName,
								district: { districtid: item.DistrictID }
							}
							$rootScope.Wards.push(ward);
						})
					});
			} else {
				$rootScope.Wards = [];
			}
		}
	}

	$rootScope.address = {
		province: { provinceid: 0 },
		district: { districtid: 0 },
		ward: { wardid: 0 }
	};


	//  Show form đăng kí address
	$rootScope.addAddress = function () {
		$rootScope.address = {
			province: { provinceid: 0 },
			district: { districtid: 0 },
			ward: { wardid: 0 }
		};
	}
	// Show form để sửa address
	$rootScope.editAddress = function (addr) {
		$rootScope.address = angular.copy(addr);
		$rootScope.showDistrict(addr.province.provinceid);
		$rootScope.showWard(addr.district.districtid);
	}


	$rootScope.showDistrict = function (provinceid) {
		$http.get("https://online-gateway.ghn.vn/shiip/public-api/master-data/district",
			{ headers: { token: 'ebb9ad14-3d84-11ed-b824-262f869eb1a7' }, params: { "province_id": provinceid } }).then(resp => {
				resp.data.data.forEach(item => {
					var district = {
						districtid: item.DistrictID,
						districtname: item.DistrictName,
						province: { provinceid: item.ProvinceID }
					}
					$rootScope.Districts.push(district)
				})
			});
	}

	$rootScope.showWard = function (districtid) {
		$http.get(`https://online-gateway.ghn.vn/shiip/public-api/master-data/ward?district_id=${districtid}`,
			{ headers: { token: 'ebb9ad14-3d84-11ed-b824-262f869eb1a7' } }).then(resp => {

				resp.data.data.forEach(item => {
					var ward = {
						wardid: parseInt(item.WardCode),
						wardname: item.WardName,
						district: { districtid: item.DistrictID }
					}
					$rootScope.Wards.push(ward);
				})
			});
	}

	$rootScope.createAddress = function () {
		var address = $rootScope.address;
		$http.get(`/rest/system/address/ward/getone/${address.ward.wardid}`).then(resp => {
			if (resp.data == '') {
				// post tỉnh / thành phố
				var Province = $rootScope.Provinces.find(resp => resp.provinceid == address.province.provinceid);
				$http.post("/rest/system/address/postone/province", Province).then(respa => {
					// post quận / huyện	
					var District = $rootScope.Districts.find(resp => resp.districtid == address.district.districtid);
					$http.post("/rest/system/address/postone/district", District).then(respa => {
						// post phường / xã
						var Ward = $rootScope.Wards.find(resp => resp.wardid == address.ward.wardid);
						$http.post("/rest/system/address/postone/ward", Ward).then(o => {
							// Lưu địa chỉ
							$rootScope.address.account = { username: $("#userremost").text() };
							$rootScope.address.isdefault = $rootScope.Addresses.length == 0 || $rootScope.address.isdefault ? true : false;
							console.log($rootScope.address);
							$http.post(`/rest/address/`, $rootScope.address).then(resp => {
								$rootScope.loadAddress();
							});
						})
					})
				})
			} else {
				$rootScope.address.account = { username: $("#userremost").text() };
				$rootScope.address.isdefault = $rootScope.Addresses.length == 0 || $rootScope.address.isdefault ? true : false;
				$http.post(`/rest/address/`, $rootScope.address).then(resp => {
					$rootScope.loadAddress();
				});
			}
		})

	}
	$rootScope.deleteAddress = function (addressid) {
		$http.delete(`/rest/address/${addressid}`).then(resp => {
			$rootScope.loadAddress();
		})
	}

	$rootScope.changeDefaultAddress = function (addr) {
		$rootScope.Addresses.map(address => {
			if (address.isdefault) {
				address.isdefault = false;
			}
		})
		$rootScope.Addresses.map(address => {
			if (address == addr) {
				address.isdefault = true;
			}
		})
		$rootScope.Addresses.forEach(addr => {
			$http.post(`/rest/address/`, addr)
		})
	}
});


//Điều khiển trang đặt hàng của người dùng////////////////////////////////////////////////////////////////
app.controller("order-ctrl", function ($scope, $rootScope, $http) {
	$scope.opts = {
		createOrder: function (data, actions) {
			$scope.value = $rootScope.cart.amount + $rootScope.ship.fee;
			let num = $scope.value / 24365;
			$scope.value1 = Math.round(num * 100) / 100

			console.log($scope.value1);

			return actions.order.create({
				purchase_units: [{
					amount: {
						value: $scope.value1
					}
				}]
			});
		},

		onApprove: function (data, actions) {
			$rootScope.order.purchase();
			return actions.order.capture().then(function () {
				swal({
					title: "Thanh toán",
					text: "Thanh toán thành công",
					icon: "success",
					buttons: "Đồng ý",
					dangerMode: true,
				}).then((willDelete) => {
					if (willDelete) {
						window.location.href = '/';
					}
				});

				// swal({
				// 	title: "Thanh toán",
				// 	text: "Thanh toán thành công",
				// 	icon: "success",
				// 	button: "Đồng ý",
				// });
			});
		},
		onCancel: function (data) {
			swal({
				title: "Thanh toán",
				text: "Giao dịch hủy bỏ",
				icon: "error",
				button: "Đồng ý",
			});
		},
		onError: function (err) {
			swal({
				title: "Thanh toán",
				text: "Trong quá trình giao dịch có xảy ra lỗi vui lòng kiểm tra lại thông tin",
				icon: "error",
				button: "Đồng ý",
			});
		}
	};
	$rootScope.order = {
		purchase() {
			if ($rootScope.shipMethod.methodShip.id == 1) {
				var order = {
					address: $rootScope.addressIsSelect,
					account: { username: $("#userremost").text() },
					status: 1,
					paymentmt: $rootScope.shipMethod.methodShip.id,
					shipfee: $rootScope.ship.fee,
					sumprice: $rootScope.cart.amount + $rootScope.ship.fee
				}
			}
			if ($rootScope.shipMethod.methodShip.id == 2) {
				var order = {
					address: $rootScope.addressIsSelect,
					account: { username: $("#userremost").text() },
					status: 2,
					paymentmt: $rootScope.shipMethod.methodShip.id,
					shipfee: $rootScope.ship.fee,
					sumprice: $rootScope.cart.amount + $rootScope.ship.fee
				}
			}

			$http.post("/rest/order", order).then(resp => {
				var orderDetails = [];
				$rootScope.cart.listCarts.forEach(cart => {
					var orderDetail = {
						sumprice: cart.sumprice,
						quantity: cart.quantity,
						product: cart.product,
						bill: resp.data
					}
					orderDetails.push(orderDetail)
					cart.product.quantity -= cart.quantity;
					console.log(cart.product)
					$http.post("/rest/products", cart.product).then(resp => {
						console.log(cart.product)
					})
				})
				$http.post("/rest/orderdetail", orderDetails).then(resp => {
					$rootScope.cart.clearCart($("#userremost").text())
					$rootScope.cart.loadCart()
					swal({
						title: "Thanh toán",
						text: "Đặt hàng thành công",
						icon: "success",
						buttons: "Đồng ý",
						dangerMode: true,
					})
						.then((willDelete) => {
							if (willDelete) {
								window.location.href = '/';
							}
						});

				})
			})

		}
	}

	$rootScope.orderAddress = {
		isEdit: false,
		updateAddress(addr) {
			this.isEdit = true;
			$rootScope.editAddress(addr)
		},
		createAddress() {
			this.isEdit = true;
			$rootScope.addAddress()
		},
		finish() {
			$rootScope.createAddress();
			this.isEdit = false;
		},
		changDefault(addr) {
			$rootScope.Addresses.map(address => {
				if (address.isdefault) {
					address.isdefault = false;
				}
			})
			$rootScope.Addresses.map(address => {
				if (address == addr) {
					$rootScope.ship.getFee(address)
					address.isdefault = true;
					$rootScope.addressIsSelect = addr;
				}
			})
		}
	}

	$rootScope.ship = {
		fee: 0,
		from_district_id: 1461,
		getFee(address) {
			var url = 'https://online-gateway.ghn.vn/shiip/public-api/v2/shipping-order/fee';
			var header = {
				token: "ebb9ad14-3d84-11ed-b824-262f869eb1a7",
				shop_id: 3292848
			}
			var body = {
				"from_district_id": this.from_district_id,
				"service_id": null,
				"service_type_id": 2,
				"to_district_id": address.district.districtid,
				"to_ward_code": address.ward.wardid,
				"height": 50,
				"length": 20,
				"weight": 200,
				"width": 20,
				"insurance_value": 10000,
				"coupon": null
			}
			$http.get(url, { headers: header, params: body }).then(resp => {
				this.fee = resp.data.data.total;
			})
		}
	}


	$rootScope.shipMethod = {
		listMethodShip: [],
		getMethodShip() {
			this.listMethodShip = [
				{
					id: 1,
					name: "Thanh toán khi nhận hàng"
				},
				{
					id: 2,
					name: "Thanh toán qua Paypal"
				}
			]
			this.methodShip = this.listMethodShip[1]
		},
		isViewMethodShip: true,
		methodShip: {},
		changeMethodShip(methodship) {
			if (methodship) {
				this.methodShip = methodship;

			}
			if (this.isViewMethodShip) {
				this.isViewMethodShip = false;

			} else {
				this.isViewMethodShip = true;
			}
		}
	}

	$rootScope.shipMethod.getMethodShip();
});



//Điều khiển trang danh sách đặt hàng của người dùng////////////////////////////////////////////////////////////////
app.controller("list-order-ctrl", function ($scope, $http) {
	$scope.isViewOne = false;

	$scope.listOrder = [];
	$scope.initialize = function () {
		$http.get(`/rest/order/getbyuser/${$("#userremost").text()}`).then(resp => {
			$scope.listOrder = resp.data;
		})
	}
	$scope.pager = {
		page: 0, size: 5, get listOrder() {
			var start = this.page * this.size;
			return $scope.listOrder.slice(start, start + this.size);
		}, get count() {
			return Math.ceil(1.0 * $scope.listOrder.length / this.size);
		}, first() {
			this.page = 0;
		}, prev() {
			this.page--;
			if (this.page < 0) {
				this.last();
			}
		}, next() {
			this.page++;
			if (this.page >= this.count) {
				this.first();
			}
		}, last() {
			this.page = this.count - 1;
		}
	}
	$scope.initialize();

	$scope.order = {
		orderView: {},
		viewById(orderid) {
			$scope.isViewOne = true;
			$http.get(`/rest/orderdetail/getbybill/${orderid}`).then(resp => {
				this.orderView = resp.data;
				console.log(this.orderView)
			})
		}
	}
	$scope.setViewAll = function () {
		$scope.isViewOne = false;
		$scope.order.orderView = {};
	}
	$scope.listcart = [];
	$scope.preorder = function (ord) {
		console.log(ord);
		var item = {
			status: 1
		};
		$http.get(`/rest/orderdetail/getbybill/${ord.billid}`, item).then(resp => {
			$scope.listcart = resp.data;
			for (let i = 0; i < $scope.listcart.length; i++) {
				var item = {
					account: { username: ord.account.username },
					product: { productid: $scope.listcart[i].product.productid },
					quantity: $scope.listcart[i].quantity,
					sumprice: $scope.listcart[i].sumprice
				}
				$http.post(`/rest/cart`, item).then(resp => {
					console.log(resp.data);
				})
			}
		})
		window.location.href = '/order/purchase';
	}
	$scope.cancelorder = function (ord) {
		var item = {
			status: 4
		};
		console.log(ord.billid);
		$http.put(`/rest/bill/cancel/${ord.billid}`, item).then(resp => {
			var index = $scope.listOrder.findIndex(p => p.billid == item.billid);
			$scope.listOrder[index] = item;
			$scope.initialize();
		}).catch(error => {
			alert("loi");
			console.log(error);
		})
	}
});

//Điều khiển trang quên mật khẩu của người dùng////////////////////////////////////////////////////////////////
app.controller("forget-pass-ctrl", function ($scope, $http) {
	$scope.isSended = false;
	$scope.isDoneCheckCode = false;
	$scope.password = {
		emailSend: '',
		codeFromClient: '',

	}
	$scope.sendCode = function () {
		$scope.isSended = true;
		$http.post(`/rest/sendmail/${$scope.password.emailSend}`).then(resp => {
			$scope.codeFromServer = resp.data;
		})
	}
	$scope.checkCode = function () {
		console.log($scope.codeFromServer, $scope.password.codeFromClient)
		if ($scope.codeFromServer === parseInt($scope.password.codeFromClient)) {
			$scope.isDoneCheckCode = true;
		} else {
			swal({
				title: "Thất bại!", text: "Mã code sai", icon: "error", button: "OK!",
			});
		}
	}
	$scope.checkNewPass = function () {
		if ($scope.password.newPass === $scope.password.confirmPass) {
			$http.put(`/rest/accounts/changepassword/?email=${$scope.password.emailSend}&pass=${$scope.password.newPass}`).then(resp => {
				window.location.pathname = '/security/login/form';
			})
		} else {
			swal({
				title: "Thất bại!", text: "Mật khẩu không giống nhau", icon: "error", button: "OK!",
			});
		}
	}
});

