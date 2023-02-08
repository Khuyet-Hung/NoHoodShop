package com.nohood.duantotnghiep.service;

import java.util.List;

import com.nohood.duantotnghiep.entity.FAVORITE;
import com.nohood.duantotnghiep.entity.chart.SOLUONG;

public interface FAVORITESERVICE {
	List<FAVORITE> findall();
    FAVORITE create(FAVORITE FAVORITE);
    void deletebyid(long FAVID);
    void delete(FAVORITE FAVORITE);
    FAVORITE findone(long FAVID);
	FAVORITE findByUserAndProduct(long productId, String username);
	List<FAVORITE> findByUsername(String username);
	List<SOLUONG> soluongfav();
}
