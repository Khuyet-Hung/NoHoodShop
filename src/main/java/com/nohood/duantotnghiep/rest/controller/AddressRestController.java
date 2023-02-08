package com.nohood.duantotnghiep.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nohood.duantotnghiep.entity.ADDRESS;
import com.nohood.duantotnghiep.service.ADDRESSSERVICE;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/address")
public class AddressRestController {
	@Autowired
	ADDRESSSERVICE addressService;
	
	@GetMapping("")
    public List<ADDRESS> getAll(){
		return addressService.findall();
    }
	
	@GetMapping("username/{username}")
    public List<ADDRESS> getByUsername(@PathVariable String username){
		return addressService.findByUserName(username);
    }
	
	@GetMapping("{addressId}")
    public ADDRESS getOne(@PathVariable int addressId){
		return addressService.findOne(addressId);
    }
	
	@PostMapping("")
    public void postAddress(@RequestBody ADDRESS address){
		addressService.create(address);
    }
	@DeleteMapping("{addressid}")
    public void deleteAddress(@PathVariable int addressid){
		addressService.deleteById(addressid);
    }
}
