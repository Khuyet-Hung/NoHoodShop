package com.nohood.duantotnghiep.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nohood.duantotnghiep.entity.COMMENT;


public interface COMMENTDAO extends JpaRepository<COMMENT,Long> {
	@Query("select c from COMMENT c where c.product.PRODUCTID = ?1")
	List<COMMENT> getByProductId(long productId);

}
