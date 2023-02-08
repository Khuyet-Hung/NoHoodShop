package com.nohood.duantotnghiep.rest.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nohood.duantotnghiep.entity.COMMENT;
import com.nohood.duantotnghiep.entity.FAVORITE;
import com.nohood.duantotnghiep.service.COMMENTSERVICE;
import com.nohood.duantotnghiep.service.FAVORITESERVICE;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/comment")
public class CommentRestController {
	@Autowired
    COMMENTSERVICE service;
	
	@GetMapping()  
    public List<COMMENT> getAll() {
        return service.findall();
    }
	@GetMapping("/getpid/{productid}")  
    public List<COMMENT> getProductid(@PathVariable("productid") long productid) {
        return service.getByProductId(productid);
    }
	@PostMapping()  
    public COMMENT create(@RequestBody COMMENT comment) {
        return service.create(comment);
    }
}
