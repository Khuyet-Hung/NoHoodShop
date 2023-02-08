app.controller("thongke-ctrl", function ($scope, $http) {
	$scope.value = [];
	$scope.year = [];
	$scope.year1 = [];
	$scope.year2 = [];
	$scope.year3 = [];
	$scope.month = [];
	$scope.month1 = [];
	$scope.month2 = [];
	$scope.month3 = [];
	$scope.trangchu = function () {
		$http.get("/rest/bill/nam").then(resp => {
			$scope.year = resp.data;
			$scope.year1 = resp.data;
			$scope.year2 = resp.data;
			$scope.year3 = resp.data;
			
		});
		
	}
	$scope.searchmonth = function(){
		var year = $scope.nam.number;
		$http.get(`/rest/bill/timthang/${year}`).then(resp => {
			$scope.month = resp.data;
			console.log($scope.month)
		});
	}
	$scope.searchmonth1 = function(){
		var year = $scope.namtron.number;
		$http.get(`/rest/bill/timthang/${year}`).then(resp => {
			$scope.month1 = resp.data;
			console.log($scope.month);
		});
	}
	$scope.searchmonth2 = function(){
		var year = $scope.namtron1.number;
		$http.get(`/rest/bill/timthang/${year}`).then(resp => {
			$scope.month2 = resp.data;
			console.log($scope.month)
		});
	}
	$scope.searchmonth3 = function(){
		var year = $scope.namtron2.number;
		$http.get(`/rest/bill/timthang/${year}`).then(resp => {
			$scope.month3 = resp.data;
			console.log($scope.month)
		});
	}
	$scope.piechart = function(id,title,series){
		Highcharts.chart(id, {
			chart: {
				plotBackgroundColor: null,
				plotBorderWidth: null,
				plotShadow: false,
				type: 'pie'
			},
			title: {
				align: 'center',
				text: title
			},
			tooltip: {
				headerFormat: '<span style="font-size:11px">{series.name}</span><br>',
				pointFormat: '<span style="color:{point.color}">{point.name}</span>: <b>{point.y:.0f} </b> của tổng số lượng<br/>'
			},
			accessibility: {
				announceNewData: {
					enabled: true
				}
			},
			plotOptions: {
				pie: {
					allowPointSelect: true,
					cursor: 'pointer',
					dataLabels: {
						enabled: true,
						format: '<b>{point.name}</b>: {point.percentage:.1f} %'
					}
				}
			},
			series: series
		});
	}
	$scope.barchart = function(id,title,series){
		Highcharts.chart(id, {
			chart: {
				type: 'bar'
			},
			title: {
				align: 'center',
				text: title
			},
			accessibility: {
				announceNewData: {
					enabled: true
				}
			},
			xAxis: {
				type: 'category'
			},
			yAxis: {
				title: {
					text: 'Doanh thu của từng thể loại sản phẩm'
				}

			},
			legend: {
				enabled: false
			},
			plotOptions: {
				series: {
					borderWidth: 0,
					dataLabels: {
						enabled: true,
						format: '{point.y} đ'
					}
				}
			},

			tooltip: {
				headerFormat: '<span style="font-size:11px">{series.name}</span><br>',
				pointFormat: '<span style="color:{point.color}">{point.name}</span>: <b>{point.y} đ</b> doanh thu<br/>'
			},

			series: series,
			drilldown: {
				breadcrumbs: {
					position: {
						align: 'right'
					}
				}

			}
		});
	}
	$scope.chartcolumn = function (id, title, series) {
		Highcharts.chart(id, {
			chart: {
				type: 'column'
			},
			title: {
				align: 'center',
				text: title
			},
			accessibility: {
				announceNewData: {
					enabled: true
				}
			},
			xAxis: {
				type: 'category'
			},
			yAxis: {
				title: {
					text: 'Doanh thu bán sản phẩm'
				}

			},
			legend: {
				enabled: false
			},
			plotOptions: {
				series: {
					borderWidth: 0,
					dataLabels: {
						enabled: true,
						format: '{point.y} đ'
					}
				}
			},

			tooltip: {
				headerFormat: '<span style="font-size:11px">{series.name}</span><br>',
				pointFormat: '<span style="color:{point.color}">{point.name}</span>: <b>{point.y} đ</b> doanh thu<br/>'
			},

			series: series,
			drilldown: {
				breadcrumbs: {
					position: {
						align: 'right'
					}
				}

			}
		});
	}








	$scope.thongkedoanhthu = function () {
		$http.get("/rest/TKDT").then(resp => {
			$scope.value = resp.data;
			var data = [];
			var series = [];
			for (let i = 0; i < $scope.value.length; i++) {
				var obs = {};
				obs.name = $scope.value[i].name;
				obs.y = $scope.value[i].tien;
				data.push(obs);
			}
			var seriesobj = {
				name: "Doanh số",
				colorByPoint: true,
				data: data
			}
			series.push(seriesobj);
			$scope.chartcolumn('thongkedt', 'Tổng số doanh thu sản phẩm', series);
		});
	}

	$scope.sptg = [];
	$scope.thongkesptg = function(){
		var year = $scope.nam.number;
		var month = $scope.thang.number;
		$http.get(`/rest/TKDT/sanpham/${year}/${month}`).then(resp => {
			$scope.sptg = resp.data;
			console.log(resp.data);
			var data = [];
			var series = [];
			for (let i = 0; i < $scope.sptg.length; i++) {
				var obs = {};
				obs.name = $scope.sptg[i].name;
				obs.y = $scope.sptg[i].tien;
				data.push(obs);
			}
			var seriesobj = {
				name: "Doanh số",
				colorByPoint: true,
				data: data
			}
			series.push(seriesobj);
			$scope.chartcolumn('thongkedt', 'Tổng số doanh thu sản phẩm', series);
		});
	}











	$scope.valuesl = [];
	$scope.thongkesoluong = function () {
		$http.get("/rest/TKSP").then(resp => {
			$scope.valuesl = resp.data;
			var data = [];
			var series = [];
			for (let i = 0; i < $scope.valuesl.length; i++) {
				var obs = {};
				obs.name = $scope.valuesl[i].name;
				obs.y = $scope.valuesl[i].soluong;
				data.push(obs);
			}
			var seriesobj = {
				name: "Số lượng",
				colorByPoint: true,
				data: data
			}
			series.push(seriesobj);
			$scope.piechart('thongkesp','Số lượng sản phẩm bán ra',series);

		});
	}

	$scope.slsp = [];
	$scope.thongkesoluongsptg = function(){
		var year = $scope.namtron.number;
		var month = $scope.thangtron.number;
		$http.get(`/rest/TKSP/sanpham/${year}/${month}`).then(resp => {
			$scope.slsp = resp.data;
			console.log(resp.data);
			var data = [];
			var series = [];
			for (let i = 0; i < $scope.slsp.length; i++) {
				var obs = {};
				obs.name = $scope.valuesl[i].name;
				obs.y = $scope.valuesl[i].soluong;
				data.push(obs);
			}
			var seriesobj = {
				name: "Số lượng",
				colorByPoint: true,
				data: data
			}
			series.push(seriesobj);
			$scope.piechart('thongkesp','Số lượng sản phẩm bán ra',series);
		});
	}


















	$scope.theloaisl = [];
	$scope.thongketheloaisl = function () {
		$http.get("/rest/TKSP/theloai").then(resp => {
			$scope.theloaisl = resp.data;
			var data = [];
			var series = [];
			for (let i = 0; i < $scope.theloaisl.length; i++) {
				var obs = {};
				obs.name = $scope.theloaisl[i].name;
				obs.y = $scope.theloaisl[i].soluong;
				data.push(obs);
			}
			var seriesobj = {
				name: "Số lượng",
				colorByPoint: true,
				data: data
			}
			series.push(seriesobj);
			$scope.piechart('thongketheloaisl', 'Số lượng thể loại bán ra', series);
		});
	}

	$scope.ttsp = [];
	$scope.thongketheloaitltg = function(){
		var year = $scope.namtron1.number;
		var month = $scope.thangtron1.number;
		$http.get(`/rest/TKSP/theloai/${year}/${month}`).then(resp => {
			$scope.ttsp = resp.data;
			console.log(resp.data);
			var data = [];
			var series = [];
			for (let i = 0; i < $scope.ttsp.length; i++) {
				var obs = {};
				obs.name = $scope.ttsp[i].name;
				obs.y = $scope.ttsp[i].soluong;
				data.push(obs);
			}
			var seriesobj = {
				name: "Số lượng",
				colorByPoint: true,
				data: data
			}
			series.push(seriesobj);
			$scope.piechart('thongketheloaisl', 'Số lượng thể loại bán ra', series);
		});
	}
	









	$scope.theloaidt = [];
	$scope.theloaidoanhthu = function () {
		$http.get("/rest/TKDT/theloai").then(resp => {
			$scope.theloaidt = resp.data;
			var data = [];
			var series = [];
			for (let i = 0; i < $scope.theloaidt.length; i++) {
				var obs = {};
				obs.name = $scope.theloaidt[i].name;
				obs.y = $scope.theloaidt[i].tien;
				data.push(obs);
			}
			var seriesobj = {
				name: "Doanh số",
				colorByPoint: true,
				data: data
			}
			series.push(seriesobj);
			$scope.barchart('thongketheloaidt','Doanh thu thể loại',series);
		});

	}
	$scope.tlsl = [];
	$scope.thongketheloaisltl = function(){
		var year = $scope.namtron2.number;
		var month = $scope.thangtron2.number;
		$http.get(`/rest/TKDT/theloai/${year}/${month}`).then(resp => {
			$scope.tlsl = resp.data;
			console.log(resp.data);
			var data = [];
			var series = [];
			for (let i = 0; i < $scope.tlsl.length; i++) {
				var obs = {};
				obs.name = $scope.tlsl[i].name;
				obs.y = $scope.tlsl[i].tien;
				data.push(obs);
			}
			var seriesobj = {
				name: "Doanh số",
				colorByPoint: true,
				data: data
			}
			series.push(seriesobj);
			$scope.barchart('thongketheloaidt','Doanh thu thể loại',series);
		});
	}



	$scope.trangchu();
	$scope.thongkedoanhthu();
	$scope.thongkesoluong();
	$scope.theloaidoanhthu();
	$scope.thongketheloaisl();



});