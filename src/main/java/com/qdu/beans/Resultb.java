package com.qdu.beans;

import java.util.Date;

/**
 * Created by Alpha-LGC on 2018/11/16.
 */
public class Resultb {
    private Integer count1;
    private String eday;
    private String btype;
    private static String[] sd = new String[]{"xxx","babas"};
    public Integer getCount1() {
        return count1;
    }

    public void setCount1(Integer count1) {
        this.count1 = count1;
    }

    public String getEday() {
        return eday;
    }

    public void setEday(String eday) {
        this.eday = eday;
    }

    public String getBtype() {
        return btype;
    }

    public void setBtype(String btype) {
        this.btype = btype;
    }

    public static String[] getSd() {
        return sd;
    }

    public static void setSd(String[] sd) {
        Resultb.sd = sd;
    }

    @Override
    public String toString() {
        return "Resultb{" +
                "count1=" + count1 +
                ", eday='" + eday + '\'' +
                ", btype='" + btype + '\'' +
                '}';
    }
}
