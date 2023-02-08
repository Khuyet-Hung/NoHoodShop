package com.nohood.duantotnghiep.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import com.nohood.duantotnghiep.entity.ACCOUNT;
import java.util.List;

import javax.transaction.Transactional;

public interface ACCOUNTDAO extends JpaRepository<ACCOUNT, String> {
    @Query("Select a from ACCOUNT a where a.USERNAME like ?1")
    List<ACCOUNT> findName(String username);
    @Query("Select DISTINCT ar.account FROM ROLEACC ar Where ar.role.ROLEID IN ('STAF','SPST')")
    List<ACCOUNT> getAdministrators();
    @Query("Select DISTINCT ar.account FROM ROLEACC ar Where ar.role.ROLEID IN ('CUST','')")
    List<ACCOUNT> getUser();
    
    @Query("SELECT count(a.USERNAME) FROM ACCOUNT a")
	long sluser();
    
    @Modifying
	@Transactional
	@Query(value = "UPDATE ACCOUNT set PASSWORD = ?1 where EMAIL = ?2", nativeQuery = true)
	int UpdatePW(String PASSWORD, String EMAIL);
}
