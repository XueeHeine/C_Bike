package com.qdu.beans;

import java.io.Serializable;

/**
 * Created by admin on 2019/4/14.
 */
public class Sup implements Serializable {
    private Integer id;
    private String sname;
    private String address;
    private Integer priority;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    @Override
    public String toString() {
        return "Sup{" +
                "id=" + id +
                ", sname='" + sname + '\'' +
                ", address='" + address + '\'' +
                ", priority=" + priority +
                '}';
    }
}
