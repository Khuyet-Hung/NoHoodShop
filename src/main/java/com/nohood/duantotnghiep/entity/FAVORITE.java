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
@Table(name = "FAVORITE")
public class FAVORITE {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long FAVID;
    @Temporal(TemporalType.TIMESTAMP)
    private Date FAVDATE;

    @JoinColumn(name = "PRODUCTID")
    @ManyToOne
    private PRODUCT product;

    @JoinColumn(name = "USERNAME")
    @ManyToOne
    private ACCOUNT account;
}
