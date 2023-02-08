package com.nohood.duantotnghiep.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.nohood.duantotnghiep.service.CATEGORYSERVICE;


@Component
public class GobalInter implements HandlerInterceptor{
	@Autowired
	CATEGORYSERVICE service;
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		request.setAttribute("cates", service.findall());
	
	}
	
}
