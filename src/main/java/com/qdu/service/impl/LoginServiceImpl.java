package com.qdu.service.impl;

import com.qdu.beans.CModule;
import com.qdu.beans.CRole;
import com.qdu.beans.CUser;
import com.qdu.mapper.CRoleMapper;
import com.qdu.mapper.CUserMapper;
import com.qdu.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Alpha-LGC on 2019/1/25.
 */
@Service
public class LoginServiceImpl implements LoginService{
    @Autowired
    private CUserMapper mapper;

    @Autowired
    private CRoleMapper roleMapper;


    @Override
    public CUser getUserByUS(CUser user) {
        return mapper.getUserByUS(user);
    }

    @Override
    public List<CRole> queryRolesByUserId(CUser user) {
        return roleMapper.queryRolesByUserId(user);
    }

    @Override
    public List<CModule> queryModulesByRoleId(CRole role) {
        return roleMapper.queryModulesByRoleId(role);
    }

    @Override
    public CUser getUserByName(CUser user) {
        return mapper.getUserByName(user);
    }


}
