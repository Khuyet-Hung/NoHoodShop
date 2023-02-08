package com.nohood.duantotnghiep.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nohood.duantotnghiep.entity.ADDRESS;

public interface ADDRESSDAO extends JpaRepository<ADDRESS, Integer>{

	@Query("Select a from ADDRESS a where a.account.USERNAME like ?1")
	List<ADDRESS> findByUserName(String username);

}
