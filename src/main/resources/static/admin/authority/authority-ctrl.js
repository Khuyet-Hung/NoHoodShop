app.controller("authority-ctrl",function($scope,$http,$location){
    $scope.roles =[];
    $scope.admins = [];
    $scope.authorities = [];


    //Hiện danh sách nhân viên
    $scope.intialize = function (){
        $http.get("/rest/roles").then(resp => {
            $scope.roles = resp.data;
        })
        $http.get("/rest/accounts?admin=true").then(resp => {
            $scope.admins = resp.data;
        })

        $http.get("/rest/authorities?admin=true").then(resp => {
            $scope.authorities = resp.data;
        }).catch(error => {
            $location.path("/unauthorized");
        })
    }

    //Check tài khoản có trong dữ liệu có trong $scope.authorities không nếu có sẽ check 
    $scope.authority_of = function(acc,role){
        if($scope.authorities){
            return $scope.authorities.find(ur => ur.account.username == acc.username && ur.role.roleid == role.roleid);
        }
    }

    $scope.authority_changed = function(acc,role){
        var authority = $scope.authority_of(acc,role);
        if(authority){
           $scope.revoke_authority(authority); 
        }else{
            authority = {account: acc,role: role};
            $scope.grant_authority(authority);
        }
    }
    $scope.grant_authority = function(authority){
        $http.post(`/rest/authorities`,authority).then(resp => {
            $scope.authorities.push(resp.data);
            swal({
				title: "Thao tác",
				text: "Thêm thành công",
				icon: "success",
				button: "OK!",
			});
        }).catch(error =>{
            swal({
				title: "Thao tác",
				text: "Thêm thất bại",
				icon: "error",
				button: "OK!",
			});
            console.log("Error",error);
        })
    }
    $scope.revoke_authority = function(authority){
        $http.delete(`/rest/authorities/${authority.roleaccid}`).then(resp => {
            var index = $scope.authorities.findIndex(a => a.roleaccid == authority.roleaccid);
            $scope.authorities.splice(index,1);
            swal({
				title: "Thao tác",
				text: "Xóa thành công",
				icon: "success",
				button: "OK!",
			});
        }).catch(error =>{
            swal({
				title: "Thao tác",
				text: "Xóa thất bại",
				icon: "error",
				button: "OK!",
			});
            console.log("Error",error);
        })
    }
    
    $scope.intialize();
});