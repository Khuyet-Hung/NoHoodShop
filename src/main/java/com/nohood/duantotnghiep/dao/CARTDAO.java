package com.nohood.duantotnghiep.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.nohood.duantotnghiep.entity.CART;

public interface CARTDAO extends JpaRepository<CART, Long> {
	@Query("SELECT o FROM CART o where o.account.USERNAME = ?1")
	List<CART> findByUsername(String username);

	@Modifying
	@Transactional
	@Query(value = "UPDATE CART h set h.PHONE = ?1 and h.ADDRESS =?2 where h.CARTID = ?3", nativeQuery = true)
	int UpdateSl(String PHONE, String ADDRESS, long CARTID);

	@Transactional
	@Modifying
	@Query("delete from CART c where c.account.USERNAME like ?1")
	void deleteByUser(String username);

}
