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
public class Holiday {
    private int id;
    private Date date;
    private String event;
    private int compony_id;
    private int branch_id;

    public void setId(int id) {
        this.id = id;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public void setCompony_id(int compony_id) {
        this.compony_id = compony_id;
    }

    public void setBranch_id(int branch_id) {
        this.branch_id = branch_id;
    }

    
    public int getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public String getEvent() {
        return event;
    }

    public int getCompony_id() {
        return compony_id;
    }

    public int getBranch_id() {
        return branch_id;
    }
    
    
}
