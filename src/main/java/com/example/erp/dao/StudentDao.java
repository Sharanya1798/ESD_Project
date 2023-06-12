package com.example.erp.dao;

import com.example.erp.bean.Students;

import java.util.List;

public interface StudentDao {

    List<Students> emailVerify(Students student);
    int registerStudent(Students student);
    int updateStudent(Students student);
    boolean photoPathInsertion(Integer id, String path);

    List<Students> getStudents();

    boolean deleteStudent(Students roll);
}
