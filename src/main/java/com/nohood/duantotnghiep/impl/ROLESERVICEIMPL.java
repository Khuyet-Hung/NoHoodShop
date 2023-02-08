package com.nohood.duantotnghiep.impl;

import com.nohood.duantotnghiep.dao.COMMENTDAO;
import com.nohood.duantotnghiep.dao.ROLEDAO;
import com.nohood.duantotnghiep.entity.ROLE;
import com.nohood.duantotnghiep.service.COMMENTSERVICE;
import com.nohood.duantotnghiep.service.ROLESERVICE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ROLESERVICEIMPL implements ROLESERVICE {
    @Autowired
    ROLEDAO dao;
    @Override
    public List<ROLE> findall() {
        return dao.findAll();
    }

    @Override
    public ROLE create(ROLE ROLE) {
        return dao.save(ROLE);
    }

    @Override
    public void deletebyid(String ROLEID) {
        dao.deleteById(ROLEID);
    }

    @Override
    public void delete(ROLE ROLE) {
        dao.delete(ROLE);
    }

    @Override
    public ROLE findone(String ROLEID) {
        return dao.findById(ROLEID).get();
    }

	@Override
	public List<ROLE> getAdmin() {
		// TODO Auto-generated method stub
		return dao.getAdmin();
	}


}
