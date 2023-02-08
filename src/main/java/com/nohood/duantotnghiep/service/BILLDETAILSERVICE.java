package com.nohood.duantotnghiep.service;

import java.util.List;

import com.nohood.duantotnghiep.entity.BILLDETAIL;
import com.nohood.duantotnghiep.entity.chart.TKDT;
import com.nohood.duantotnghiep.entity.chart.TKSP;

public interface BILLDETAILSERVICE {
	List<BILLDETAIL> findall();
    BILLDETAIL create(BILLDETAIL BILLDETAIL);
    void deletebyid(long BILLDETAILID);
    void delete(BILLDETAIL BILLDETAIL);
    BILLDETAIL findone(long BILLDETAILID);
	List<BILLDETAIL> getByBillID(long billid);
	List<TKSP> sanphammuanhieu();
	List<TKDT> sanphamtien();
	List<TKDT> theloaitien();
	List<TKSP> theloaimuanhieu(); 
	List<TKSP> sanphammuanhieutg(int year,int month);
	List<TKDT> sanphamtientg(int year,int month);
	List<TKDT> theloaitientg(int year,int month);
	List<TKSP> theloaimuanhieutg(int year,int month); 
	long slspbr();
	double dtspbr();
}
