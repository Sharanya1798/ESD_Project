package com.example.erp.service;

import com.example.erp.bean.Domains;
import com.example.erp.dao.DomainDao;
import com.example.erp.dao.implementation.DomainDaoImpl;

import java.util.List;

public class DomainService {
    DomainDao domainDao = new DomainDaoImpl();

    public List<Domains> getDomains (){
        return domainDao.getDomains();
    }

    public Domains getDomainByID(Integer id){
        return domainDao.getDomainByID(id);
    }
}
