package com.nohood.duantotnghiep.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.nohood.duantotnghiep.entity.CART;

import java.util.List;

public interface CARTSERVICE {
    List<CART> findall();
    CART create(CART CART);
    void deletebyid(long CARTID);
    void delete(CART CART);
    CART findone(long CARTID);
	List<CART> findByUsername(String username);
	void deleteByUser(String username);
}
