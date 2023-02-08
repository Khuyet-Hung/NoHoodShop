package com.nohood.duantotnghiep.service;

import java.util.List;

import com.nohood.duantotnghiep.entity.ROLEACC;

public interface ROLEACCSERVICE {
    List<ROLEACC> findall();
    ROLEACC create(ROLEACC ROLEACC);
    void deletebyid(long ROLEACCID);
    void delete(ROLEACC ROLEACC);
    ROLEACC findone(long ROLEACCID);
    List<ROLEACC> Authorityof();
}
