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

import com.nohood.duantotnghiep.entity.BILLDETAIL;
import com.nohood.duantotnghiep.service.BILLDETAILSERVICE;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/orderdetail")
public class OrderDetailRestController {
	@Autowired
	BILLDETAILSERVICE billdetailService;
	
	@GetMapping("/getbybill/{billid}")
    public List<BILLDETAIL> getByBillId(@PathVariable long billid) {
		return billdetailService.getByBillID(billid);
    }
	@PostMapping()
    public void createList(@RequestBody List<BILLDETAIL> bills) {
		bills.forEach(bill -> {
	         billdetailService.create(bill);
		});
    }
	@GetMapping("tongdt")
	public double getdoanhthu() {
		return billdetailService.dtspbr();
	}
	@GetMapping("tongsl")
	public long getsoluong() {
		return billdetailService.slspbr();
	}
}
