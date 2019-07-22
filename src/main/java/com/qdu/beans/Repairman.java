package com.qdu.beans;


public class Repairman {

/*    rid       NUMBER(10) PRIMARY KEY,
    rname     VARCHAR2(50),
    rlevel     VARCHAR2(50),
    rstatus    NUMBER(10),
    birthday  VARCHAR2(50),
    starttime VARCHAR2(50),
    endtime   VARCHAR2(50)*/

    private Integer rid;
    private String rname;
    private String rlevel;
    private Integer rstatus;
    private String birthday;
    private String starttime;
    private String endtime;


    public Repairman() {
    }

    public Repairman(Integer rid, String rname, String rlevel, Integer rstatus, String birthday, String starttime, String endtime) {
        this.rid = rid;
        this.rname = rname;
        this.rlevel = rlevel;
        this.rstatus = rstatus;
        this.birthday = birthday;
        this.starttime = starttime;
        this.endtime = endtime;

    }

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    public String getRname() {
        return rname;
    }

    public void setRname(String rname) {
        this.rname = rname;
    }

    public String getRlevel() {
        return rlevel;
    }

    public void setRlevel(String rlevel) {
        this.rlevel = rlevel;
    }

    public Integer getRstatus() {
        return rstatus;
    }

    public void setRstatus(Integer rstatus) {
        this.rstatus = rstatus;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }


}
