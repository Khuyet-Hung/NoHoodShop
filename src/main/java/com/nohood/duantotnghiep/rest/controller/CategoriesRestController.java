package com.nohood.duantotnghiep.rest.controller;

import com.nohood.duantotnghiep.entity.CATEGORY;
import com.nohood.duantotnghiep.service.CATEGORYSERVICE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/categories")
public class CategoriesRestController {
    @Autowired
    CATEGORYSERVICE service;
    @PostMapping()
    public CATEGORY post(@RequestBody CATEGORY Category){
        service.create(Category);
        return Category;
    }
    @DeleteMapping("{names}")
    public void delete(@PathVariable("names") String names){
        service.deletebyid(names);
    }
    @GetMapping() 
    public List<CATEGORY> getAll() {
        return service.findall(); 
    } 

    @GetMapping("{names}") 
    public CATEGORY getOne(@PathVariable("names") String names){
        return service.findone(names);
    }
    
    @PutMapping("{names}")
    public CATEGORY put(@PathVariable("names") String names,@RequestBody CATEGORY Category){
        service.create(Category);
        return Category;
    }

   
    @GetMapping("soluong") 
    public long getsoluong(){
        return service.slcate();
    }
}
