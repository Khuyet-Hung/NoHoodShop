package com.nohood.duantotnghiep.impl;

import com.nohood.duantotnghiep.dao.COMMENTDAO;
import com.nohood.duantotnghiep.dao.PRODUCTDAO;
import com.nohood.duantotnghiep.entity.NOTID;
import com.nohood.duantotnghiep.entity.PRODUCT;
import com.nohood.duantotnghiep.service.COMMENTSERVICE;
import com.nohood.duantotnghiep.service.PRODUCTSERVICE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PRODUCTSERVICEIMPL implements PRODUCTSERVICE {
    @Autowired
    PRODUCTDAO dao;
    @Override
    public List<PRODUCT> findall() {
        return dao.findAll();
    }

    @Override
    public PRODUCT create(PRODUCT PRODUCT) {
        return dao.save(PRODUCT);
    }

    @Override 
    public void deletebyid(long PRODUCTID) {
        dao.deleteById(PRODUCTID);
    }

    @Override
    public void delete(PRODUCT PRODUCT) {
        dao.delete(PRODUCT);
    }

    @Override
    public PRODUCT findone(long PRODUCTID) {
        return dao.findById(PRODUCTID).get();
    }

    @Override
    public List<PRODUCT> findByCategoryId(String id) {
        return dao.findByCategoryId(id);
    }
	@Override
	public NOTID getNotId() {
		// TODO Auto-generated method stub
		return dao.getNotId();
	}


}
