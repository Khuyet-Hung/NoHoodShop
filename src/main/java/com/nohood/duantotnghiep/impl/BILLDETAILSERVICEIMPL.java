package com.nohood.duantotnghiep.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nohood.duantotnghiep.dao.BILLDAO;
import com.nohood.duantotnghiep.dao.BILLDETAILDAO;
import com.nohood.duantotnghiep.entity.BILL;
import com.nohood.duantotnghiep.entity.BILLDETAIL;
import com.nohood.duantotnghiep.entity.chart.TKDT;
import com.nohood.duantotnghiep.entity.chart.TKSP;
import com.nohood.duantotnghiep.service.BILLDETAILSERVICE;
import com.nohood.duantotnghiep.service.BILLSERVICE;


@Service
public class BILLDETAILSERVICEIMPL implements BILLDETAILSERVICE{

	@Autowired
    BILLDETAILDAO dao;
    @Override
    public List<BILLDETAIL> findall() {
        return dao.findAll();
    }
	@Override
	public BILLDETAIL create(BILLDETAIL BILLDETAIL) {
		return dao.save(BILLDETAIL);
	}
	@Override
	public void deletebyid(long BILLDETAILID) {
	}
	@Override
	public void delete(BILLDETAIL BILLDETAIL) {
		dao.delete(BILLDETAIL);
	}
	@Override
	public BILLDETAIL findone(long BILLDETAILID) {
		return null;
	}
	@Override
	public List<BILLDETAIL> getByBillID(long billid) {
		return dao.finByBillId(billid);
	}
	@Override
	public List<TKSP> sanphammuanhieu() {
		return dao.sanphammuanhieu();
	}
	@Override
	public List<TKDT> sanphamtien() {
		// TODO Auto-generated method stub
		return dao.sanphamtien();
	}
	@Override
	public List<TKDT> theloaitien() {
		// TODO Auto-generated method stub
		return dao.theloaitien();
	}
	@Override
	public List<TKSP> theloaimuanhieu() {
		// TODO Auto-generated method stub
		return dao.theloaimuanhieu();
	}
	@Override
	public List<TKSP> sanphammuanhieutg(int year, int month) {
		// TODO Auto-generated method stub
		return dao.sanphammuanhieutg(year, month);
	}
	@Override
	public List<TKDT> sanphamtientg(int year, int month) {
		// TODO Auto-generated method stub
		return dao.sanphamtientg(year, month);
	}
	@Override
	public List<TKDT> theloaitientg(int year, int month) {
		// TODO Auto-generated method stub
		return dao.theloaitientg(year, month);
	}
	@Override
	public List<TKSP> theloaimuanhieutg(int year, int month) {
		// TODO Auto-generated method stub
		return dao.theloaimuanhieutg(year, month);
	}
	@Override
	public long slspbr() {
		// TODO Auto-generated method stub
		return dao.slspbr();
	}
	@Override
	public double dtspbr() {
		// TODO Auto-generated method stub
		return dao.dtspbr();
	}

}
