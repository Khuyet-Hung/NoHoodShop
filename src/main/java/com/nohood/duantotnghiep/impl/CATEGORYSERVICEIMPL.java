package com.nohood.duantotnghiep.impl;

import com.nohood.duantotnghiep.dao.CARTDAO;
import com.nohood.duantotnghiep.dao.CATEGORYDAO;
import com.nohood.duantotnghiep.entity.CATEGORY;
import com.nohood.duantotnghiep.service.CARTSERVICE;
import com.nohood.duantotnghiep.service.CATEGORYSERVICE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CATEGORYSERVICEIMPL implements CATEGORYSERVICE {
    @Autowired
    CATEGORYDAO dao;
    @Override
    public List<CATEGORY> findall() {
        return dao.findAll();
    }

    @Override
    public CATEGORY create(CATEGORY CATEGORY) {
        return dao.save(CATEGORY);
    }

    @Override
    public void deletebyid(String CATEID) {
        dao.deleteById(CATEID);
    }


    @Override
    public void delete(CATEGORY CATEGORY) {
        dao.delete(CATEGORY);
    }

    @Override
    public CATEGORY findone(String CATEID) {
        return dao.findById(CATEID).get();
    }

	@Override
	public long slcate() {
		// TODO Auto-generated method stub
		return dao.slcate();
	}


}
