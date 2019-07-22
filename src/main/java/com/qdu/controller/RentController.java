package com.qdu.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.qdu.beans.Rentt;
import com.qdu.service.RentService;
import com.qdu.utils.ResultMsg;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;



@Controller
@RequestMapping(value="rent")
public class RentController {
	@Autowired
	private RentService rs;

	@RequestMapping(value="/list")
	public String list(){
		return "bike/list1";
	}
	@RequestMapping(value="/list1")
	public String list1(){
		return "bike/list2";
	}
	@RequestMapping(value="/list3")
	public String list3(){
		return "bike/list3";
	}
	@RequestMapping(value="/empAddAndUpdate",method=RequestMethod.GET)
	public String empAddAndUpdate(Integer id,Model m,String etoak,Integer xml){
		/*if(id!=null){
			Bicycle bicycle =  bs.getBcById(id);
			m.addAttribute("b", bicycle);
		}
		*/
		/*Date a = new Date();
		String date = new SimpleDateFormat("yyyy-MM-dd").format(a);
		m.addAttribute("time",date);*/
		
		String maxid = rs.getMaxId();
		m.addAttribute("idd", maxid);
		return "/bike/addAndUpdate2";
	}
	@RequiresPermissions({"单车权限_修改","单车权限_添加"})
	@ResponseBody
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public ResultMsg add(Rentt rent){
		
		
		try{
			rs.doAdd(rent);
			return new ResultMsg(200,"单车组接订单"+rent.getRno()+"成功");
		}catch(Exception e){
			e.printStackTrace();
			return new ResultMsg(500,e.getMessage());
		}
	}
	@ResponseBody
	@RequestMapping(value="/profit",method=RequestMethod.POST)
	public Map<String,Object> profit(@RequestParam(value="idd")Integer id){
		return rs.profit(id);


	}
	@ResponseBody
	@RequestMapping(value="/queryy",method=RequestMethod.POST)
	public Map<String,Object> queryy(Integer id,String time){
		return rs.queryy(id,time);


	}
	@ResponseBody
	@RequestMapping(value="/queryyday",method=RequestMethod.POST)
	public Map<String,Object> queryyday(Integer id,String time){
		return rs.queryyday(id,time);


	}
	@ResponseBody
	@RequestMapping(value="/compare",method=RequestMethod.POST)
	public Map<String,Object> compare(Integer bike1,Integer bike2){
		return rs.compare(bike1,bike2);


	}
	@ResponseBody
	@RequestMapping(value="/query",method=RequestMethod.POST)
	public Map<String,Object> query(Integer page,Integer rows){
		
		return rs.query(page, rows);
		
	}
	@RequiresPermissions({"单车权限_修改","单车权限_添加","单车权限_删除"})
	@ResponseBody
	@RequestMapping(value="/jia",method=RequestMethod.POST)
	public ResultMsg jia(Integer idd,Integer money){
		
		
		try{
			rs.jia(idd,money);
			return new ResultMsg(200,"还车成功");
		}catch(Exception e){
			e.printStackTrace();
			return new ResultMsg(500,e.getMessage());
		}
	}
	@ResponseBody
	@RequestMapping(value="/count",method=RequestMethod.POST)
	public  Map<String,Object> count(@DateTimeFormat(pattern = "yyyy-MM-dd")@RequestParam(value="ftime",defaultValue="" ) Date ftime,
									 @DateTimeFormat(pattern = "yyyy-MM-dd")@RequestParam(value="ltime",defaultValue="") Date ltime){


		return rs.number(ftime,ltime);
		
		
		
		
		
		
	}
	
}
