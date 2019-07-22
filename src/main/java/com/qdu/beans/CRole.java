package com.qdu.beans;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class CRole implements Serializable {
    private Long id;

    private String rolename;

    private Set<CModule> modules = new HashSet<>();

    public Set<CModule> getModules() {
        return modules;
    }

    public void setModules(Set<CModule> modules) {
        this.modules = modules;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename == null ? null : rolename.trim();
    }

    @Override
    public String toString() {
        return "CRole{" +
                "id=" + id +
                ", rolename='" + rolename + '\'' +
                ", modules=" + modules +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if(null == obj) return false;
        if(!(obj instanceof CRole)) return false;
        if (this == obj) return true;
        return this.id.compareTo (((CRole)obj).id) == 0 && this.rolename.equals(((CRole)obj).rolename);
    }
}