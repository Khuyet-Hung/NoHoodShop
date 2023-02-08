package com.nohood.duantotnghiep.service;

import java.util.List;

import com.nohood.duantotnghiep.entity.ACCOUNT;
import com.nohood.duantotnghiep.entity.DISTRICT;

public interface DISTRICTSERVICE {
    List<DISTRICT> findall();
    DISTRICT create(DISTRICT district);
    void delete(DISTRICT district);
	void createDistricts(List<DISTRICT> districts);
	List<DISTRICT> findByProvinceid(int provinceid);
}
