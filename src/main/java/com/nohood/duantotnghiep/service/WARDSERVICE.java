package com.nohood.duantotnghiep.service;

import java.util.List;

import com.nohood.duantotnghiep.entity.DISTRICT;
import com.nohood.duantotnghiep.entity.WARD;

public interface WARDSERVICE {
    List<WARD> findall();
    WARD create(WARD ward);
    void delete(WARD ward);
	void createWards(List<WARD> wards);
	List<WARD> findByProvinceid(int districtid);
	WARD findById(int wardid);
}
