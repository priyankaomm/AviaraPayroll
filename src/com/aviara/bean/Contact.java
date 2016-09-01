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
public class Contact {
    private int id;
    private String emp_id;
    private String contact_no;
    private String alt_contact;
    private String email_id;
    private String guardian_name;
    private String g_contact;
    private String mother_name;
    private String m_contact;
    private String emergency1_name;
    private String emergency2_name;
    private String emergency1;
    private String emergency2;

    public void setId(int id) {
        this.id = id;
    }

    public void setEmp_id(String emp_id) {
        this.emp_id = emp_id;
    }

    public void setContact_no(String contact_no) {
        this.contact_no = contact_no;
    }

    public void setAlt_contact(String alt_contact) {
        this.alt_contact = alt_contact;
    }

    public void setEmail_id(String email_id) {
        this.email_id = email_id;
    }

    public void setGuardian_name(String guardian_name) {
        this.guardian_name = guardian_name;
    }

    public void setG_contact(String g_contact) {
        this.g_contact = g_contact;
    }

    public void setMother_name(String mother_name) {
        this.mother_name = mother_name;
    }

    public void setM_contact(String m_contact) {
        this.m_contact = m_contact;
    }

    public void setEmergency1_name(String emergency1_name) {
        this.emergency1_name = emergency1_name;
    }

    public void setEmergency2_name(String emergency2_name) {
        this.emergency2_name = emergency2_name;
    }

    public void setEmergency1(String emergency1) {
        this.emergency1 = emergency1;
    }

    public void setEmergency2(String emergency2) {
        this.emergency2 = emergency2;
    }

    
    public int getId() {
        return id;
    }

    public String getEmp_id() {
        return emp_id;
    }

    public String getContact_no() {
        return contact_no;
    }

    public String getAlt_contact() {
        return alt_contact;
    }

    public String getEmail_id() {
        return email_id;
    }

    public String getGuardian_name() {
        return guardian_name;
    }

    public String getG_contact() {
        return g_contact;
    }

    public String getMother_name() {
        return mother_name;
    }

    public String getM_contact() {
        return m_contact;
    }

    public String getEmergency1_name() {
        return emergency1_name;
    }

    public String getEmergency2_name() {
        return emergency2_name;
    }

    public String getEmergency1() {
        return emergency1;
    }

    public String getEmergency2() {
        return emergency2;
    }

    
}
