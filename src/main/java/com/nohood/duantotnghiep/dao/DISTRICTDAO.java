package com.nohood.duantotnghiep.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nohood.duantotnghiep.entity.DISTRICT;

public interface DISTRICTDAO extends JpaRepository<DISTRICT, Integer>{

	@Query("select d from DISTRICT d where d.province.PROVINCEID like ?1")
	List<DISTRICT> findByProvinceid(int provinceid);

}
