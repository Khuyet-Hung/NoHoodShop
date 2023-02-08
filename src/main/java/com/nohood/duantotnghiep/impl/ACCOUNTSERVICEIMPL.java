package com.nohood.duantotnghiep.impl;

import com.nohood.duantotnghiep.dao.ACCOUNTDAO;
import com.nohood.duantotnghiep.entity.ACCOUNT;
import com.nohood.duantotnghiep.service.ACCOUNTSERVICE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ACCOUNTSERVICEIMPL implements ACCOUNTSERVICE {
    @Autowired
    ACCOUNTDAO dao;
    @Override
    public List<ACCOUNT> findall() {
        return dao.findAll();
    }

    @Override
    public ACCOUNT create(ACCOUNT account) {
        return dao.save(account);
    }

    @Override
    public void deletebyid(String username) {
        dao.deleteById(username);
    }

    @Override
    public void delete(ACCOUNT account) {
        dao.delete(account);
    }

    @Override
    public ACCOUNT findone(String username) {
    	if(dao.findById(username).isPresent()) {
            return dao.findById(username).get();
    	}
    	return null;
    }

    @Override
    public List<ACCOUNT> findName(String username) {
        return dao.findName(username);
    }

    @Override
    public List<ACCOUNT> getAdministrators() {
        return dao.getAdministrators();
    }

	@Override
	public List<ACCOUNT> getUser() {
		// TODO Auto-generated method stub
		return dao.getUser();
	}

	@Override
	public long sluser() {
		// TODO Auto-generated method stub
		return dao.sluser();
	}

	@Override
	public int UpdatePW(String PASSWORD, String EMAIL) {
		// TODO Auto-generated method stub
		return dao.UpdatePW(PASSWORD, EMAIL);
	}


}
