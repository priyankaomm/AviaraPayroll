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
public class Bank {
    private int id;
    private String bank_name;
    private String acc_no;
    private String ifsc_code;
    private String pf_no;
    private String pan_no;
    private String voter_id;
    private String adhar_no;
    private String emp_id;

    public void setId(int id) {
        this.id = id;
    }

    public void setBank_name(String bank_name) {
        this.bank_name = bank_name;
    }

    public void setAcc_no(String acc_no) {
        this.acc_no = acc_no;
    }

    public void setIfsc_code(String ifsc_code) {
        this.ifsc_code = ifsc_code;
    }

    public void setPf_no(String pf_no) {
        this.pf_no = pf_no;
    }

    public void setPan_no(String pan_no) {
        this.pan_no = pan_no;
    }

    public void setVoter_id(String voter_id) {
        this.voter_id = voter_id;
    }

    public void setAdhar_no(String adhar_no) {
        this.adhar_no = adhar_no;
    }

    public void setEmp_id(String emp_id) {
        this.emp_id = emp_id;
    }

    
    public int getId() {
        return id;
    }

    public String getBank_name() {
        return bank_name;
    }

    public String getAcc_no() {
        return acc_no;
    }

    public String getIfsc_code() {
        return ifsc_code;
    }

    public String getPf_no() {
        return pf_no;
    }

    public String getPan_no() {
        return pan_no;
    }

    public String getVoter_id() {
        return voter_id;
    }

    public String getAdhar_no() {
        return adhar_no;
    }

    public String getEmp_id() {
        return emp_id;
    }
    
    
}
