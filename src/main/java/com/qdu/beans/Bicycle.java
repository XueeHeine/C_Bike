package com.qdu.beans;

import java.io.Serializable;

/**
 * Created by admin on 2019/4/14.
 */
public class Bicycle implements Serializable {
    private Integer id;
    private String gno;
    private String model;
    private Sup sup;
    private Integer category;
    private Integer nnumber;
    private Integer anumber;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGno() {
        return gno;
    }

    public void setGno(String gno) {
        this.gno = gno;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Sup getSup() {
        return sup;
    }

    public void setSup(Sup sup) {
        this.sup = sup;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public Integer getNnumber() {
        return nnumber;
    }

    public void setNnumber(Integer nnumber) {
        this.nnumber = nnumber;
    }

    public Integer getAnumber() {
        return anumber;
    }

    public void setAnumber(Integer anumber) {
        this.anumber = anumber;
    }

    @Override
    public String toString() {
        return "Bicycle{" +
                "id=" + id +
                ", gno='" + gno + '\'' +
                ", model='" + model + '\'' +
                ", sup=" + sup +
                ", category=" + category +
                ", nnumber=" + nnumber +
                ", anumber=" + anumber +
                '}';
    }
}
