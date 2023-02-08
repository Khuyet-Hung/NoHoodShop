package com.nohood.duantotnghiep.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nohood.duantotnghiep.entity.DISTRICT;
import com.nohood.duantotnghiep.entity.WARD;

public interface WARDDAO extends JpaRepository<WARD, Integer>{

	@Query("select w from WARD w where w.district.DISTRICTID like ?1")
	List<WARD> findByDistrictid(int districtid);

}
