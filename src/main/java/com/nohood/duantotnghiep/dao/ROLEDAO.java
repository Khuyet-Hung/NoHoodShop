package com.nohood.duantotnghiep.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.nohood.duantotnghiep.entity.ROLE;

public interface ROLEDAO extends JpaRepository<ROLE,String> {
	 @Query("Select DISTINCT ar FROM ROLE ar Where ar.ROLEID IN ('SPST','STAF')")
	 List<ROLE> getAdmin();
}
