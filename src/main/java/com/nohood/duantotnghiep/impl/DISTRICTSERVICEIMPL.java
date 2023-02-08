package com.nohood.duantotnghiep.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nohood.duantotnghiep.dao.ACCOUNTDAO;
import com.nohood.duantotnghiep.dao.DISTRICTDAO;
import com.nohood.duantotnghiep.entity.DISTRICT;
import com.nohood.duantotnghiep.entity.PRODUCT;
import com.nohood.duantotnghiep.service.DISTRICTSERVICE;
import com.nohood.duantotnghiep.service.PRODUCTSERVICE;
@Service
public class DISTRICTSERVICEIMPL implements DISTRICTSERVICE{
	@Autowired
    DISTRICTDAO dao;

	@Override
	public List<DISTRICT> findall() {
		return dao.findAll();
	}

	@Override
	public DISTRICT create(DISTRICT district) {
		return dao.save(district);
	}

	@Override
	public void delete(DISTRICT district) {
		dao.delete(district);		
	}

	@Override
	public void createDistricts(List<DISTRICT> districts) {
		districts.forEach(item -> {
			try {
				dao.save(item);
			} catch (Exception e) {
				System.out.println("Lỗi: " + e);
			}
		});
	}

	@Override
	public List<DISTRICT> findByProvinceid(int provinceid) {
		return dao.findByProvinceid(provinceid);
	}

}
