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
public class Deduction {
    private int id;
    private String emp_id;
    private int deduction_id;

    public int getId() {
        return id;
    }

    public String getEmp_id() {
        return emp_id;
    }

    public int getDeduction_id() {
        return deduction_id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEmp_id(String emp_id) {
        this.emp_id = emp_id;
    }

    public void setDeduction_id(int deduction_id) {
        this.deduction_id = deduction_id;
    }
    
    
}
