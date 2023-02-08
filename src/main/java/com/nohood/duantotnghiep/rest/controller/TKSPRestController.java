package com.nohood.duantotnghiep.rest.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nohood.duantotnghiep.entity.chart.TKDT;
import com.nohood.duantotnghiep.entity.chart.TKSP;
import com.nohood.duantotnghiep.service.BILLDETAILSERVICE;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/TKSP")
public class TKSPRestController {
	@Autowired
	BILLDETAILSERVICE dao;
	@GetMapping()
	public List<TKSP> thongke(){
		return dao.sanphammuanhieu();
	}
	@GetMapping("theloai")
	public List<TKSP> thongkett(){
		return dao.theloaimuanhieu();
	}
	@GetMapping("theloai/{year}/{month}")
	public List<TKSP> theloaitg(@PathVariable("year") int year,@PathVariable("month") int month){
		return dao.theloaimuanhieutg(year, month);
	}
	@GetMapping("sanpham/{year}/{month}")
	public List<TKSP> sanphamtg(@PathVariable("year") int year,@PathVariable("month") int month){
		return dao.sanphammuanhieutg(year, month);
	}
}
