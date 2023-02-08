package com.nohood.duantotnghiep.service;

import java.util.List;

import com.nohood.duantotnghiep.entity.COMMENT;

public interface COMMENTSERVICE {
    List<COMMENT> findall();
    COMMENT create(COMMENT COMMENT);
    void deletebyid(long CMTID);
    void delete(COMMENT COMMENT);
    COMMENT findone(long CMTID);
	List<COMMENT> getByProductId(long productId);
}
