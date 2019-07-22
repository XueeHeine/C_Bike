package com.qdu.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qdu.beans.Bicy;
import com.qdu.beans.Bicycle;
import com.qdu.beans.Sup;
import com.qdu.mapper.SupMapper;
import com.qdu.service.SupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by admin on 2019/4/16.
 */
@Service
public class SupServiceImpl implements SupService {
    @Autowired
    private SupMapper sm;

    @Override
    public void doAdd(Sup sup) {
        String sname = sup.getSname();
        int count = sm.getCountBySname(sname);

        if(count>0){
            throw new RuntimeException("不可添加相同名称的供应商");
        }


        int result = sm.insert(sup);
        if(result <=0){
            throw new RuntimeException("添加供应商"+sup.getSname()+"失败");
        }
    }

    @Override
    public Map<String, Object> query(Integer page, Integer rows, String sname, Integer priority) {
        PageHelper.startPage(page, rows);
        List<Sup> list = sm.query(sname,priority);
        PageInfo<Sup> pi = new PageInfo<Sup>(list);

        Map<String,Object> result = new HashMap<>();
        result.put("rows", list);
        result.put("total", pi.getTotal());
        return result;
    }

    @Override
    public int doRemove(List<Integer> ids) {



        try{
            if(ids != null&&ids.size()>0){

                sm.remove(ids);
            }
        }catch(Exception e){
            e.printStackTrace();
            throw new RuntimeException("删除失败");
        }
        return 0;
    }

    @Override
    public Sup getSupById(Integer id) {
        return sm.getSupById(id);
    }

    @Override
    public void doUpdate(Sup sup) {
        int result = sm.update(sup);


        if(result <=0){
            throw new RuntimeException("更新失败");
        }
    }
}
