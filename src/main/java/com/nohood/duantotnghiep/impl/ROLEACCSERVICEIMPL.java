package com.nohood.duantotnghiep.impl;

import com.nohood.duantotnghiep.dao.ACCOUNTDAO;
import com.nohood.duantotnghiep.dao.ROLEACCDAO;
import com.nohood.duantotnghiep.entity.ACCOUNT;
import com.nohood.duantotnghiep.entity.ROLEACC;
import com.nohood.duantotnghiep.service.ROLEACCSERVICE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ROLEACCSERVICEIMPL implements ROLEACCSERVICE {
    @Autowired
    ROLEACCDAO dao;
    @Autowired
    ACCOUNTDAO acdao;
    @Override
    public List<ROLEACC> Authorityof() {
        List<ACCOUNT> accounts = acdao.getAdministrators();
        return dao.Authorityof(accounts);
    }

    @Override
    public List<ROLEACC> findall() {
        return dao.findAll();
    }

    @Override
    public ROLEACC create(ROLEACC ROLEACC) {
        return dao.save(ROLEACC);
    }

    @Override
    public void deletebyid(long ROLEACCID) {
        dao.deleteById(ROLEACCID);
    }

    @Override
    public void delete(ROLEACC ROLEACC) {
        dao.delete(ROLEACC);
    }

    @Override
    public ROLEACC findone(long ROLEACCID) {
        return dao.findById(ROLEACCID).get();
    }



}
