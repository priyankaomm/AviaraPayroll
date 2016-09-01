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
public class EmpLeave {
    private int id;
    private String emp_id;
    private int leave_id;
    private Date applicableFrom;
    private int tLeaveAssigned;
    private int leavesTaken;
    private int remLeaves;

    public int gettLeaveAssigned() {
        return tLeaveAssigned;
    }

    public int getLeavesTaken() {
        return leavesTaken;
    }

    public int getRemLeaves() {
        return remLeaves;
    }

    public void settLeaveAssigned(int tLeaveAssigned) {
        this.tLeaveAssigned = tLeaveAssigned;
    }

    public void setLeavesTaken(int leavesTaken) {
        this.leavesTaken = leavesTaken;
    }

    public void setRemLeaves(int remLeaves) {
        this.remLeaves = remLeaves;
    }
    
    public int getId() {
        return id;
    }

    public String getEmp_id() {
        return emp_id;
    }

    public int getLeave_id() {
        return leave_id;
    }

    public Date getApplicableFrom() {
        return applicableFrom;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEmp_id(String emp_id) {
        this.emp_id = emp_id;
    }

    public void setLeave_id(int leave_id) {
        this.leave_id = leave_id;
    }

    public void setApplicableFrom(Date applicableFrom) {
        this.applicableFrom = applicableFrom;
    }
    
    
}
