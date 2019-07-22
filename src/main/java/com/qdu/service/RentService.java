package com.qdu.service;



import com.qdu.beans.Rentt;

import java.util.Date;
import java.util.Map;



public interface RentService {

	String getMaxId();

	void doAdd(Rentt rent);

	Map<String, Object> query(Integer page, Integer rows);

	void jia(Integer idd, Integer money);

	int count(Rentt rent);

	Map<String, Object> number(Date ftime, Date ltime);

    Map<String,Object> profit(Integer id);

    Map<String,Object> queryy(Integer id, String time);

	Map<String,Object> compare(Integer id1, Integer id2);

	Map<String,Object> queryyday(Integer id, String time);
}
