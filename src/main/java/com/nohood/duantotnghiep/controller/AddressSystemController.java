package com.nohood.duantotnghiep.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nohood.duantotnghiep.entity.ACCOUNT;

@Controller
public class AddressSystemController {
	@RequestMapping("/admin/address")
    public String editinfo() {
        return "/test/address";
    }
}
