package com.example.erp.dao;

import com.example.erp.bean.Domains;

import java.util.List;

public interface DomainDao {
    List<Domains> getDomains();
    Domains getDomainByID(Integer id);
}
