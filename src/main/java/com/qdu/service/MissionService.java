package com.qdu.service;

import com.qdu.beans.CUserMission;
import com.qdu.beans.Mission;
import com.qdu.utils.TreeNode;

import java.util.List;
import java.util.Map;


public interface MissionService {
	public void doAdd(Mission mission);

/*
	public Map<String, Object> query(Integer page, Integer rows, String model, Integer supid);
*/

/*	public Bicycle getBcById(Integer id);

	public void doUpdate(Bicycle bicycle);

	public int doRemove(List<Integer> ids);

	public List<TreeNode> getModel();*/

    List<TreeNode> getMan(Integer index);

    public void addCUM(CUserMission cUserMission);

    Map<String,Object> query(Integer page, Integer rows, String mname, String qzhj,Integer mstatus);

    void doRemove(Integer id);

    void doUpdate(Integer id,Integer man);
    void doUpdate2(Integer id,String mstatus);
}
