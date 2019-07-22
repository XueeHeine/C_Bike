package com.qdu.beans;

import java.io.Serializable;

public class CModule implements Serializable {
    private Long id;

    private String modulename;

    private String url;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModulename() {
        return modulename;
    }

    public void setModulename(String modulename) {
        this.modulename = modulename == null ? null : modulename.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    @Override
    public boolean equals(Object obj) {
        if(null == obj) return false;
        if(!(obj instanceof CModule)) return false;
        if (this == obj) return true;
        return this.modulename.equals(((CModule)obj).modulename);
    }
}