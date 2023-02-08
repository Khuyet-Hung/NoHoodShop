	package com.nohood.duantotnghiep.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.nohood.UserDetail;
import com.nohood.duantotnghiep.service.ACCOUNTSERVICE;
@Controller
public class AuthController {
	@Autowired
	UserDetail service;
	@Autowired
	ACCOUNTSERVICE acdao;
	@Autowired
	BCryptPasswordEncoder pe;
	@RequestMapping("/auth/login/form")
	public String form() {
		return "login/login";
	} 
	
	@RequestMapping("/auth/login/success")
	public String success(Model model) {
		model.addAttribute("message","Đăng nhập thành công");
		return "redirect:/product/list";
	}  
	
	@RequestMapping("/auth/login/error")
	public String error(Model model) {
		model.addAttribute("message","Sai thông tin đăng nhập");
		return "forward:/auth/login/form";
	} 
	
	
	
	@RequestMapping("/auth/logoff/success")
	public String logsuc(Model model) {
		model.addAttribute("message","Đăng xuất thành công");
		return "forward:/auth/login/form";
	}
	
	 

	@RequestMapping("/oauth2/login/success")
	public String success(OAuth2AuthenticationToken oauth2) {
		service.loginFromOAuth2(oauth2);
		return "forward:/auth/login/success";
	}
	
	
}
