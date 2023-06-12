package com.example.erp.bean;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Students")
public class Students {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer student_id;

    private String first_name;
    private String last_name;
    private String specialization;
    private String grad_year;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false, unique = true)
    private String roll;
    @Column(nullable = false)
    private String photograph_path;


    @ManyToOne
    @JoinColumn(name="domain_id", nullable=false)
    private Domains domain;

   /* @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "Student_Courses", joinColumns = {@JoinColumn(name = "student_id")},
            inverseJoinColumns = {@JoinColumn(name = "course_id")})
    private List<Courses> courses;*/

    public Students() {
    }

    public Students(String first_name, String last_name, String specialization, String grad_year, String email, String roll) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.specialization = specialization;
        this.grad_year = grad_year;
        this.email = email;
        this.roll = roll;
    }

    public Students(String first_name, String last_name, String specialization, String grad_year, String email, String roll, Domains domain, String photograph_path) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.specialization = specialization;
        this.grad_year = grad_year;
        this.email = email;
        this.roll = roll;
        this.domain = domain;
        this.photograph_path = photograph_path;
    }

    public String getPhotograph_path() {
        return photograph_path;
    }

    public void setPhotograph_path(String photograph_path) {
        this.photograph_path = photograph_path;
    }

    public Integer getStudent_id() {
        return student_id;
    }

    public void setStudent_id(Integer student_id) {
        this.student_id = student_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getGrad_year() {
        return grad_year;
    }

    public void setGrad_year(String grad_year) {
        this.grad_year = grad_year;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRoll() {
        return roll;
    }

    public void setRoll(String roll) {
        this.roll = roll;
    }

    public Domains getDomain() {
        return domain;
    }

    public void setDomain(Domains domain) {
        this.domain = domain;
    }
}
