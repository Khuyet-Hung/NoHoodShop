package com.nohood.duantotnghiep.rest.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.nohood.duantotnghiep.entity.chart.TKDT;
import com.nohood.duantotnghiep.service.BILLDETAILSERVICE;
@CrossOrigin("*")
@RestController
@RequestMapping("/rest/TKDT")
public class TKDTRestController {
	@Autowired
	BILLDETAILSERVICE dao;
	@GetMapping()
	public List<TKDT> thongke(){
		return dao.sanphamtien();
	}
	@GetMapping("theloai")
	public List<TKDT> theloai(){
		return dao.theloaitien();
	}
	@GetMapping("theloai/{year}/{month}")
	public List<TKDT> theloaitg(@PathVariable("year") int year,@PathVariable("month") int month){
		return dao.theloaitientg(year, month);
	}
	@GetMapping("sanpham/{year}/{month}")
	public List<TKDT> sanphamtg(@PathVariable("year") int year,@PathVariable("month") int month){
		return dao.sanphamtientg(year, month);
	}
}
