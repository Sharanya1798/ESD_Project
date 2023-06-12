package com.example.erp.bean;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Domains")
public class Domains {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer domain_id;

    private String qualification;

    @Column(nullable = false)
    private String program;
    @Column(nullable = false)
    private String batch;
    @Column(nullable = false)
    private int capacity;

    @OneToMany(mappedBy = "domain")
    private List<Students> students;

    public Domains() {
    }

    public Domains(String program, String batch, int capacity, String qualification) {
        this.program = program;
        this.batch = batch;
        this.capacity = capacity;
        this.qualification = qualification;

    }

    public Domains(Integer domain_id) {
        this.domain_id = domain_id;
    }

    public String getProgram()
    {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public Integer getDomain_id() {
        return domain_id;
    }
}
