package com.qdu.test;



import java.text.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

/**
 * Created by Alpha-LGC on 2019/1/30.
 */
public class TestSql
{
    public static void main(String[] args){
        Date date = new Date();
        date.setTime(1551926575058L);

        System.out.println(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date(1551926575058L)));

        ArrayList<String> xl = new ArrayList();
        Collections.addAll(xl,"a","b","c");
        ArrayList<String> xs = new ArrayList<>();
        Collections.addAll(xs,"b");
        xl.removeAll(xs);
        System.out.println(xl);
        Integer m = 6;
        System.out.println(Long.parseLong(m+""));


    }
}
