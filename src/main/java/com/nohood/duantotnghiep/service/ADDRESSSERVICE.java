package com.nohood.duantotnghiep.service;

import java.util.List;

import com.nohood.duantotnghiep.entity.ADDRESS;

public interface ADDRESSSERVICE {
    List<ADDRESS> findall();
    ADDRESS create(ADDRESS address);
    void delete(ADDRESS address);
	ADDRESS findOne(int addressId);
	void deleteById(int addressid);
	List<ADDRESS> findByUserName(String username);
}
