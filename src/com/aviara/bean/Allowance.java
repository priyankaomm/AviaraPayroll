/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aviara.bean;

import javafx.scene.control.TextField;

/**
 *
 * @author comp11
 */
public class Allowance {
    
    private int allowance_id; 
    private String allow_name;
    private String allow_type;
    private double value;

    public int getAllowance_id() {
        return allowance_id;
    }

    public String getAllow_name() {
        return allow_name;
    }

    public String getAllow_type() {
        return allow_type;
    }

    public double getValue() {
        return value;
    }

    public void setAllowance_id(int allowance_id) {
        this.allowance_id = allowance_id;
    }

    public void setAllow_name(String allow_name) {
        this.allow_name = allow_name;
    }

    public void setAllow_type(String allow_type) {
        this.allow_type = allow_type;
    }

    public void setValue(double value) {
        this.value = value;
    }

    
}
