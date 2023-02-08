package com.nohood.duantotnghiep.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "PRODUCT")
public class PRODUCT {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long PRODUCTID; 
    private String PRODUCTNAME;
    private float PRICE;
    private int QUANTITY;

    @JoinColumn(name = "CATEID")
    @ManyToOne
    private CATEGORY categories;
    private String IMAGE;
    @Temporal(TemporalType.TIMESTAMP) 
    private Date DATECREATE = new Date();
    @JsonIgnore
    @OneToMany(mappedBy = "product")
    private List<COMMENT> comments;
    @JsonIgnore
    @OneToMany(mappedBy = "product")
    private List<FAVORITE> favorites;
}
