package com.nohood.duantotnghiep.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
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
@Table(name = "PROVINCE")
public class PROVINCE {
	@Id
	private int PROVINCEID;
	private String PROVINCENAME;
	
	@JsonIgnore
    @OneToMany(mappedBy = "province")
    private List<DISTRICT> districts;
	
	@JsonIgnore
    @OneToMany(mappedBy = "province")
    private List<ADDRESS> addresses;
}
