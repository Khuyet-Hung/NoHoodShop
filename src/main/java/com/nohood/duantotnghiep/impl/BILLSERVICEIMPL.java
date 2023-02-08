package com.nohood.duantotnghiep.impl;

import com.nohood.duantotnghiep.dao.BILLDAO;
import com.nohood.duantotnghiep.entity.BILL;
import com.nohood.duantotnghiep.entity.chart.TIME;
import com.nohood.duantotnghiep.service.BILLSERVICE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class BILLSERVICEIMPL implements BILLSERVICE {
    @Autowired
    BILLDAO dao;
    @Override
    public List<BILL> findall() {
        return dao.findAll();
    }

    @Override
    public BILL create(BILL BILL) {
        return dao.save(BILL);
    }

    @Override
    public void deletebyid(long BILLID) {
        dao.deleteById(BILLID);
    }


    @Override
    public void delete(BILL BILL) {
        dao.delete(BILL);
    }

    @Override
    public BILL findone(long BILLID) {
        return dao.findById(BILLID).get();
    }

//	@Override
//	public List<TKSP> sanphammuanhieu() {
//		// TODO Auto-generated method stub
//		return dao.sanphammuanhieu();
//	}
//
//	@Override
//	public List<TKDT> sanphamtien() {
//		
//		return dao.sanphamtien();
//	}
//
//	@Override
//	public int UpdateSl(STATUSPR IDST, long BILLID) {
//		return dao.UpdateSl(IDST, BILLID);
//	}

	@Override
	public List<BILL> findByUser(String username) {
		return dao.findByUser(username);
	}

	@Override
	public int UpdateSl(int status, long BILLID) {
		// TODO Auto-generated method stub
		return dao.UpdateSl(status, BILLID);
	}

	@Override
	public int Updatepre(int status, Date billdate, long BILLID) {
		// TODO Auto-generated method stub
		return dao.Updatepre(status, billdate, BILLID);
	}

	@Override
	public List<TIME> getYear() {
		// TODO Auto-generated method stub
		return dao.getYear();
	}

	@Override
	public List<TIME> getMonth(int year) {
		// TODO Auto-generated method stub
		return dao.getMonth(year);
	}

	@Override
	public List<BILL> findStatus(int status) {
		// TODO Auto-generated method stub
		return dao.findStatus(status);
	}



}
