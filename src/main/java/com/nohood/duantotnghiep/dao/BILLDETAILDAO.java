package com.nohood.duantotnghiep.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nohood.duantotnghiep.entity.BILLDETAIL;
import com.nohood.duantotnghiep.entity.chart.TKDT;
import com.nohood.duantotnghiep.entity.chart.TKSP;

public interface BILLDETAILDAO extends JpaRepository<BILLDETAIL, Integer>{
	@Query("SELECT b FROM BILLDETAIL b WHERE b.bill.BILLID like ?1")
	List<BILLDETAIL> finByBillId(long billid);
	@Query("SELECT new com.nohood.duantotnghiep.entity.chart.TKSP(ord.product.PRODUCTNAME,sum(ord.QUANTITY)) FROM BILLDETAIL ord group by ord.product.PRODUCTNAME")
	List<TKSP> sanphammuanhieu();
	@Query("SELECT new com.nohood.duantotnghiep.entity.chart.TKDT(ord.product.PRODUCTNAME,sum(ord.SUMPRICE*ord.QUANTITY)) FROM BILLDETAIL ord group by ord.product.PRODUCTNAME")
	List<TKDT> sanphamtien();
	@Query("SELECT new com.nohood.duantotnghiep.entity.chart.TKDT(ord.product.categories.CATENAME,sum(ord.SUMPRICE*ord.QUANTITY)) FROM BILLDETAIL ord group by ord.product.categories.CATENAME")
	List<TKDT> theloaitien();
	@Query("SELECT new com.nohood.duantotnghiep.entity.chart.TKSP(ord.product.categories.CATENAME,sum(ord.QUANTITY)) FROM BILLDETAIL ord group by ord.product.categories.CATENAME")
	List<TKSP> theloaimuanhieu(); 
	
	@Query("SELECT new com.nohood.duantotnghiep.entity.chart.TKSP(ord.product.PRODUCTNAME,sum(ord.QUANTITY)) FROM BILLDETAIL ord where year(ord.bill.BILLDATE) = ?1 and month(ord.bill.BILLDATE) = ?2  group by ord.product.PRODUCTNAME")
	List<TKSP> sanphammuanhieutg(int year,int month);
	@Query("SELECT new com.nohood.duantotnghiep.entity.chart.TKDT(ord.product.PRODUCTNAME,sum(ord.SUMPRICE*ord.QUANTITY)) FROM BILLDETAIL ord where year(ord.bill.BILLDATE) = ?1 and month(ord.bill.BILLDATE) = ?2 group by ord.product.PRODUCTNAME")
	List<TKDT> sanphamtientg(int year,int month);
	@Query("SELECT new com.nohood.duantotnghiep.entity.chart.TKDT(ord.product.categories.CATENAME,sum(ord.SUMPRICE*ord.QUANTITY)) FROM BILLDETAIL ord where year(ord.bill.BILLDATE) = ?1 and month(ord.bill.BILLDATE) = ?2 group by ord.product.categories.CATENAME")
	List<TKDT> theloaitientg(int year,int month);
	@Query("SELECT new com.nohood.duantotnghiep.entity.chart.TKSP(ord.product.categories.CATENAME,sum(ord.QUANTITY)) FROM BILLDETAIL ord where year(ord.bill.BILLDATE) = ?1 and month(ord.bill.BILLDATE) = ?2 group by ord.product.categories.CATENAME")
	List<TKSP> theloaimuanhieutg(int year,int month);
	
	@Query("SELECT sum(ord.QUANTITY) FROM BILLDETAIL ord")
	long slspbr();
	 
	@Query("SELECT sum(ord.QUANTITY*ord.SUMPRICE) FROM BILLDETAIL ord")
	double dtspbr();


	
}
 