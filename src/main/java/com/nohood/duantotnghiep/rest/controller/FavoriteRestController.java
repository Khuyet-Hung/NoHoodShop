package com.nohood.duantotnghiep.rest.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nohood.duantotnghiep.entity.ACCOUNT;
import com.nohood.duantotnghiep.entity.FAVORITE;
import com.nohood.duantotnghiep.entity.PRODUCT;
import com.nohood.duantotnghiep.entity.chart.SOLUONG;
import com.nohood.duantotnghiep.service.FAVORITESERVICE;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/favorite")
public class FavoriteRestController {
	@Autowired
    FAVORITESERVICE service;
	@GetMapping("all")
	public List<FAVORITE> index(){
		return service.findall();
	}
	@GetMapping()
	public FAVORITE getOne(@RequestParam long p, @RequestParam String u) {
		FAVORITE f = service.findByUserAndProduct(p, u);
		if(f != null){
			return service.findByUserAndProduct(p, u);
		}else {
			return null;
		}
	}
	
	@GetMapping("/checkexist")
	public boolean isExist (@RequestParam long p, @RequestParam String u) {
		FAVORITE f = service.findByUserAndProduct(p, u);
		if(f != null){
			return true;
		}else {
			return false;
		}
	}
	 
	@PostMapping()  
    public FAVORITE create(@RequestBody FAVORITE favorite) {
        return service.create(favorite);
    }
	@GetMapping("/finduser/{username}")  
    public List<FAVORITE> finduser(@PathVariable("username") String username) {
        return service.findByUsername(username);
    }
	@DeleteMapping("{id}")
    public void delete(@PathVariable("id") long id) {
        service.deletebyid(id);
    }
	@GetMapping("/yeuthich")
	public List<SOLUONG> soluong() {
       return service.soluongfav();
    }
}
