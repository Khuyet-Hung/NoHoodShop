package com.nohood.duantotnghiep.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nohood.duantotnghiep.entity.ACCOUNT;
import com.nohood.duantotnghiep.entity.ROLEACC;

import java.util.List;

public interface ROLEACCDAO extends JpaRepository<ROLEACC,Long> {
    @Query("Select DISTINCT a FROM ROLEACC a Where a.account IN ?1")
    List<ROLEACC> Authorityof(List<ACCOUNT> accounts);
}
