package com.nohood.duantotnghiep.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nohood.duantotnghiep.service.BILLSERVICE;

@Controller
public class OrderController {
	@Autowired
    BILLSERVICE service;
	
	@RequestMapping("/order/purchase")
    private String index() { 
        return "order/purchase.html"; 
    }
}
