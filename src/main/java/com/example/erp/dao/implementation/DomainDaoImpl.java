package com.example.erp.dao.implementation;

import com.example.erp.bean.Domains;
import com.example.erp.dao.DomainDao;
import com.example.erp.utils.SessionUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class DomainDaoImpl implements DomainDao {
    @Override
    public List<Domains> getDomains() {
        Session session = SessionUtil.getSession();
        List<Domains> domains = new ArrayList<>();
        try {
            for (final Object domain : session.createQuery("from Domains ").list()) {
                domains.add((Domains) domain);
            }
        } catch (HibernateException exception) {
            System.out.print(exception.getLocalizedMessage());
        }
        return domains;
    }

    @Override
    public Domains getDomainByID(Integer id) {
        try (Session session = SessionUtil.getSession()) {
            return session.get(Domains.class, id);
        } catch (HibernateException exception) {
            System.out.print(exception.getLocalizedMessage());
        }
        return null;
    }
}
