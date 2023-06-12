package com.example.erp.dao.implementation;

import com.example.erp.bean.Students;
import com.example.erp.dao.StudentDao;
import com.example.erp.utils.SessionUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class StudentDaoImpl implements StudentDao {

    @Override
    public List<Students> emailVerify(Students student) {
        List<Students> students = new ArrayList<>();
        Session session = SessionUtil.getSession();
        try {
            Query query = session.createQuery("from Students where email=:email");
            query.setParameter("email", student.getEmail());
            System.out.println(student.getEmail());
            for (final Object fetch : query.list()) {
                System.out.println(fetch);
                 students.add((Students) fetch);
            }

        } catch (HibernateException exception) {
            System.out.print(exception.getLocalizedMessage());
            return null;
        }finally {
            session.close();
        }
        return students;
    }

    @Override
    public int registerStudent(Students student) {
        Session session = SessionUtil.getSession();
        try {
            Transaction transaction = session.beginTransaction();
            Query query = session.createQuery("from Students where domain.domain_id =:domain_id");
            query.setParameter("domain_id", student.getDomain().getDomain_id());
            if(query.getResultList().size()==student.getDomain().getCapacity()) {
                return 2;
            }
            student.setRoll(student.getEmail());
            student.setPhotograph_path("dummy");
            session.save(student);
            transaction.commit();
            return 1;
        } catch (HibernateException exception) {
            System.out.print(exception.getLocalizedMessage());
            return 3;
        }finally {
            session.close();
        }
    }


    @Override
    public boolean photoPathInsertion(Integer id, String path) {
        Session session = SessionUtil.getSession();
        try {
            Transaction transaction = session.beginTransaction();
            Query query = session.createQuery("update Students set photograph_path =: path where student_id =:id");
            query.setParameter("path", path);
            query.setParameter("id", id);
            int result = query.executeUpdate();
            transaction.commit();
            return result == 1;
        } catch (HibernateException exception)
        {
            System.out.println(exception.getLocalizedMessage());
            return false;
        }
    }

    @Override
    public List<Students> getStudents() {
        Session session = SessionUtil.getSession();
        List<Students> students = new ArrayList<>();
        try {
            for (final Object student : session.createQuery("from Students").list()) {
                students.add((Students) student);
            }
        } catch (HibernateException exception) {
            System.out.print(exception.getLocalizedMessage());
        }
        return students;
    }

    @Override
    public boolean deleteStudent(Students roll) {
        try (Session session = SessionUtil.getSession()) {
            Transaction transaction = session.beginTransaction();
            //int convert_id = Integer.parseInt(roll.getRoll());
            int convert_id = roll.getStudent_id();
            System.out.println(convert_id);
            System.out.println("Hello ji");

            Query query = session.createQuery("delete from Students where student_id=:S_id");
            query.setParameter("S_id", convert_id);
            int result = query.executeUpdate();

            transaction.commit();
            return true;
        } catch (HibernateException exception) {
            System.out.print(exception.getLocalizedMessage());
            return false;
        }
    }

    @Override
    public int updateStudent(Students student) {
        Session session = SessionUtil.getSession();
        try {
            Transaction transaction = session.beginTransaction();
            Query query = session.createQuery("from Students where domain.domain_id =:domain_id and student_id!=:id");
            query.setParameter("domain_id", student.getDomain().getDomain_id());
            query.setParameter("id", student.getStudent_id());
            if(query.getResultList().size()==student.getDomain().getCapacity()) {
                return 2;
            }
            student.setRoll(student.getEmail());
            Query query1 = session.createQuery("Update Students set first_name =: firstName, last_name =: lastName, email =: email, domain.domain_id =: domainId, specialization =:specialization, grad_year =:year where student_id =:sId");
            query1.setParameter("firstName", student.getFirst_name());
            query1.setParameter("lastName", student.getLast_name());
            query1.setParameter("email", student.getEmail());
            query1.setParameter("domainId", student.getDomain().getDomain_id());
            query1.setParameter("specialization", student.getSpecialization());
            query1.setParameter("year", student.getGrad_year());
            query1.setParameter("sId", student.getStudent_id());
            System.out.println(student.getStudent_id()+" dao");

            query1.executeUpdate();
            transaction.commit();
            return 1;
        } catch (HibernateException exception) {
            System.out.print(exception.getLocalizedMessage());
            return 3;
        }finally {
            session.close();
        }
    }
}
