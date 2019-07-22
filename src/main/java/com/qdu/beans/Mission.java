package com.qdu.beans;


public class Mission {
/*
    mid    NUMBER(10) PRIMARY KEY,
    mname VARCHAR2(255),
    mdetails VARCHAR2(255), 任务描述
    qzhj VARCHAR2(255), 严重 一般
    mstatus VNUMBER(10) 0未领取 1工作中 2等待完成检验 3 关闭
    stime VARCHAR2(255),
    etime VARCHAR2(255)*/


    private Integer mid;
    private String mname;
    private String mdetails;
    private String qzhj;
    private String mstatus;
    private Integer repairmanid;
    private String stime;
    private String etime;
    private String UserName;

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public Mission() {
    }

    public Mission(Integer mid, String mname, String mdetails, String qzhj, String mstatus, Integer repairmanid, String stime, String etime, String userName) {
        this.mid = mid;
        this.mname = mname;
        this.mdetails = mdetails;
        this.qzhj = qzhj;
        this.mstatus = mstatus;
        this.repairmanid = repairmanid;
        this.stime = stime;
        this.etime = etime;
        UserName = userName;
    }

    public String getStime() {
        return stime;
    }

    public void setStime(String stime) {
        this.stime = stime;
    }

    public String getEtime() {
        return etime;
    }

    public void setEtime(String etime) {
        this.etime = etime;
    }

    public Integer getMid() {
        return mid;
    }

    public void setMid(Integer mid) {
        this.mid = mid;
    }

    public String getMname() {
        return mname;
    }

    public void setMname(String mname) {
        this.mname = mname;
    }

    public String getMdetails() {
        return mdetails;
    }

    public void setMdetails(String mdetails) {
        this.mdetails = mdetails;
    }

    public String getQzhj() {
        return qzhj;
    }

    public void setQzhj(String qzhj) {
        this.qzhj = qzhj;
    }

    public String getMstatus() {
        return mstatus;
    }

    public void setMstatus(String mstatus) {
        this.mstatus = mstatus;
    }

    public Integer getRepairmanid() {
        return repairmanid;
    }

    public void setRepairmanid(Integer repairmanid) {
        this.repairmanid = repairmanid;
    }
}
