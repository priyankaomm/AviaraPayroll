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
public class MonthlyLeaves {
    private int id;
    private String emp_id;
    private int month;
    private int isTaken;
    private int total;
    private int leave_id;
    private int year;

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    
    public int getId() {
        return id;
    }

    public String getEmp_id() {
        return emp_id;
    }

    public int getMonth() {
        return month;
    }

    public int getIsTaken() {
        return isTaken;
    }

    public int getTotal() {
        return total;
    }

    public int getLeave_id() {
        return leave_id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEmp_id(String emp_id) {
        this.emp_id = emp_id;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public void setIsTaken(int isTaken) {
        this.isTaken = isTaken;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public void setLeave_id(int leave_id) {
        this.leave_id = leave_id;
    }
    
    
}
