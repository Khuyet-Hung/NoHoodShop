package com.nohood.duantotnghiep.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nohood.duantotnghiep.service.CARTSERVICE;


@Controller
public class CartController {
    @Autowired
    CARTSERVICE service;
    @RequestMapping("/cart/viewcart")
    private String index() { 
        return "cart/cart.html"; 
    }
} 

