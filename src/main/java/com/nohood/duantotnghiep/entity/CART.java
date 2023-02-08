package com.nohood.duantotnghiep.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "CART")
public class CART {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long CARTID;
    private int QUANTITY;
    private int SUMPRICE;
  
    @JoinColumn(name = "USERNAME")
    @ManyToOne
    private ACCOUNT account;
    @JoinColumn(name = "PRODUCTID")
    @ManyToOne
    private PRODUCT product;
}
