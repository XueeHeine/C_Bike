package com.qdu.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.qdu.beans.Bicycle;
import com.qdu.service.BicycleService;
import com.qdu.utils.ResultMsg;
import com.qdu.utils.TreeNode;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;



@RequestMapping(value="/bicycle")
@Controller
public class BicycleController {
	@Autowired
	private BicycleService bs;
	
	@RequestMapping(value="/list")
	public String list(){
		return "bike/list";
	}
	@RequestMapping(value="/list1",method=RequestMethod.GET)
	public String list1(){
		return "emp/list1";
	}
	@RequestMapping(value="/list2",method=RequestMethod.GET)
	public String list2(){
		return "emp/list2";
	}

	@RequestMapping(value="/empAddAndUpdate",method=RequestMethod.GET)
	public String empAddAndUpdate(Integer id,Model m){
		if(id!=null){
			Bicycle bicycle =  bs.getBcById(id);
			m.addAttribute("b", bicycle);
		}

		return "/bike/addAndUpdate";
	}

	@ResponseBody
	@RequestMapping(value="/getSup")
	public List<TreeNode> getSup(@RequestParam(value="index",defaultValue="0") Integer index){

		return bs.getSup(index);
	}

	@RequiresPermissions({"单车权限_修改","系统权限_添加"})
	@ResponseBody
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public ResultMsg add(Bicycle bicycle){


		try{
			bs.doAdd(bicycle);
			return new ResultMsg(200,"新增单车类型"+bicycle.getModel()+"成功");
		}catch(Exception e){
			e.printStackTrace();
			return new ResultMsg(500,e.getMessage());
		}
	}

	@ResponseBody
	@RequestMapping(value="/query",method=RequestMethod.POST)
	public Map<String,Object> query(Integer page,Integer rows,@RequestParam(value="model",defaultValue="") String model,@RequestParam(value="supid",defaultValue="0") Integer supid){

		return bs.query(page, rows, model,supid);

	}
    @RequiresPermissions({"单车权限_修改","单车权限_删除"})
	@ResponseBody
	@RequestMapping(value="/remove",method=RequestMethod.POST)
	public ResultMsg remove(@RequestBody List<Integer> ids){


		try{

			int a = bs.doRemove(ids);
			if(a==1){
				return new ResultMsg(500,"未全部归还不可删除！");
			}else{
				return new ResultMsg(200,"成功删除");
			}
		}catch(Exception e){
			e.printStackTrace();
			return new ResultMsg(500,e.getMessage());
		}

	}
    @RequiresPermissions({"单车权限_修改"})
	@ResponseBody
	@RequestMapping(value="/remove1",method=RequestMethod.POST)
	public ResultMsg remove1(Integer idd){

		List<Integer> list = new ArrayList<Integer>();
		list.add(idd);
		int a = bs.doRemove(list);
		if(a==1){
			return new ResultMsg(null,"未全部归还不可删除！");
		}

		return new ResultMsg(null,"成功删除员工信息！");

	}

	@RequiresPermissions({"单车权限_修改"})
	@ResponseBody
	@RequestMapping(value="/update",method=RequestMethod.POST)
	public ResultMsg update(Bicycle bicycle){

		try{
			bs.doUpdate(bicycle);
			return new ResultMsg(200,"更新成功");
		}catch(Exception e){
			e.printStackTrace();
			return new ResultMsg(500,e.getMessage());
		}

	}
	@ResponseBody
	@RequestMapping(value="/getBicycle")
	public List<TreeNode> getBicycle(@RequestParam(value="id",defaultValue="0") Integer id){

		return bs.getModel();
	}
	
	/*
	

	

	@ResponseBody
	@RequestMapping(value="/update",method=RequestMethod.POST)
	public ResultMsg update(Bicycle bicycle){
		
		try{
			bs.doUpdate(bicycle);
			return new ResultMsg(200,"���³ɹ���");
		}catch(Exception e){
			e.printStackTrace();
			return new ResultMsg(500,e.getMessage());
		}
		
	}
	@ResponseBody
	@RequestMapping(value="/remove",method=RequestMethod.POST)
	public ResultMsg remove(@RequestBody List<Integer> ids){
		
		
		try{
			
			int a = bs.doRemove(ids);
			if(a==1){
				return new ResultMsg(500,"δȫ���黹����ɾ��");
			}else{
			return new ResultMsg(200,"�ɹ�ɾ��");
			}
		}catch(Exception e){
			e.printStackTrace();
			return new ResultMsg(500,e.getMessage());
		}
		
	}
	@ResponseBody
	@RequestMapping(value="/remove1",method=RequestMethod.POST)
	public ResultMsg remove1(Integer idd){
		
			List<Integer> list = new ArrayList<Integer>();
			list.add(idd);
			int a = bs.doRemove(list);
			if(a==1){
				return new ResultMsg(null,"δȫ���黹����ɾ����");
			}
			
			return new ResultMsg(null,"�ɹ�ɾ��Ա����Ϣ��");
		
	}
	@ResponseBody
	@RequestMapping(value="/getBicycle")
	public List<TreeNode> getBicycle(@RequestParam(value="id",defaultValue="0") Integer id){
		System.out.println("~~~~~~~~~~~~~"+id);
		return bs.getModel();
	}
*/

}
