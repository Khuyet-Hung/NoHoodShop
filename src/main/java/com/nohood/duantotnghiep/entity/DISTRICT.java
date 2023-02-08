package com.nohood.duantotnghiep.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "DISTRICT")
public class DISTRICT {
	@Id
	private int DISTRICTID;
	private String DISTRICTNAME;
	
	
	@JoinColumn(name = "PROVINCEID")
    @ManyToOne
	private PROVINCE province;
	
	@JsonIgnore
    @OneToMany(mappedBy = "district")
    private List<WARD> wards;
	
	@JsonIgnore
    @OneToMany(mappedBy = "district")
    private List<ADDRESS> adresses;
}
