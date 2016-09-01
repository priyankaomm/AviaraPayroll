/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aviara.bean;

import java.sql.Date;

/**
 *
 * @author comp14
 */
public class Userregistration 
{
    private int id;
    private String username;
    private String password;
    private String conf_password;
    private String seq_question1;
    private String seq_ans1;
    private String seq_question2;
    private String seq_ans2;
    private int compony_id;

    public int getCompony_id() {
        return compony_id;
    }

    public void setCompony_id(int compony_id) {
        this.compony_id = compony_id;
    }

    
    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getConf_password() {
        return conf_password;
    }

    public String getSeq_question1() {
        return seq_question1;
    }

    public String getSeq_ans1() {
        return seq_ans1;
    }

    public String getSeq_question2() {
        return seq_question2;
    }

    public String getSeq_ans2() {
        return seq_ans2;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setConf_password(String conf_password) {
        this.conf_password = conf_password;
    }

    public void setSeq_question1(String seq_question1) {
        this.seq_question1 = seq_question1;
    }

    public void setSeq_ans1(String seq_ans1) {
        this.seq_ans1 = seq_ans1;
    }

    public void setSeq_question2(String seq_question2) {
        this.seq_question2 = seq_question2;
    }

    public void setSeq_ans2(String seq_ans2) {
        this.seq_ans2 = seq_ans2;
    }

    

    
    
}
