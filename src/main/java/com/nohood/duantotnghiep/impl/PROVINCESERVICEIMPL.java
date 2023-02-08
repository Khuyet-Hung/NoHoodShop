package com.nohood.duantotnghiep.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nohood.duantotnghiep.dao.ACCOUNTDAO;
import com.nohood.duantotnghiep.dao.PROVINCEDAO;
import com.nohood.duantotnghiep.entity.DISTRICT;
import com.nohood.duantotnghiep.entity.PRODUCT;
import com.nohood.duantotnghiep.entity.PROVINCE;
import com.nohood.duantotnghiep.service.PROVINCESERVICE;
@Service
public class PROVINCESERVICEIMPL implements PROVINCESERVICE{
	@Autowired
    PROVINCEDAO dao;

	@Override
	public List<PROVINCE> findall() {
		return dao.findAll();
	}

	@Override
	public PROVINCE create(PROVINCE province) {
		return dao.save(province);
	}

	@Override
	public void delete(PROVINCE province) {
		dao.delete(province);
	}

	@Override
	public void createProvinces(List<PROVINCE> provinces) {
		provinces.forEach(item -> {
			dao.save(item);
		});
	}

}
