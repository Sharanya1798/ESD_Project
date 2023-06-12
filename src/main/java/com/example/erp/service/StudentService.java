package com.example.erp.service;

import com.example.erp.bean.Students;
import com.example.erp.dao.StudentDao;
import com.example.erp.dao.implementation.StudentDaoImpl;
import com.example.erp.utils.SessionUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.StreamingOutput;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class StudentService {
    StudentDao studentDao = new StudentDaoImpl();
    public List<Students> verifyEmail(Students student){
        return studentDao.emailVerify(student);
    }
    public boolean insertPhotoPath(Integer id, String path) {
        return studentDao.photoPathInsertion(id, path);
    }
    public StreamingOutput fetchPhotoFromPath(String upload_path)
    {
        StreamingOutput fileStream = new StreamingOutput() {
            @Override
            public void write(OutputStream outputStream) throws IOException, WebApplicationException {
                java.nio.file.Path path = Paths.get(upload_path);
                byte[] data = Files.readAllBytes(path);
                outputStream.write(data);
                outputStream.flush();
            }
        };
        return fileStream;
    }
    public int updateStudent(Students student){
        System.out.println(student.getLast_name()+"service");

        int status = studentDao.updateStudent(student);
        if(status == 1)
        {
            Session session = SessionUtil.getSession();
            try {
                Transaction transaction = session.beginTransaction();

                if (student.getDomain().getProgram().equals("Integrated M.Tech"))
                {
                    student.setRoll("IMT2020"+student.getStudent_id());
                } else if (student.getDomain().getProgram().equals("Masters in Technology"))
                {
                    student.setRoll("MT2020"+student.getStudent_id());
                } else
                { student.setRoll("MS2020"+student.getStudent_id()); }
                System.out.println(student.getRoll());
                Query query1 = session.createQuery("Update Students set roll =:roll where student_id =:sId");
                query1.setParameter("roll", student.getRoll());
                query1.setParameter("sId", student.getStudent_id());
                query1.executeUpdate();
                transaction.commit();
            } catch (HibernateException exception) {
                System.out.print(exception.getLocalizedMessage());
                status = 3;
            }finally {
                session.close();
            }
        }
        return status;
    }

    public int registerStudent(Students student){
        int status = studentDao.registerStudent(student);
        if(status == 1)
        {
            Session session = SessionUtil.getSession();
            try {
                Transaction transaction = session.beginTransaction();
                System.out.println(student.getStudent_id());
                System.out.println(student.getDomain().getProgram());
                if (student.getDomain().getProgram().equals("Integrated M.Tech"))
                {
                    student.setRoll("IMT2020"+student.getStudent_id());
                } else if (student.getDomain().getProgram().equals("Masters in Technology"))
                {
                    student.setRoll("MT2020"+student.getStudent_id());
                } else
                { student.setRoll("MS2020"+student.getStudent_id()); }
                session.saveOrUpdate(student);
                transaction.commit();
            } catch (HibernateException exception) {
                System.out.print(exception.getLocalizedMessage());
                status = 3;
            }finally {
                session.close();
            }
        }
        return status;
    }

    public List<Students> getStudents() {return studentDao.getStudents(); }

    public boolean deleteStudent(Students roll) { return studentDao.deleteStudent(roll);
    }
}
