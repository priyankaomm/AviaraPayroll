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
public class PaymentDetails {
    private int id;
    private String emp_id;
    private String emp_type;
    private Double basicSal;
    private String duration;
    private Double hra;
    private Double other;
    private Double total;
    private Date nextPay;
    private Date incDate;

    public void setId(int id) {
        this.id = id;
    }

    public void setEmp_id(String emp_id) {
        this.emp_id = emp_id;
    }

    public void setEmp_type(String emp_type) {
        this.emp_type = emp_type;
    }

    public void setBasicSal(Double basicSal) {
        this.basicSal = basicSal;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public void setHra(Double hra) {
        this.hra = hra;
    }

    public void setOther(Double other) {
        this.other = other;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public void setNextPay(Date nextPay) {
        this.nextPay = nextPay;
    }

    public void setIncDate(Date incDate) {
        this.incDate = incDate;
    }

    
    public int getId() {
        return id;
    }

    public String getEmp_id() {
        return emp_id;
    }

    public String getEmp_type() {
        return emp_type;
    }

    public Double getBasicSal() {
        return basicSal;
    }

    public String getDuration() {
        return duration;
    }

    public Double getHra() {
        return hra;
    }

    public Double getOther() {
        return other;
    }

    public Double getTotal() {
        return total;
    }

    public Date getNextPay() {
        return nextPay;
    }

    public Date getIncDate() {
        return incDate;
    }
    
}
