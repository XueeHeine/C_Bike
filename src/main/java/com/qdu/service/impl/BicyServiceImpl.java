package com.qdu.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qdu.beans.Bicy;
import com.qdu.mapper.BicyMapper;
import com.qdu.service.BicyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Alpha-LGC on 2018/11/16.
 */
@Service
public class BicyServiceImpl implements BicyService {

    @Autowired
    private BicyMapper mapper;

    @Transactional
    @Override
    public void doAdd(Bicy bicy) {
        Bicy e = mapper.getBicy(bicy);
        if(null != e){
            throw new RuntimeException("单车："+bicy.getBno()+"已存在！！");
        }
        int result = mapper.add(bicy);
        if(result <= 0){
            throw new RuntimeException("新增员工:"+bicy.getBno()+"失败！");
        }
    }

 /*   @Override
    public Emp getEno(String eno) {
        return mapper.getEno(eno);
    }*/

    @Override
    public Map<String, Object> query(Integer page, Integer rows, Bicy bicy) {
        System.out.println("到这里吗");
        PageHelper.startPage(page,rows);
        List<Bicy> list = mapper.query(bicy);

        PageInfo<Bicy> pi = new PageInfo<>(list);
        Map<String,Object> result = new HashMap<>();
        result.put("rows",list);
        result.put("total",pi.getTotal());
        return result;
    }

    @Transactional
    @Override
    public boolean update(Bicy bicy) {
        /*Emp e = mapper.findEmpById(emp.getId());
        if(null==e){
            throw  new RuntimeException("该员工"+e.getEno()+"不存在！请刷新列表");
        }*/
        Bicy e = mapper.getBicy(bicy);
        if(null != e && e.getId()!=bicy.getId()){
            throw new RuntimeException("单车："+bicy.getBno()+"已存在！！");
        }
        int result = mapper.update(bicy);
        if(result<1){
            throw  new RuntimeException("修改该员工失败");
        }
        return result==1;
    }

    @Override
    public Bicy getBicy(Bicy bicy) {
        return mapper.getBicy(bicy);
    }

    /* @Override
     public Emp fineEmpById(Integer id) {
         return mapper.findEmpById(id);
     }
 */
    @Transactional
    @Override
    public int remove(List<Integer> ids) {
        if(ids!=null&&ids.size()>0){
            return mapper.remove(ids);
        }

        return 0;
    }

    @Override
    public List<Bicy> getAllXinghao() {
        return mapper.getAllXinghao();
    }

    @Override
    public int subCount(String bno) {
        return mapper.subCount(bno);
    }

    @Override
    public int addCount(String bno) {
        return mapper.addCount(bno);
    }

    @Override
    public Bicy gettype(String bno) {
        return mapper.gettype(bno);
    }
}
