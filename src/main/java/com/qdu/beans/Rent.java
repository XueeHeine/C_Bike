package com.qdu.beans;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Alpha-LGC on 2018/11/16.
 */
public class Rent {
    static  int count;
    static ReentrantLock lock = new ReentrantLock();
    private Integer id;
    private String rno;
    private String bno;
    private String person;
    private String phone;
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date starttime;
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date backtime;
    private Integer status = 0;

    public Rent(){

    }
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRno() {
        return rno;
    }

    public void setRno(String rno) {
        this.rno = rno;
    }

    public String getBno() {
        return bno;
    }

    public void setBno(String bno) {
        this.bno = bno;
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getStarttime() {
        return starttime;
    }

    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }

    public Date getBacktime() {
        return backtime;
    }

    public void setBacktime(Date backtime) {
        this.backtime = backtime;
    }
    public static String generateEmpno(){
        lock.lock();
        String str = "BICYCLE";
        String time =new SimpleDateFormat("yyyy").format(new Date());
        str += time.replace("-","");
        String number = count+"";
        if(number.length()<4){
            for(int i =0;i<= 4-number.length();i++){
                number="0"+number;
            }
        }
        str+=number;
        count++;
        System.out.println("静态值加一了");
        lock.unlock();
        return str;
    }
}
