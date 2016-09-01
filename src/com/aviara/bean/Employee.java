/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aviara.bean;

import java.sql.Date;

/**
 *
 * @author comp2
 */
public class Employee {
    private String emp_id;
    private String device_id;
    private String firstName;
    private String midName;
    private String lastName;
    private Date dob;
    private Date doj;
    private Date dor;
    private Date lastPayment;
    private String gender;
    private String designation;
   // private String qualification;
   // private String exp;
    private String branch;
    private String department;

    public String getDevice_id() {
        return device_id;
    }

    public void setDevice_id(String device_id) {
        this.device_id = device_id;
    }

    
    public void setEmp_id(String emp_id) {
        this.emp_id = emp_id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setMidName(String midName) {
        this.midName = midName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public void setDoj(Date doj) {
        this.doj = doj;
    }

    public void setDor(Date dor) {
        this.dor = dor;
    }

    public void setLastPayment(Date lastPayment) {
        this.lastPayment = lastPayment;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    
    public String getEmp_id() {
        return emp_id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMidName() {
        return midName;
    }

    public String getLastName() {
        return lastName;
    }

    public Date getDob() {
        return dob;
    }

    public Date getDoj() {
        return doj;
    }

    public Date getDor() {
        return dor;
    }

    public Date getLastPayment() {
        return lastPayment;
    }

    public String getGender() {
        return gender;
    }

    public String getDesignation() {
        return designation;
    }

    public String getBranch() {
        return branch;
    }

    public String getDepartment() {
        return department;
    }

    
}
