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
public class EmpLeaveTaken {
    private int id;
    private String emp_id;
    private Date fromDate;
    private Date toDate;
    private int totalDays;
    private int leave_id;
    private int status;
    private String approvedByLead;
    private String approvedBy;
    private String remarks;

    public int getId() {
        return id;
    }

    public String getEmp_id() {
        return emp_id;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public int getTotalDays() {
        return totalDays;
    }

    public int getLeave_id() {
        return leave_id;
    }

    public int getStatus() {
        return status;
    }

    public String getApprovedByLead() {
        return approvedByLead;
    }

    public String getApprovedBy() {
        return approvedBy;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEmp_id(String emp_id) {
        this.emp_id = emp_id;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public void setTotalDays(int totalDays) {
        this.totalDays = totalDays;
    }

    public void setLeave_id(int leave_id) {
        this.leave_id = leave_id;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setApprovedByLead(String approvedByLead) {
        this.approvedByLead = approvedByLead;
    }

    public void setApprovedBy(String approvedBy) {
        this.approvedBy = approvedBy;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
    
    
}
