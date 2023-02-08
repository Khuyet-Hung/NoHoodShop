package com.nohood.duantotnghiep.service;

import java.util.List;

import com.nohood.duantotnghiep.entity.ACCOUNT;
import com.nohood.duantotnghiep.entity.DISTRICT;
import com.nohood.duantotnghiep.entity.PROVINCE;

public interface PROVINCESERVICE {
    List<PROVINCE> findall();
    PROVINCE create(PROVINCE provice);
    void delete(PROVINCE provice);
	void createProvinces(List<PROVINCE> provices);
}
