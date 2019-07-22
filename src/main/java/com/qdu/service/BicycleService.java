package com.qdu.service;

import com.qdu.beans.Bicycle;
import com.qdu.utils.TreeNode;

import java.util.List;
import java.util.Map;




public interface BicycleService {
	public void doAdd(Bicycle bic);

	public Map<String, Object> query(Integer page, Integer rows, String model, Integer supid);

	public Bicycle getBcById(Integer id);

	public void doUpdate(Bicycle bicycle);

	public int doRemove(List<Integer> ids);

	public List<TreeNode> getModel();

    List<TreeNode> getSup(Integer index);
}
