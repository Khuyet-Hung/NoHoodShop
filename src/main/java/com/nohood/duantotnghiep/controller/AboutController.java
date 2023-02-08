package com.nohood.duantotnghiep.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nohood.duantotnghiep.service.ACCOUNTSERVICE;

@Controller
public class AboutController {
    @RequestMapping("/about")
    public String index() {
        return "about/about";
    }
}
