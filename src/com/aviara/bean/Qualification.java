/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aviara.bean;

/**
 *
 * @author comp2
 */
public class Qualification {
    private int id;
    private String emp_id;
    private String degree;
    private String university;
    private String collage_name;
    private Double marks;

    public int getId() {
        return id;
    }

    public String getEmp_id() {
        return emp_id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEmp_id(String emp_id) {
        this.emp_id = emp_id;
    }

    
    public void setDegree(String degree) {
        this.degree = degree;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public void setCollage_name(String collage_name) {
        this.collage_name = collage_name;
    }

    public void setMarks(Double marks) {
        this.marks = marks;
    }

    
    public String getDegree() {
        return degree;
    }

    public String getUniversity() {
        return university;
    }

    public String getCollage_name() {
        return collage_name;
    }

    public Double getMarks() {
        return marks;
    }
    
    
}
