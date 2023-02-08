package com.nohood.duantotnghiep.impl;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nohood.duantotnghiep.dao.BILLDAO;
import com.nohood.duantotnghiep.dao.CARTDAO;
import com.nohood.duantotnghiep.entity.BILL;
import com.nohood.duantotnghiep.entity.CART;
import com.nohood.duantotnghiep.service.BILLSERVICE;
import com.nohood.duantotnghiep.service.CARTSERVICE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CARTSERVICEIMPL implements CARTSERVICE {
    @Autowired
    CARTDAO dao;
    @Autowired
    BILLDAO bdao;
    @Override
    public List<CART> findall() {
        return dao.findAll();
    }

    @Override
    public CART create(CART CART) {
        return dao.save(CART);
    }

    @Override
    public void deletebyid(long CARTID) {
        dao.deleteById(CARTID);
    }


    @Override
    public void delete(CART CART) {
        dao.delete(CART);
    }

    @Override
    public CART findone(long CARTID) {
        return dao.findById(CARTID).get();
    }

	@Override
	public List<CART> findByUsername(String username) {
		return dao.findByUsername(username);
	}

	@Override
	public void deleteByUser(String username) {
		dao.deleteByUser(username);
	}

}
