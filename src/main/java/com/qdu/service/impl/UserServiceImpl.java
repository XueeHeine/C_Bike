package com.qdu.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qdu.beans.CUser;
import com.qdu.mapper.CUserMapper;
import com.qdu.service.UserService;
import org.apache.commons.collections.map.HashedMap;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Alpha-LGC on 2019/2/12.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private CUserMapper mapper;


    @Override
    public Map<String, Object> queryAllUser(Integer page, Integer rows) {
        PageHelper.startPage(page,rows);
        List<CUser> list = mapper.queryAll();
        if(null == list)
            return null;
        PageInfo<CUser> pi = new PageInfo<>(list);
        Map<String,Object> map = new HashedMap();
        map.put("rows",list);
        map.put("total",pi.getTotal());
        return map;
    }

    @Override
    public int insert(CUser user) {
        return mapper.insert(user);
    }

    @Override
    public int update(CUser user) {
        return mapper.updateByPrimaryKey(user);
    }

    @Override
    public int deleteByPrimaryKeys(List<Long> ids) {
        for(Long l : ids)
            mapper.deleteByPrimaryKey(l);
        return ids.size();
    }
}
