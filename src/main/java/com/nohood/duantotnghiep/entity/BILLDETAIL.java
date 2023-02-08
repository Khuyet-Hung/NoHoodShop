package com.nohood.duantotnghiep.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "BILLDETAILS")
public class BILLDETAIL {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int BILLDETAILID;
	private double SUMPRICE;
	private int QUANTITY;
	
	@JoinColumn(name = "PRODUCTID")
    @ManyToOne
	private PRODUCT product;
	@JoinColumn(name = "BILLID")
    @ManyToOne
	private BILL bill;
	
}
