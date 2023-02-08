package com.nohood.duantotnghiep.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nohood.duantotnghiep.dao.ACCOUNTDAO;
import com.nohood.duantotnghiep.dao.ADDRESSDAO;
import com.nohood.duantotnghiep.entity.ADDRESS;
import com.nohood.duantotnghiep.entity.PRODUCT;
import com.nohood.duantotnghiep.service.ADDRESSSERVICE;
import com.nohood.duantotnghiep.service.PRODUCTSERVICE;
@Service
public class ADDRESSSERVICEIMPL implements ADDRESSSERVICE{
	@Autowired
    ADDRESSDAO dao;

	@Override
	public List<ADDRESS> findall() {
		return dao.findAll();
	}

	@Override
	public ADDRESS create(ADDRESS address) {
		return dao.save(address);
	}

	@Override
	public void delete(ADDRESS address) {
		dao.delete(address);
	}

	@Override
	public ADDRESS findOne(int addressId) {
		return dao.findById(addressId).get();
	}

	@Override
	public void deleteById(int addressid) {
		dao.deleteById(addressid);
	}

	@Override
	public List<ADDRESS> findByUserName(String username) {
		return dao.findByUserName(username);
	}
	
	
}
