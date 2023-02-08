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
@Table(name = "WARD")
public class WARD {
	@Id
	private int WARDID;
	private String WARDNAME;
	
	
	@JoinColumn(name = "DISTRICTID")
    @ManyToOne
	private DISTRICT district;
	
	@JsonIgnore
    @OneToMany(mappedBy = "ward")
    private List<ADDRESS> adresses;
}
