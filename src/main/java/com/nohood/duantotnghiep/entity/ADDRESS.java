package com.nohood.duantotnghiep.entity;

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
@Table(name = "ADDRESS")
public class ADDRESS {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int ADDRESSID;
	private String STREETNAME;
	private String FULLNAME;
	private String PHONENUMBER;
	private boolean ISDEFAULT;
	
	@JoinColumn(name = "PROVINCEID")
    @ManyToOne
	private PROVINCE province;
	
	@JoinColumn(name = "DISTRICTID")
    @ManyToOne
	private DISTRICT district;
	
	@JoinColumn(name = "WARDID")
    @ManyToOne
	private WARD ward;
	
	@JoinColumn(name = "USERNAME")
    @ManyToOne
	private ACCOUNT account;
}
