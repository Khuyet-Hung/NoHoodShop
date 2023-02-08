package com.nohood.duantotnghiep.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nohood.duantotnghiep.entity.CATEGORY;

public interface CATEGORYDAO extends JpaRepository<CATEGORY,String> {
	@Query("SELECT count(c.CATEID) FROM CATEGORY c")
	long slcate();
}
