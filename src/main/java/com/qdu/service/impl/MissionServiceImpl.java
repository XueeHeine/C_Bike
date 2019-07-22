package com.qdu.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qdu.beans.CUser;
import com.qdu.beans.CUserMission;
import com.qdu.beans.Mission;
import com.qdu.mapper.MissionMapper;
import com.qdu.service.MissionService;
import com.qdu.utils.TreeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class MissionServiceImpl implements MissionService {
	@Autowired
	private MissionMapper mm;
	
	@Override
	public void doAdd(Mission mission) {
		//mission.set
		if ("未领取".equals(mission.getMstatus())){
			mission.setMstatus("0");
		}
		if ("工作中".equals(mission.getMstatus())){
			mission.setMstatus("1");
		}
		if ("等待完成检验".equals(mission.getMstatus())){
			mission.setMstatus("2");
		}
		if ("关闭".equals(mission.getMstatus())){
			mission.setMstatus("3");
		}
		mission.setStime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		mm.insert(mission);
	}

	@Override
	public List<TreeNode> getMan(Integer index) {
		List<TreeNode> trees = Collections.EMPTY_LIST;
		List<CUser> list = mm.getMan();
		if(list != null&& list.size() > 0){
			trees = new ArrayList<>();
			TreeNode tree = null;
			if(index == 1){
				tree = new TreeNode();
				tree.setId(0l);
				tree.setText("");
				tree.setState("open");
				trees.add(tree);
			}
			for(CUser d:list){
				tree = new TreeNode();
				tree.setId(Long.parseLong(d.getId()+""));
				tree.setText(d.getUsername());
				tree.setState("open");
				trees.add(tree);
			}
		}
		return trees;
	}

	@Override
	public void addCUM(CUserMission cUserMission) {

		//cUserMission.getMname();
		//String a = mm.selectByName(cUserMission.getMname()).getId().toString();
		//cUserMission.setId(Integer.valueOf(a));
		mm.insertCUM(cUserMission);
	}

	@Override
	public Map<String, Object> query(Integer page, Integer rows,  String mname, String qzhj,Integer mstatus) {
		PageHelper.startPage(page, rows);
		List<Mission> list = mm.query(mname,qzhj,mstatus);
		PageInfo<Mission> pi = new PageInfo<Mission>(list);
		Map<String,Object> result = new HashMap<>();
		result.put("rows", list);
		result.put("total", pi.getTotal());
		return result;
	}

	@Override
	public void doRemove(Integer id) {
		mm.doRemove(id);
	}

	@Override
	public void doUpdate(Integer id, Integer man) {
		CUserMission cUserMission = new CUserMission();
		cUserMission.setMid(id);
		cUserMission.setUid(man);
		mm.doUpdate(cUserMission);
	}

	@Override
	public void doUpdate2(Integer id, String mstatus) {
		Mission mission = new Mission();
		mission.setMid(id);
		mission.setMstatus(mstatus);
		if ("3".equals(mstatus)){
			mission.setEtime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		}
		mm.doUpdate2(mission);
	}
/*
	@Override
	public Map<String, Object> query(Integer page, Integer rows, String model,Integer supid) {
		PageHelper.startPage(page, rows);
		List<Bicycle> list = bm.query(model,supid);
		PageInfo<Bicycle> pi = new PageInfo<Bicycle>(list);
		
		Map<String,Object> result = new HashMap<>();
		result.put("rows", list);
		result.put("total", pi.getTotal());
		return result;
	}

	@Override
	public Bicycle getBcById(Integer id) {
		// TODO Auto-generated method stub
		return bm.getBcById(id);
	}

	@Override
	public void doUpdate(Bicycle bicycle) {
		String supid = bicycle.getSup().getId().toString();
		List<Bicycle> list = bm.getModelBySupid(supid);
		if(list!=null&&list.size()>0){
			for(Bicycle bc:list){
				if(bicycle.getModel().equals(bc.getModel())){
					throw new RuntimeException("不可在同一个供应商下添加相同型号的单车");
				}
			}
		}
		
		int result = bm.update(bicycle);


		if(result <=0){
			throw new RuntimeException("更新失败");
		}

	}

	@Override
	public int doRemove(List<Integer> ids) {
		List<Bicycle> list = new ArrayList<Bicycle>();
		list = bm.getBcs(ids);
		for(Bicycle bc :list){
			if(bc.getNnumber()!=bc.getAnumber()){
				return 1;
				
			}
		}
		
		
		try{
			if(ids != null&&ids.size()>0){
				System.out.println(ids);
				 bm.remove(ids);
			}
		}catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException("删除失败");
		}
		return 0;
	}

	@Override
	public List<TreeNode> getModel() {
		List<TreeNode> trees = Collections.EMPTY_LIST;
		List<Bicycle> list = bm.getModel();
		if(list != null&& list.size() > 0){
			trees = new ArrayList<>();
			TreeNode tree = null;
			for(Bicycle d:list){
				tree = new TreeNode();
				tree.setId(Long.parseLong(d.getId()+""));
				tree.setText(d.getModel());
				tree.setState("open");
				trees.add(tree);
			}
		}
		return trees;
	}

	@Override
	public List<TreeNode> getSup(Integer index) {
		List<TreeNode> trees = Collections.EMPTY_LIST;
		List<Sup> list = bm.getSup();
		if(list != null&& list.size() > 0){
			trees = new ArrayList<>();
			TreeNode tree = null;
			if(index == 1){
				tree = new TreeNode();
				tree.setId(0l);
				tree.setText("");
				tree.setState("open");
				trees.add(tree);
			}
			for(Sup d:list){
				tree = new TreeNode();
				tree.setId(Long.parseLong(d.getId()+""));
				tree.setText(d.getSname());
				tree.setState("open");
				trees.add(tree);
			}
		}
		return trees;
	}*/

}
