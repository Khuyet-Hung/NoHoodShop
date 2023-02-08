package com.nohood.duantotnghiep.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class BlogController {
    @RequestMapping("/blog")
    public String index() {
        return "blog/blog";
    }
    
    @RequestMapping("/blog/blogdetail")
    public String blogdetail() {
        return "blog/blog-detail";
    }
}
