package com.qdu.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.qdu.beans.Bicycle;
import com.qdu.beans.Sup;
import com.qdu.mapper.BicycleMapper;
import com.qdu.service.BicycleService;
import com.qdu.utils.TreeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
@Service
public class BycycleServiceImpl implements BicycleService {
	@Autowired
	private BicycleMapper bm;
	
	@Override
	public void doAdd(Bicycle bic) {
		String supid = bic.getSup().getId().toString();
		List<Bicycle> list = bm.getModelBySupid(supid);
		if(list!=null&&list.size()>0){
			for(Bicycle bc:list){
				if(bic.getModel().equals(bc.getModel())){
					throw new RuntimeException("不可在同一个供应商下添加相同型号的单车");
				}
			}
		}
		int aaa = bic.getNnumber();
		bic.setAnumber(aaa);
		int result = bm.insert(bic);
		if(result <=0){
			throw new RuntimeException("添加单车类型"+bic.getModel()+"失败");
		}

	}

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
	}

}
