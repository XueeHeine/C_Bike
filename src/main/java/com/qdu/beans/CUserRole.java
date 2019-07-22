package com.qdu.beans;

import java.io.Serializable;

public class CUserRole implements Serializable {
    private Long id;

    private Long userid;

    private Long roleid;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public Long getRoleid() {
        return roleid;
    }

    public void setRoleid(Long roleid) {
        this.roleid = roleid;
    }

    public CUserRole(Long id, Long userid, Long roleid) {
        this.id = id;
        this.userid = userid;
        this.roleid = roleid;
    }

    public CUserRole() {
    }
}