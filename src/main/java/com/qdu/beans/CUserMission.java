package com.qdu.beans;

import java.io.Serializable;

public class CUserMission implements Serializable {
    private Integer id;

    private Integer uid;

    private Integer mid;

    private String mname;


    public CUserMission(Integer id, Integer uid, Integer mid, String mname) {
        this.id = id;
        this.uid = uid;
        this.mid = mid;
        this.mname = mname;
    }

    public String getMname() {
        return mname;
    }

    public void setMname(String mname) {
        this.mname = mname;
    }

    public CUserMission() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getMid() {
        return mid;
    }

    public void setMid(Integer mid) {
        this.mid = mid;
    }
}