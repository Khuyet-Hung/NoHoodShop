package com.nohood.duantotnghiep.dao;

import java.util.Date;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import com.nohood.duantotnghiep.entity.BILL;
import com.nohood.duantotnghiep.entity.chart.TIME;

import org.springframework.data.jpa.repository.Query;
public interface BILLDAO extends JpaRepository<BILL,Long> {
	@Query("SELECT b FROM BILL b WHERE b.account.USERNAME like ?1")
	List<BILL> findByUser(String username);
	@Modifying
	@Transactional
	@Query(value = "UPDATE BILL set STATUS = ?1 where BILLID = ?2", nativeQuery = true)
	int UpdateSl(int status, long BILLID);
	@Modifying
	@Transactional
	@Query(value = "UPDATE BILL set STATUS = ?1, BILLDATE = ?2 where BILLID = ?3", nativeQuery = true)
	int Updatepre(int status,Date billdate, long BILLID);
	
	@Query("Select new com.nohood.duantotnghiep.entity.chart.TIME(YEAR(b.BILLDATE)) from BILL b group by YEAR(b.BILLDATE)")
	List<TIME> getYear();
	
	@Query("Select new com.nohood.duantotnghiep.entity.chart.TIME(MONTH(b.BILLDATE)) from BILL b where YEAR(b.BILLDATE) = ?1 group by MONTH(b.BILLDATE)")
	List<TIME> getMonth(int year);
	
	@Query("Select b from BILL b where b.STATUS = ?1")
	List<BILL> findStatus(int status);
	
}
