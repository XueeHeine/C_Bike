package com.qdu.controller;

import com.qdu.beans.CUserMission;
import com.qdu.beans.Mission;
import com.qdu.service.MissionService;
import com.qdu.utils.ResultMsg;
import com.qdu.utils.TreeNode;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;


@RequestMapping(value="/mission")
@Controller
public class MissionController {
	@Autowired
	private MissionService ms;
	
	@RequestMapping(value="/list")
	public String list(){
		return "mission/list";
	}
	@RequestMapping(value="/addAndUpdate")
	public String addAndUpdate(){
		return "mission/addAndUpdate";
	}
	@RequestMapping(value="/addMan")
	public String addMan(){
		return "mission/addMan";
	}
	@RequestMapping(value="/updateMan")
	public String updateMan(String mid, Model model){
		model.addAttribute("mid",mid);
		return "mission/updateMan";
	}

	@RequestMapping(value="/map")
	public String map(){
		return "mission/map";
	}
	@RequestMapping(value="/updatezt")
	public String updatezt(String mid, Model model){
		model.addAttribute("mid",mid);
		return "mission/updatezt";
	}
	@RequestMapping(value="/list2")
	public String list2(){
		return "mission/list2";
	}
	@ResponseBody
	@RequestMapping(value="/getManList")
	public List<TreeNode> getManList(@RequestParam(value="index",defaultValue="0") Integer index){
		return ms.getMan(index);
	}

/*	@RequestMapping(value="/empAddAndUpdate",method=RequestMethod.GET)
	public String empAddAndUpdate(Integer id,Model m){
		if(id!=null){
			Bicycle bicycle =  ms.getBcById(id);
			m.addAttribute("b", bicycle);
		}

		return "/bike/addAndUpdate";
	}

	@ResponseBody
	@RequestMapping(value="/getSup")
	public List<TreeNode> getSup(@RequestParam(value="index",defaultValue="0") Integer index){

		return ms.getSup(index);
	}*/

	@RequiresPermissions({"单车权限_修改","系统权限_添加"})
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public String add(Mission mission){
		try{
			ms.doAdd(mission);
			//return new ResultMsg(200,"新增任务"+mission.getMname()+"成功");
			Subject subject = SecurityUtils.getSubject();
			Session session = subject.getSession();
			session.setAttribute("mission",mission);
			return "/mission/addMan";
		}catch(Exception e){
			e.printStackTrace();
			//return new ResultMsg(500,e.getMessage());
			return null;
		}
	}
	//添加 c_u_m
	@RequiresPermissions({"单车权限_修改","系统权限_添加"})
	@RequestMapping(value="/addRM",method=RequestMethod.POST)
	public String addRM(CUserMission cUserMission){
		try{
			Subject subject = SecurityUtils.getSubject();
			Session session = subject.getSession();
			Mission mission = (Mission) session.getAttribute("mission");
			cUserMission.setMid(mission.getMid());
			ms.addCUM(cUserMission);
			return "/mission/list";
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	@ResponseBody
	@RequestMapping(value="/query",method=RequestMethod.POST)
	public Map<String,Object> query(Integer page, Integer rows, @RequestParam(value="mname",defaultValue="") String mname, @RequestParam(value="qzhj",defaultValue="") String qzhj, @RequestParam(value="mstatus",defaultValue="") Integer mstatus){

		return ms.query(page,rows,mname,qzhj,mstatus);

	}
	@RequiresPermissions({"单车权限_修改","单车权限_删除"})
	@ResponseBody
	@RequestMapping(value="/remove1",method=RequestMethod.POST)
	public ResultMsg remove1(String mid){

		ms.doRemove(Integer.valueOf(mid));
		return new ResultMsg(null,"成功关闭任务！");
	}
	@RequiresPermissions({"单车权限_修改"})
	@ResponseBody
	@RequestMapping(value="/update",method=RequestMethod.POST)
	public ResultMsg update(Mission mission){

		try{
			ms.doUpdate(mission.getMid(), Integer.valueOf(mission.getUserName()));
			return new ResultMsg(200,"更新成功!");
		}catch(Exception e){
			e.printStackTrace();
			return new ResultMsg(500,e.getMessage());
		}

	}
	@RequiresPermissions({"单车权限_修改"})
	@ResponseBody
	@RequestMapping(value="/update2",method=RequestMethod.POST)
	public ResultMsg update2(Mission mission){

		try{
			ms.doUpdate2(mission.getMid(), mission.getMstatus());
			return new ResultMsg(200,"更新成功!");
		}catch(Exception e){
			e.printStackTrace();
			return new ResultMsg(500,e.getMessage());
		}

	}
/*
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
	}*/
	
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
