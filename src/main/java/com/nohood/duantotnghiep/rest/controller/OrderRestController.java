package com.nohood.duantotnghiep.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nohood.duantotnghiep.entity.ACCOUNT;
import com.nohood.duantotnghiep.entity.BILL;
import com.nohood.duantotnghiep.service.BILLSERVICE;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/order")
public class OrderRestController {
	@Autowired
	BILLSERVICE billService;
	
	@GetMapping()
    public List<BILL> getAll() {
        return billService.findall();
    }
	
	@GetMapping("{id}")
    public BILL getOne(@PathVariable long id) {
        return billService.findone(id);
    }
	
	@GetMapping("/getbyuser/{username}")
    public List<BILL> getByUser(@PathVariable String username) {
        return billService.findByUser(username);
    }
	
	@PostMapping()
    public BILL createOne(@RequestBody BILL bill) {
        return billService.create(bill);
    }
}
