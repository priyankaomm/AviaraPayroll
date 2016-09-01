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
public class Dbinfo {
    private int id;
    private String path;
    private String logname;
    private int status;

    public void setId(int id) {
        this.id = id;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setLogname(String logname) {
        this.logname = logname;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    
    public int getId() {
        return id;
    }

    public String getPath() {
        return path;
    }

    public String getLogname() {
        return logname;
    }

    public int getStatus() {
        return status;
    }
    
    
}
