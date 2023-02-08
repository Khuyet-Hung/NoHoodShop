package com.nohood.duantotnghiep.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "COMMENT")
public class COMMENT {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long CMTID;
    @Temporal(TemporalType.TIMESTAMP)
    
    private Date CMTDATE;
    private String CONTENT;

    @JoinColumn(name = "PRODUCTID")
    @ManyToOne
    private PRODUCT product;

    @JoinColumn(name = "USERNAME")
    @ManyToOne
    private ACCOUNT account;
}
