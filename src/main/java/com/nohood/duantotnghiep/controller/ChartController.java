package com.nohood.duantotnghiep.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.nohood.duantotnghiep.service.BILLSERVICE;

@Controller
public class ChartController {
//	@Autowired
//	BILLSERVICE dao;
//	@RequestMapping("/admin/thongke/doanhthu") 
//	public String main(Model model) {
//		List<String> name = dao.sanphammuanhieu().stream().map(x -> x.getName()).collect(Collectors.toList());
//		List<Long> soluong = dao.sanphammuanhieu().stream().map(x -> x.getSoluong()).collect(Collectors.toList());
//		model.addAttribute("name",name); 
//		model.addAttribute("soluong",soluong);
//		return "home/chart"; 
//	}
}
