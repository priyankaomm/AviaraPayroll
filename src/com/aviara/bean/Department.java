/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aviara.bean;

/**
 *
 * @author comp11
 */
public class Department {
    private String dept_name;
    private String ot_status;
    private double rate;
    private int dept_id;

    public void setDept_id(int dept_id) {
        this.dept_id = dept_id;
    }

    public int getDept_id() {
        return dept_id;
    }

    public void setDept_name(String dept_name) {
        this.dept_name = dept_name;
    }

    public void setOt_status(String ot_status) {
        this.ot_status = ot_status;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public String getDept_name() {
        return dept_name;
    }

    public String getOt_status() {
        return ot_status;
    }

    public double getRate() {
        return rate;
    }
}
