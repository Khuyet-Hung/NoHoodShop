package com.nohood.duantotnghiep.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nohood.duantotnghiep.service.ACCOUNTSERVICE;

@Controller
public class AccountController {
	@Autowired
	ACCOUNTSERVICE accService;
	
    @RequestMapping("/account/profile")
    public String editinfo(Model model, HttpServletRequest request) {
        return "/account/profile";
    }
    
    @RequestMapping("/account/address")
    public String showAddress(Model model, HttpServletRequest request) {
        return "/account/address";
    }
    
    @RequestMapping("/account/changepass")
    public String showChangePass(Model model, HttpServletRequest request) {
        return "/account/changepass";
    }
    
    @RequestMapping("/account/coin")
    public String showCoinPage(Model model, HttpServletRequest request) {
        return "/account/coin";
    }
    
    @RequestMapping("/account/listorder")
    public String showListOrder(Model model, HttpServletRequest request) {
        return "/account/listorder";
    }
}
