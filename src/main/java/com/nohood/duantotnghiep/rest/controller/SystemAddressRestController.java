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

import com.nohood.duantotnghiep.entity.DISTRICT;
import com.nohood.duantotnghiep.entity.PROVINCE;
import com.nohood.duantotnghiep.entity.WARD;
import com.nohood.duantotnghiep.service.DISTRICTSERVICE;
import com.nohood.duantotnghiep.service.PROVINCESERVICE;
import com.nohood.duantotnghiep.service.WARDSERVICE;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/system/address")
public class SystemAddressRestController {
	@Autowired
    PROVINCESERVICE proviceservice;
	@Autowired
    DISTRICTSERVICE districtservice;
	@Autowired
    WARDSERVICE wardservice;
	
	@GetMapping("/province")
    public List<PROVINCE> getAll(){
		return proviceservice.findall();
    }
	@PostMapping("/province")
    public void postprovince(@RequestBody List<PROVINCE> provinces){
		proviceservice.createProvinces(provinces);
    }
	@PostMapping("/postone/province")
    public void postoneprovince(@RequestBody PROVINCE province){
		proviceservice.create(province);
    }
	
	
	@GetMapping("/district/{provinceid}")
    public List<DISTRICT> getByProvince(@PathVariable int provinceid){
		return districtservice.findByProvinceid(provinceid);
    }
	@PostMapping("/district")
    public void postdistrict(@RequestBody List<DISTRICT> districts){
		districtservice.createDistricts(districts);
    }
	@PostMapping("/postone/district")
    public void postonedistrict(@RequestBody DISTRICT district){
		districtservice.create(district);
    }
	
	
	
	@GetMapping("/ward/{districtid}")
    public List<WARD> getByDistrict(@PathVariable int districtid){
		return wardservice.findByProvinceid(districtid);
    }
	@GetMapping("/ward/getone/{wardid}")
    public WARD getOne(@PathVariable int wardid){
		return wardservice.findById(wardid);
    }
	@PostMapping("/ward")
    public void postward(@RequestBody List<WARD> wards){
		wardservice.createWards(wards);
    }
	@PostMapping("/postone/ward")
    public void postoneward(@RequestBody WARD ward){
		wardservice.create(ward);
    }
	
}
