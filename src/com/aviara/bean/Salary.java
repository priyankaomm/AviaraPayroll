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
public class Salary {
    private int id;
    private String emp_id;
    private String name;
    private Date salaryDate;
    private Double basic_sal;
    private Double allowance;
    private Double increment;
    private Double bonus;
    private Double reimbursment;
    private int workingDays;
    private int totalLeaves;
    private int paidLeaves;
    private int unpaidLeaves;
    private int paybleDays;
    private Double totalSalary;
    private Double totalDeductions;
    private Double grandTotal;
    private Double paidSalary;
    private Double other;
    private int month;
    private int year;
    private String monthInWords;

    public String getMonthInWords() {
        return monthInWords;
    }

    public void setMonthInWords(String monthInWords) {
        this.monthInWords = monthInWords;
    }

    
    public Double getOther() {
        return other;
    }

    public void setOther(Double other) {
        this.other = other;
    }
    

    
    public int getId() {
        return id;
    }

    public String getEmp_id() {
        return emp_id;
    }

    public String getName() {
        return name;
    }

    public Date getSalaryDate() {
        return salaryDate;
    }

    public Double getBasic_sal() {
        return basic_sal;
    }

    public Double getAllowance() {
        return allowance;
    }

    public Double getIncrement() {
        return increment;
    }

    public Double getBonus() {
        return bonus;
    }

    public Double getReimbursment() {
        return reimbursment;
    }

    public int getWorkingDays() {
        return workingDays;
    }

    public int getTotalLeaves() {
        return totalLeaves;
    }

    public int getPaidLeaves() {
        return paidLeaves;
    }

    public int getUnpaidLeaves() {
        return unpaidLeaves;
    }

    public int getPaybleDays() {
        return paybleDays;
    }

    public Double getTotalSalary() {
        return totalSalary;
    }

    public Double getTotalDeductions() {
        return totalDeductions;
    }

    public Double getGrandTotal() {
        return grandTotal;
    }

    public Double getPaidSalary() {
        return paidSalary;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEmp_id(String emp_id) {
        this.emp_id = emp_id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSalaryDate(Date salaryDate) {
        this.salaryDate = salaryDate;
    }

    public void setBasic_sal(Double basic_sal) {
        this.basic_sal = basic_sal;
    }

    public void setAllowance(Double allowance) {
        this.allowance = allowance;
    }

    public void setIncrement(Double increment) {
        this.increment = increment;
    }

    public void setBonus(Double bonus) {
        this.bonus = bonus;
    }

    public void setReimbursment(Double reimbursment) {
        this.reimbursment = reimbursment;
    }

    public void setWorkingDays(int workingDays) {
        this.workingDays = workingDays;
    }

    public void setTotalLeaves(int totalLeaves) {
        this.totalLeaves = totalLeaves;
    }

    public void setPaidLeaves(int paidLeaves) {
        this.paidLeaves = paidLeaves;
    }

    public void setUnpaidLeaves(int unpaidLeaves) {
        this.unpaidLeaves = unpaidLeaves;
    }

    public void setPaybleDays(int paybleDays) {
        this.paybleDays = paybleDays;
    }

    public void setTotalSalary(Double totalSalary) {
        this.totalSalary = totalSalary;
    }

    public void setTotalDeductions(Double totalDeductions) {
        this.totalDeductions = totalDeductions;
    }

    public void setGrandTotal(Double grandTotal) {
        this.grandTotal = grandTotal;
    }

    public void setPaidSalary(Double paidSalary) {
        this.paidSalary = paidSalary;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public void setYear(int year) {
        this.year = year;
    }
    
}
