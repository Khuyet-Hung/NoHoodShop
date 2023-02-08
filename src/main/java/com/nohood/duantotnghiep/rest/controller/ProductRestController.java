package com.nohood.duantotnghiep.rest.controller; 
import com.nohood.duantotnghiep.entity.PRODUCT;
import com.nohood.duantotnghiep.service.PRODUCTSERVICE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/products")
public class ProductRestController {
    @Autowired
    PRODUCTSERVICE service;


    @GetMapping()
    public List<PRODUCT> getAll() {
        return service.findall();
    }

    @GetMapping("{id}")
    public PRODUCT getOne(@PathVariable("id") long id) {
        return service.findone(id);
    }
    
    @PostMapping()  
    public PRODUCT create(@RequestBody PRODUCT product) {
        return service.create(product);
    }
    @PutMapping("{id}")
    public PRODUCT update(@PathVariable("id") Integer id,@RequestBody PRODUCT product) {
        return service.create(product);
    }
    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") long id) {
        service.deletebyid(id);
    }
}
