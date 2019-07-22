package com.qdu.beans;

import java.util.UUID;

/**
 * Created by Alpha-LGC on 2018/11/15.
 */
public class Bicy {
    private Integer id;
    private String bno;
    private String bxinghao;
    private Integer btype;
    private Integer bnumber;
    private Integer fid;
    private Integer status;
    private String fname;

    public Integer getId() {
        return id;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public Bicy() {

    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBno() {
        return bno;
    }

    public void setBno(String bno) {
        this.bno = bno;
    }

    public String getBxinghao() {
        return bxinghao;
    }

    public void setBxinghao(String bxinghao) {
        this.bxinghao = bxinghao;
    }

    public Integer getBtype() {
        return btype;
    }

    public void setBtype(Integer btype) {
        this.btype = btype;
    }

    public Integer getBnumber() {
        return bnumber;
    }

    public void setBnumber(Integer bnumber) {
        this.bnumber = bnumber;
    }

    public Integer getFid() {
        return fid;
    }

    public void setFid(Integer fid) {
        this.fid = fid;
    }

    /*public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }*/
}
