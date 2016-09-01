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
public class EmpAllowance {
    private int id;
    private String emp_id;
    private int allowance_id;

    public int getId() {
        return id;
    }

    public String getEmp_id() {
        return emp_id;
    }

    public int getAllowance_id() {
        return allowance_id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEmp_id(String emp_id) {
        this.emp_id = emp_id;
    }

    public void setAllowance_id(int allowance_id) {
        this.allowance_id = allowance_id;
    }
    
    
}
