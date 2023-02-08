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
@Table(name = "CATEGORY")

public class CATEGORY {
    @Id
    private String CATEID;
    private String CATENAME;
    @JsonIgnore
    @OneToMany(mappedBy = "PRODUCTID")
    private List<PRODUCT> products;
}
