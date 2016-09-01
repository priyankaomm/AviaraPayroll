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
public class Leave1 {
    
    private int leave_id;
    private String leave_name;
    private int no_of_leave;
    private int monthly;
    private int carryForward;

    public int getMonthly() {
        return monthly;
    }

    public int getCarryForward() {
        return carryForward;
    }

    public void setMonthly(int monthly) {
        this.monthly = monthly;
    }

    public void setCarryForward(int carryForward) {
        this.carryForward = carryForward;
    }

    
    public void setLeave_id(int leave_id) {
        this.leave_id = leave_id;
    }

    public void setLeave_name(String leave_name) {
        this.leave_name = leave_name;
    }

    public void setNo_of_leave(int no_of_leave) {
        this.no_of_leave = no_of_leave;
    }

    public int getLeave_id() {
        return leave_id;
    }

    public String getLeave_name() {
        return leave_name;
    }

    public int getNo_of_leave() {
        return no_of_leave;
    }
    
    
    
}
