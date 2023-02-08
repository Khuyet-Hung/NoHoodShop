package com.nohood.duantotnghiep.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ROLE")
public class ROLE {
    @Id 
    private String ROLEID;
    private String ROLENAME;
    
    @JsonIgnore
    @OneToMany(mappedBy = "role")
    private List<ROLEACC> roleaccs;
}
