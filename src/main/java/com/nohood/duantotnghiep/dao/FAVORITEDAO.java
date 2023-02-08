package com.nohood.duantotnghiep.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nohood.duantotnghiep.entity.FAVORITE;
import com.nohood.duantotnghiep.entity.chart.SOLUONG;

@Repository
public interface FAVORITEDAO extends JpaRepository<FAVORITE,Long> {
	@Query("select u from FAVORITE u where u.product.PRODUCTID = ?1 and u.account.USERNAME = ?2")
	List<FAVORITE> findByUserAndProduct(long productId, String username);
	
	@Query("select u from FAVORITE u where u.account.USERNAME = ?1")
	List<FAVORITE> findByUser(String username);
	
	@Query("SELECT new com.nohood.duantotnghiep.entity.chart.SOLUONG(fav.product.PRODUCTID,count(fav.product.PRODUCTID)) FROM FAVORITE fav group by fav.product.PRODUCTID")
	List<SOLUONG> soluongfav();
}
