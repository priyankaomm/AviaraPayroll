/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aviara.bean;

import java.sql.Date;
import java.sql.Time;

/**
 *
 * @author comp2
 */
public class AttendanceLogs {
    private int id;
    private String emp_id;
    private String device_id;
    private Date attendanceDate;
    private String inTime;
    private String outTime;
    private int present;
    private int absent;
    private String status;

    public void setId(int id) {
        this.id = id;
    }

    public void setEmp_id(String emp_id) {
        this.emp_id = emp_id;
    }

    public void setDevice_id(String device_id) {
        this.device_id = device_id;
    }

    public void setAttendanceDate(Date attendanceDate) {
        this.attendanceDate = attendanceDate;
    }

    
    public void setPresent(int present) {
        this.present = present;
    }

    public void setAbsent(int absent) {
        this.absent = absent;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    
    public int getId() {
        return id;
    }

    public String getEmp_id() {
        return emp_id;
    }

    public String getDevice_id() {
        return device_id;
    }

    public Date getAttendanceDate() {
        return attendanceDate;
    }

   
    public int getPresent() {
        return present;
    }

    public int getAbsent() {
        return absent;
    }

    public String getStatus() {
        return status;
    }

    public String getInTime() {
        return inTime;
    }

    public String getOutTime() {
        return outTime;
    }

    public void setInTime(String inTime) {
        this.inTime = inTime;
    }

    public void setOutTime(String outTime) {
        this.outTime = outTime;
    }
    
    
}
