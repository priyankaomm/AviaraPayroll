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
public class Leave {
    private int id;
    private String emp_id;
    private String type;
    private int status;
    private Date date;

    public void setId(int id) {
        this.id = id;
    }

    
    public int getId() {
        return id;
    }

    
    public void setEmp_id(String emp_id) {
        this.emp_id = emp_id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    
    public String getEmp_id() {
        return emp_id;
    }

    public String getType() {
        return type;
    }

    public int getStatus() {
        return status;
    }

    public Date getDate() {
        return date;
    }
    
    
}
