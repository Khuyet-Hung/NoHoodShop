package com.nohood.duantotnghiep.rest.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.nohood.duantotnghiep.service.PRODUCTSERVICE;
import com.nohood.duantotnghiep.service.UPLOADSERVICE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@CrossOrigin("*")
@RestController
public class UploadRestController {
    @Autowired
    UPLOADSERVICE service;
    @Autowired
	PRODUCTSERVICE proservice;
    @PostMapping("/rest/upload/{folder}")
    public JsonNode upload(@PathVariable("file") MultipartFile file, @PathVariable("folder") String folder) {
        File savedFile = service.save(file,folder);
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode node = mapper.createObjectNode();
        node.put("name", savedFile.getName());
        node.put("size", savedFile.length());
        return node;
    }
    @PostMapping("/rest/upload/product/{folder}")
	public void uploadproduct(@PathVariable("file") MultipartFile[] file,@PathVariable("folder") long folder) {
		String folder1 = String.valueOf(folder);
		service.saveproduct(file, folder1);
	}
	@PostMapping("/rest/upload/update/{productid}")
	public void update(@PathVariable("file") MultipartFile[] file,@PathVariable("productid") long productid) {
		String folder = String.valueOf(productid);
		service.updateproduct(file, folder);
	}
}

