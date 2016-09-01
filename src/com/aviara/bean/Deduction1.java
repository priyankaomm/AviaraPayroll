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
public class Deduction1 {
    
    
   private String deductio_name;
   private double deduction_percent;
   private int deduction_id;

    public void setDeductio_name(String deductio_name) {
        this.deductio_name = deductio_name;
    }

    public void setDeduction_percent(double deduction_percent) {
        this.deduction_percent = deduction_percent;
    }

    public void setDeduction_id(int deduction_id) {
        this.deduction_id = deduction_id;
    }

    public String getDeductio_name() {
        return deductio_name;
    }

    public double getDeduction_percent() {
        return deduction_percent;
    }

    public int getDeduction_id() {
        return deduction_id;
    }

    
    
    
}
