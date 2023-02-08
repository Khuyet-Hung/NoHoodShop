package com.nohood.duantotnghiep.service;

import com.nohood.duantotnghiep.entity.BILL;
import com.nohood.duantotnghiep.entity.chart.TIME;

import java.util.Date;
import java.util.List;

public interface BILLSERVICE {
    List<BILL> findall();
    BILL create(BILL bill);
    void deletebyid(long BILLID);
    void delete(BILL bill);
    BILL findone(long BILLID);
	List<BILL> findByUser(String username);
	int UpdateSl(int status, long BILLID);
	int Updatepre(int status,Date billdate, long BILLID);
	List<TIME> getYear();
	List<TIME> getMonth(int year);
	List<BILL> findStatus(int status);
}
