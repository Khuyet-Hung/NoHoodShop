package com.nohood.duantotnghiep.controller;

import com.nohood.duantotnghiep.entity.COMMENT;
import com.nohood.duantotnghiep.entity.FAVORITE;
import com.nohood.duantotnghiep.entity.PRODUCT;
import com.nohood.duantotnghiep.service.ACCOUNTSERVICE;
import com.nohood.duantotnghiep.service.COMMENTSERVICE;
import com.nohood.duantotnghiep.service.FAVORITESERVICE;
import com.nohood.duantotnghiep.service.PRODUCTSERVICE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ProductController {
	@Autowired
    PRODUCTSERVICE prservice;
    @Autowired
    ACCOUNTSERVICE accservice;
    @Autowired
    FAVORITESERVICE fvservice;
    @Autowired
    COMMENTSERVICE cmtservice;
    @RequestMapping("/product/list")
    private String list(Model model,@RequestParam("cid") Optional<String> cid) {
        if(cid.isPresent()) {
            List<PRODUCT> list = prservice.findByCategoryId(cid.get());
            model.addAttribute("items",list);
        }else { 
            List<PRODUCT> list = prservice.findall();
            for(PRODUCT pr : list) {
            	 System.out.print(pr.getPRODUCTNAME());
            }
           
            model.addAttribute("items",list);
        }
  
        return "/product/product";
    }

    @RequestMapping("/product/detail/{id}")
    private String findid(Model model,@PathVariable("id") long id, HttpServletRequest request) {
        PRODUCT item = prservice.findone(id);
        String username = request.getRemoteUser();
        model.addAttribute("isLike", isLike(id, username));
        model.addAttribute("listComment", getByProduct(id));
        model.addAttribute("item",item);
        return "product/product-detail.html";
    }
    
    private List<COMMENT> getByProduct(long productId){
    	return cmtservice.getByProductId(productId);
    }
    
    private Boolean isLike(long productId, String username) {
		Optional<FAVORITE> f = Optional.ofNullable(fvservice.findByUserAndProduct(productId, username));
		if(f.isPresent()) {
			return true;
		}
		return false;
	}
}
