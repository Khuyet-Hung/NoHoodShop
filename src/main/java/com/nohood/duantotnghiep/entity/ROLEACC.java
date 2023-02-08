package com.nohood.duantotnghiep.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ROLEACC")
public class ROLEACC {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ROLEACCID;
    @JoinColumn(name = "ROLEID")
    @ManyToOne
    private ROLE role;

    @JoinColumn(name = "USERNAME")
    @ManyToOne
    private ACCOUNT account;
}
