package com.nohood.duantotnghiep.impl;

import com.nohood.duantotnghiep.dao.COMMENTDAO;
import com.nohood.duantotnghiep.dao.FAVORITEDAO;
import com.nohood.duantotnghiep.entity.FAVORITE;
import com.nohood.duantotnghiep.entity.chart.SOLUONG;
import com.nohood.duantotnghiep.service.COMMENTSERVICE;
import com.nohood.duantotnghiep.service.FAVORITESERVICE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FAVORITESERVICEIMPL implements FAVORITESERVICE {
    @Autowired
    FAVORITEDAO dao;
    @Override
    public List<FAVORITE> findall() {
        return dao.findAll();
    }

    @Override
    public FAVORITE create(FAVORITE FAVORITE) {
        return dao.save(FAVORITE);
    }

    @Override
    public void deletebyid(long FAVID) {
        dao.deleteById(FAVID);
    }

    @Override
    public void delete(FAVORITE FAVORITE) {
        dao.delete(FAVORITE);
    }

    @Override
    public FAVORITE findone(long FAVID) {
        return dao.findById(FAVID).get();
    }

	@Override
	public FAVORITE findByUserAndProduct(long productId, String username) {
		List<FAVORITE> lf = dao.findByUserAndProduct(productId, username);
		if(lf.size() == 0) {
			return null;
		}
		return lf.get(0);
	}

	@Override
	public List<FAVORITE> findByUsername(String username) {
		List<FAVORITE> lf = dao.findByUser(username);
		return lf;
	}

	@Override
	public List<SOLUONG> soluongfav() {
		// TODO Auto-generated method stub
		return dao.soluongfav(); 
	}


}
