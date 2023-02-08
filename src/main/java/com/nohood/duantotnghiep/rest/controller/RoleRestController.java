package com.nohood.duantotnghiep.rest.controller;

import com.nohood.duantotnghiep.entity.ROLE;
import com.nohood.duantotnghiep.service.ROLESERVICE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/roles")
public class RoleRestController {
    @Autowired
    ROLESERVICE dao;

    @GetMapping
    public List<ROLE> getAll(){ 
        return dao.findall();
    }
    @GetMapping("nhanvien")
    public List<ROLE> getAdmin(){
    	return dao.getAdmin();
    }
    
}
