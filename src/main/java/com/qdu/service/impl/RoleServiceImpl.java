package com.qdu.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qdu.beans.*;
import com.qdu.mapper.CRoleMapper;
import com.qdu.mapper.CRoleModuleMapper;
import com.qdu.mapper.CUserRoleMapper;
import com.qdu.service.RoleService;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Alpha-LGC on 2019/3/25.
 */
@Service
public class RoleServiceImpl implements RoleService{
    @Autowired
    private CRoleMapper mapper;

    @Autowired
    private CUserRoleMapper crmapper;

    @Autowired
    private CRoleModuleMapper cmm;
    @Override
    public List<CRole> queryAllRoles() {
        return mapper.queryAllRoles();
    }
    @Transactional
    @Override
    public int addOrDelUsersRole(CUserRole ur, Integer flag) {
        if(flag == 1){
            if(ur.getRoleid() == 0l){
                return crmapper.deleteUsersAllRoleByUserid(ur);
            }
            return  crmapper.deleteUsersRoleByUserid(ur);
        }else{
            if(ur.getRoleid()==0l){
                List<CRole> roles = mapper.queryAllRoles();

                if(null!= roles){
                    for(CRole c : roles){
                        CUserRole cr = new CUserRole(null,ur.getUserid(),c.getId());
                        crmapper.addUsersRole(cr);
                    }
                }
                return 0;
            }
            return crmapper.addUsersRole(ur);
        }
    }

    @Override
    public Map<String, Object> queryAllRolesPage(Integer page, Integer rows) {
        PageHelper.startPage(page,rows);
        List<CRole> list = mapper.queryAllRoles();
        if(null == list)
            return null;
        PageInfo<CRole> pi = new PageInfo<>(list);
        Map<String,Object> map = new HashedMap();
        map.put("rows",list);
        map.put("total",pi.getTotal());
        return map;
    }

    @Override
    public int addRoleByName(CRole role) {
        return mapper.addRoleByName(role);
    }

    @Override
    public List<CModule> queryModulesByRoleId(CRole role) {
        return mapper.queryModulesByRoleId(role);
    }

    @Override
    public int addRolesModules(CRoleModule cm, Integer flag) {
        try {
            if (flag == 0) {//那就是添加
                if(cm.getModuleid()>0) {
                    cmm.addRolesModule(cm);
                }else if(cm.getModuleid() <0){
                    List<CRoleModule> list = new ArrayList<>();
                    Map<String,String> map = new HashMap();
                    map.put("roleid",cm.getRoleid().toString());
                    map.put("moduleName",cm.getModuleid().toString());
                    list = cmm.getBatchCMByModuleName(map);
                    if(list == null)
                        return 0;
                    for(CRoleModule c : list){
                        cmm.addRolesModule(c);
                    }
                }
            }else if (flag == 1){
                if(cm.getModuleid()<0){
                    cmm.batchDeleteModulesByRole(cm);
                }else if(cm.getModuleid()>0){
                    cmm.deleteModuleByRole(cm);
                }
            }

            return 0;
        }catch (Exception e){
            return 1;
        }


    }

    @Override
    public int batchdeleteRolesById(List<Long> ids) {
        return mapper.batchdeleteRolesById(ids);
    }
}
