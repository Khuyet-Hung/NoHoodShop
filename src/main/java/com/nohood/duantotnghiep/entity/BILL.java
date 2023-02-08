package com.nohood.duantotnghiep.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "BILL")
public class BILL {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long BILLID;
    @Temporal(TemporalType.TIMESTAMP) 
    private Date BILLDATE = new Date();
    private int STATUS;
    private int PAYMENTMT; 
    private double SHIPFEE;
    private double SUMPRICE;

    @JoinColumn(name = "ADDRESSID")
    @ManyToOne
    private ADDRESS address;
    @JoinColumn(name = "USERNAME")
    @ManyToOne
    private ACCOUNT account;
    @JsonIgnore
    @OneToMany(mappedBy = "bill")
    private List<BILLDETAIL> billdetails;
}
