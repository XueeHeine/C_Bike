package com.qdu.service.impl;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.qdu.beans.Bicycle;
import com.qdu.beans.Rentt;
import com.qdu.beans.Sudu;
import com.qdu.mapper.BicycleMapper;
import com.qdu.mapper.RentMapper;
import com.qdu.service.RentService;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class RentServiceImpl implements RentService {
	@Autowired
	private RentMapper rm;
	@Autowired
	private BicycleMapper bm;
	
	@Override
	public String getMaxId() {
		int id = rm.getMaxId();
		int idd = id+1;
		Date date = new Date();
		String year =  new  SimpleDateFormat("yyyy").format(date);

		DecimalFormat df = new DecimalFormat("0000");
		
		
		return "BICYCLE"+year+df.format(idd);
	}

	@Override
	public void doAdd(Rentt rent) {
		System.out.println("!!!!!!"+rent);
		Bicycle bc1 = bm.getBcById(Integer.parseInt(rent.getGno()));
		if(bc1.getAnumber()==0){
			throw new RuntimeException("该类型的单车剩余量不足");
		}
		bm.jian(bc1.getGno());
		rent.setGno(bc1.getGno());
		Date a = new Date();

		rent.setGtime(a);
		int result = rm.insert(rent);
		if(result <=0){
			throw new RuntimeException("添加单车租借订单"+rent.getRno()+"失败");
		}
		
	}

	@Override
	public Map<String, Object> query(Integer page, Integer rows) {
		PageHelper.startPage(page, rows);
		List<Rentt> list = rm.query();
		PageInfo<Rentt> pi = new PageInfo<Rentt>(list);
		
		Map<String,Object> result = new HashMap<>();
		result.put("rows", list);
		result.put("total", pi.getTotal());
		return result;
	}

	@Override
	public void jia(Integer idd,Integer money) {
		/*String supname = bicycle.getSupname();
		List<Bicycle> list = bm.getModelBySup(supname);
		if(list!=null&&list.size()>0){
			for(Bicycle bc:list){
				if(bicycle.getModel().equals(bc.getModel())){
					throw new RuntimeException("�ù�Ӧ���ṩ�ĸõ����ͺ��Ѵ��� �����ظ����");
				}
			}
		}*/
		Rentt rent = rm.getById(idd);
		if(rent.getGtime()!=null&&rent.getRtime()!=null){
			throw new RuntimeException("该书已归还");
		}
		
		bm.jia(rent.getGno());
		Date date = new Date();
		Rentt rentt = new Rentt();
		rentt.setId(idd);
		rentt.setRtime(date);
		rentt.setProfit(Double.parseDouble(money+""));
		int result = rm.update(rentt);
		
		
		if(result <=0){
			throw new RuntimeException("还车失败");
		}
		
	}

	@Override
	public int count(Rentt rent) {
		
		
		
		
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~"+rent.getGno()+"~~~~~"+rent.getGtime());
		return 0;
		
	}

	@Override
	public Map<String, Object> number(Date ftime,Date ltime) {
		List<Sudu> list = new ArrayList<>();
		List<Date> gtime = rm.gtime(ftime,ltime);
		for(Date d:gtime){
			Sudu a = new Sudu();
			int z1 = rm.count(d,1);
			int z2 = rm.count(d,2);
			int z3 = rm.count(d,3);
			int z4 = rm.count(d,4);
			a.setGtime(d);
			a.setI1(z1);
			a.setI2(z2);
			a.setI3(z3);
			a.setI4(z4);
			list.add(a);
		}
		PageInfo<Sudu> pi = new PageInfo<Sudu>(list);
		
		Map<String,Object> result = new HashMap<>();
		result.put("rows", list);
		result.put("total", pi.getTotal());
		
		return result;
	}

	@Override
	public Map<String, Object> profit(Integer id) {
		Rentt rt = rm.getById(id);
		Date gtime = rt.getGtime();
		Date rtime = new Date();
		long timestamp = rtime.getTime()-gtime.getTime();
		int money;
		if(timestamp<=2*60*60*1000){
			money = 1;
		}else{
			long timestamp1 = timestamp-(2*60*60*1000);
			long cheng = (timestamp1+(2*60*60*1000)-1)/(60*60*1000);
			money = (int)(1+cheng);
		}
		Map<String,Object> map = new HashMap<>();
		map.put("money",money);
		return map;

	}

	@Override
	public Map<String, Object> queryy(Integer id,String time) {
		Bicycle b = bm.getBcById(id);
		String gno = b.getGno();
		List<Map<String,Object>> rt = rm.getListByIdTime(gno,time);
		String num = "",timee = "";
		for(int i = 0;i<rt.size();i++){
			Map<String,Object> m1 = rt.get(i);
			String num1 = getResultStr(m1,"COUNT1");
			String timee1 = getResultStr(m1,"DATE_DATA");

			num += num1 + ",";
			timee += timee1 + ",";
		}
		Map<String,Object> resultMap = new HashMap<>();
		resultMap.put("num",getStringArray(num));
		resultMap.put("time",getStringArray(timee));

		return resultMap;
	}

	@Override
	public Map<String, Object> compare(Integer id1, Integer id2) {
		Bicycle b1 = bm.getBcById(id1);
		Bicycle b2 = bm.getBcById(id2);
		Map<String,Object> m1 = rm.getProfit(b1);
		Map<String,Object> m2 = rm.getProfit(b2);
		Map<String,Object> resultMap = new HashMap<>();
		resultMap.put("profit1",m1.get("PROFIT"));
		resultMap.put("profit2",m2.get("PROFIT"));
		resultMap.put("name1",b1.getModel());
		resultMap.put("name2",b2.getModel());
		resultMap.put("count1",m1.get("COUNTT"));
		resultMap.put("count2",m2.get("COUNTT"));
		return resultMap;


	}

	@Override
	public Map<String, Object> queryyday(Integer id, String time) {
		Bicycle b = bm.getBcById(id);
		String gno = b.getGno();
		List<Map<String,Object>> rt = rm.getDayListByIdTime(gno,time);
		String num = "",timee = "";
		for(int i = 0;i<rt.size();i++){
			Map<String,Object> m1 = rt.get(i);
			String num1 = getResultStr(m1,"COUNT1");
			String timee1 = getResultStr(m1,"DATE_DATA");

			num += num1 + ",";
			timee += timee1 + ",";
		}
		Map<String,Object> resultMap = new HashMap<>();
		resultMap.put("num",getStringArray(num));
		resultMap.put("time",getStringArray(timee));

		return resultMap;

	}

	public String getResultStr(Map<String,Object> queryResultMap,
							   String queryResultKey){
		String resultStr = "";
		try {
			resultStr = queryResultMap.get(queryResultKey) == null ? "'-'"
					: queryResultMap.get(queryResultKey).toString();
		}catch (Exception e){
			e.printStackTrace();
		}
		return resultStr;
	}
	public String[] getStringArray(String paramString){
		paramString = paramString.substring(0,paramString.length()-1);
		return paramString.split(",");
	}

}
