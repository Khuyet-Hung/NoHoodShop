package com.nohood.duantotnghiep.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nohood.duantotnghiep.entity.FAVORITE;
import com.nohood.duantotnghiep.service.FAVORITESERVICE;

@Controller
public class FavoriteController {
	FAVORITESERVICE fvservice;
	
    @RequestMapping("/favorite/list")
    public String listFavorite(HttpServletRequest request) {
    	String user = request.getRemoteUser();
    	List<FAVORITE> listfv= fvservice.findByUsername(user);
    	return "favorite/favoritelist";
    }
    
}
