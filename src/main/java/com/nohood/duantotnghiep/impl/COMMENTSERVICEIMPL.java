package com.nohood.duantotnghiep.impl;

import com.nohood.duantotnghiep.dao.CARTDAO;
import com.nohood.duantotnghiep.dao.COMMENTDAO;
import com.nohood.duantotnghiep.entity.COMMENT;
import com.nohood.duantotnghiep.service.CARTSERVICE;
import com.nohood.duantotnghiep.service.COMMENTSERVICE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class COMMENTSERVICEIMPL implements COMMENTSERVICE {
    @Autowired
    COMMENTDAO dao;
    @Override
    public List<COMMENT> findall() {
        return dao.findAll();
    }

    @Override
    public COMMENT create(COMMENT COMMENT) {
        return dao.save(COMMENT);
    }

    @Override
    public void deletebyid(long CMTID) {
        dao.deleteById(CMTID);
    }

    @Override
    public void delete(COMMENT COMMENT) {
        dao.delete(COMMENT);
    }

    @Override
    public COMMENT findone(long CMTID) {
        return dao.findById(CMTID).get();
    }

	@Override
	public List<COMMENT> getByProductId(long productId) {
		return dao.getByProductId(productId);
	}


}
